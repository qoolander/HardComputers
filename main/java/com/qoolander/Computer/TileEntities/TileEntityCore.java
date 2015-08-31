package com.qoolander.Computer.TileEntities;

import com.qoolander.Computer.ComputerMain;
import com.qoolander.Computer.IComputerComponent;
import com.qoolander.Computer.blocks.BlockMem;
import com.qoolander.Computer.blocks.BlockOutput;
import com.qoolander.Computer.blocks.BlockWire;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sam on 25/08/2015.
 */
public class TileEntityCore extends ComputerTileEntityBase{

    public boolean ConnectToWire(ForgeDirection direction){
        return true;
    }

    private HashMap<Byte, ComputerTileEntityBase> network;

    void updateNetwork(){
        network = new HashMap<Byte, ComputerTileEntityBase>();

        ArrayList<Vec3> searchedBlocks = new ArrayList<Vec3>();

        for (int i = 0; i < connectedWires.size(); i++) {
            getNewBlock(connectedWires.get(i).xCoord, connectedWires.get(i).yCoord, connectedWires.get(i).zCoord, searchedBlocks);
        }

        if(marAddress==null) throw new Error("no Mar found");

        System.out.println(network);
    }

    public void onPlace(){
        super.onPlace();
        updateNetwork();
    }

    public void onBlockUpdate(){
        super.onBlockUpdate();
        updateNetwork();
        if(this.worldObj.getBlockPowerInput(xCoord, yCoord, zCoord)>0){
            startUp();
        }else{
            ShutDown(); //TODO implement ShutDown
        }
    }

    private void ShutDown() {
        running = false;
        for(int i = 0; i < network.size(); i++){
            network.get((byte)i).onShutDown();
        }
    }

    private Byte IAR = 0;
    private Byte IR = 0;
    private Byte[] registers = new Byte[4];
    private Byte IOAddress;
    private Byte marAddress;
    private Byte romAddress;

    private boolean CFlag = false;
    private boolean AFlag = false;
    private boolean EFlag = false;
    private boolean ZFlag = false;

    public void getNewBlock(int x, int y, int z, ArrayList<Vec3 > alreadySearchedBlocks){
        Vec3 location = Vec3.createVectorHelper(x, y, z);

        if(!ComputerMain.Vec3Contains(alreadySearchedBlocks, location) && worldObj.getTileEntity(x,y,z) instanceof TileEntityWire) {
            alreadySearchedBlocks.add(location);

            TileEntityWire currentWire = (TileEntityWire) this.worldObj.getTileEntity(x, y, z);
            for(int i = 0; i<currentWire.connectedTileEntities.size(); i++) {
                TileEntity te = currentWire.connectedTileEntities.get(i);
                if(te instanceof ComputerTileEntityBase){
                    network.put(((ComputerTileEntityBase) te).setByteAddress(new ArrayList<Byte>(network.keySet())), ((ComputerTileEntityBase) te));
                    if(te instanceof TileEntityMar) marAddress = ((TileEntityMar) te).getAddress();
                    if(te instanceof TileEntityRom) romAddress = ((TileEntityRom) te).getAddress();

                }else if(te instanceof TileEntityWire){
                    getNewBlock(((TileEntityWire) te).xCoord, ((TileEntityWire) te).yCoord, ((TileEntityWire) te).zCoord, alreadySearchedBlocks);
                }
            }


        }
    }

    private boolean running = false;

    public void startUp(){
        for(int i = 0; i < network.size(); i++){
            network.get((byte)i).onStart();
        }
        loadRomToRam();
        running = true;
    }

    public void updateEntity(){

        if(running) this.cycle();
    }

    private void loadRomToRam(){

        for(int i = 0; i<((TileEntityRom) network.get(romAddress)).instructions.length; i++){
            ((TileEntityMar) network.get(marAddress)).setDataAtAddress((byte)i, ((TileEntityRom) network.get(romAddress)).instructions[i]);
        }
    }

    public void cycle(){
        IR = ((TileEntityMar) network.get(marAddress)).getDataAtAddress(IAR);

        int num = (int)(IR)&0xFF;

        switch(num>>4) {
            case 0xF:
                compare(num&0xF);
                break;
            case 0x8:
                add(num&0xF);
                break;
            case 0x6:
                CLF();
                break;
            case 0x5:
                jumpif(num&0xF);
                break;
            case 0x4:
                jump();
                break;
            case 0x7:
                IO(num & 0xF);
                break;
            case 0x2:
                data(num & 0xF);
                break;
            case 0x1:
                store(num & 0xF);
                break;
            case 0x0:
                load(num & 0xF);
                break;
        }

        IAR++;
    }

    private void CLF(){
        CFlag = AFlag = EFlag = ZFlag = false;
    }


    private void compare(int registerData) {
        int ra = (registerData>>2)&0x3;
        int rb = registerData&0x3;

        AFlag = registers[ra]>registers[rb];
        EFlag = registers[ra].equals(registers[rb]);
    }

    private void jumpif(int flags){
        int C = flags>>3;
        int A = (flags>>2)&0x1;
        int E = (flags>>1)&0x1;
        int Z = flags&0x1;

        if (((C == 1 && CFlag) || !(C == 1)) && ((A == 1 && AFlag) || !(A == 1)) && ((E == 1 && EFlag) || !(E == 1)) && ((Z == 1 && ZFlag) || !(Z == 1))) {
            jump();
        }else{
            IAR++;
        }
    }

    private void jump(){
        IAR++;

        IAR = ((TileEntityMar) network.get(marAddress)).getDataAtAddress(IAR);
    }

    private void data(int registerData){
        int r = registerData&0x3;
        IAR++;

        registers[r] = ((TileEntityMar) network.get(marAddress)).getDataAtAddress(IAR);
    }

    private void add(int registerData) {
        int ra = (registerData>>2)&0x3;
        int rb = registerData&0x3;

        registers[ra] = (byte)(registers[ra]+registers[rb]);
    }

    private void load(int registerData) {
        int address = (registerData>>2)&0x3;
        int data = registerData&0x3;

        registers[data] = ((TileEntityMar) network.get(marAddress)).getDataAtAddress(registers[address]);
    }

    private void store(int registerData){
        int r1 = (registerData>>2)&0x3;
        int r2 = registerData&0x3;

        ((TileEntityMar) network.get(marAddress)).setDataAtAddress(registers[r1], registers[r2]);
    }

    private void IO(int registerData){
        int IO = registerData>>3;
        int DataAddress = (registerData>>2)&0x1;
        int reg = registerData&0x3;

        if(IO==0){
            //input TODO Manage input
            if(DataAddress==0){
                //Data

            }else{
                //Address
                registers[reg] = IOAddress;
            }
        }else{
            //output
            if(DataAddress==0){
                //Data
                ((TileEntityOutput) network.get(IOAddress)).setData(registers[reg]);
            }else{
                //Address
                IOAddress = registers[reg];
            }
        }
    }

}
