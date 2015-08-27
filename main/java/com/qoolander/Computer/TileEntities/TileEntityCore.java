package com.qoolander.Computer.TileEntities;

import com.qoolander.Computer.ComputerMain;
import com.qoolander.Computer.IComputerComponent;
import com.qoolander.Computer.blocks.BlockMem;
import com.qoolander.Computer.blocks.BlockOutput;
import com.qoolander.Computer.blocks.BlockWire;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;
import scala.collection.mutable.HashTable;

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

        System.out.println(network);
    }

    public void onPlace(){
        super.onPlace();
        updateNetwork();
    }

    public void onBlockUpdate(){
        super.onBlockUpdate();
        updateNetwork();
    }

    private Byte[] registers = new Byte[4];
    private Byte IOAddress;


    public void getNewBlock(int x, int y, int z, ArrayList<Vec3 > alreadySearchedBlocks){
        Vec3 location = Vec3.createVectorHelper(x, y, z);

        if(!ComputerMain.Vec3Contains(alreadySearchedBlocks, location) && worldObj.getTileEntity(x,y,z) instanceof TileEntityWire) {
            alreadySearchedBlocks.add(location);

            TileEntityWire currentWire = (TileEntityWire) this.worldObj.getTileEntity(x, y, z);
            for(int i = 0; i<currentWire.connectedTileEntities.size(); i++) {
                TileEntity te = currentWire.connectedTileEntities.get(i);
                if(te instanceof ComputerTileEntityBase){
                    network.put(((ComputerTileEntityBase) te).setByteAddress(new ArrayList<Byte>(network.keySet())), ((ComputerTileEntityBase) te));
                }else if(te instanceof TileEntityWire){
                    getNewBlock(((TileEntityWire) te).xCoord, ((TileEntityWire) te).yCoord, ((TileEntityWire) te).zCoord, alreadySearchedBlocks);
                }
            }


        }
    }

    public void runCommand(int command){
        int num = command & 0xFF;

        switch(num>>4) {
            case 0x7:
                IO(num & 0xF);
                break;
            case 0x1:

                break;
            case 0x0:
                load(num & 0xF);
                break;
        }
    }

    private void load(int registerData){
        int r1 = (registerData>>2)&0x3;
        int r2 = registerData&0x3;


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
                IOAddress = registers[reg];
            }
        }else{
            //output
            if(DataAddress==0){
                //Data
                ((TileEntityOutput) network.get(IOAddress)).setData(registers[reg]);
            }else{
                //Address
                 registers[reg] = IOAddress;
            }
        }
    }

}
