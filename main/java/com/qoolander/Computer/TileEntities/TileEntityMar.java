package com.qoolander.Computer.TileEntities;

import com.qoolander.Computer.ComputerMain;
import com.qoolander.Computer.IComputerComponent;
import com.qoolander.Computer.blocks.BlockMem;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

public class TileEntityMar extends ComputerTileEntityBase implements IUpdatePlayerListBox{

    public TileEntityMar(){

    }

    public boolean ConnectToWire(ForgeDirection direction){
        if(direction==ForgeDirection.getOrientation(this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord))){
            return true;
        }else{
            return false;
        }
    }

    /*public void Init(){
        UpdateMemoryAmount();
        data = new byte[ByteCount];
    }*/

    private byte[] data;

    void UpdateMemoryAmount(){
        int result = 0;
        ArrayList<Vec3> checked = new ArrayList<Vec3>();

        for(int x = xCoord-1; x<=xCoord+1; x+=2) {
            result = getNewBlock(result, x, yCoord, zCoord, checked);
        }

        for(int y = yCoord-1; y<=yCoord+1; y+=2) {
            result = getNewBlock(result, xCoord, y, zCoord, checked);
        }

        for(int z = zCoord-1; z<=zCoord+1; z+=2) {
            result = getNewBlock(result, xCoord, yCoord, z, checked);
        }

        ByteCount = result;
    }


    int ByteCount = 0;

    public void updateEntity(){}

    public void onStart(){
        UpdateMemoryAmount();
        System.out.println("Bytes: " +ByteCount);
        data = new byte[ByteCount];
    }

    public void onPlace() {
        super.onPlace();
        UpdateMemoryAmount();
        System.out.println("Bytes: " +ByteCount);
        data = new byte[ByteCount];
    }

    public void update() {
    }

    public int getNewBlock(int result, int x, int y, int z, ArrayList<Vec3> alreadySearchedBlocks){
        Vec3 location = Vec3.createVectorHelper(x, y, z);

        if(!(this.worldObj.getBlock(x, y, z) instanceof BlockMem) || ComputerMain.Vec3Contains(alreadySearchedBlocks, location)) return result;

        BlockMem currentBlock = (BlockMem)this.worldObj.getBlock(x, y, z);
        result += currentBlock.ByteCount;

        alreadySearchedBlocks.add(location);

        for(int newX = x-1; newX<=x+1; newX+=2) {
            result = getNewBlock(result, newX, y, z, alreadySearchedBlocks);
        }

        for(int newY = y-1; newY<=y+1; newY+=2) {
            result = getNewBlock(result, x, newY, z, alreadySearchedBlocks);
        }

        for(int newZ = z-1; newZ<=z+1; newZ+=2) {
            result = getNewBlock(result, x, y, newZ, alreadySearchedBlocks);
        }

        return result;
    }


    public void setDataAtAddress(Byte address, Byte dataIn) {
        data[address] = dataIn;
    }

    public byte getDataAtAddress(Byte address){
        return data[address];
    }
}
