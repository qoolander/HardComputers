package com.qoolander.Computer.TileEntities;

import com.qoolander.Computer.IComputerComponent;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Sam on 26/08/2015.
 */
public class ComputerTileEntityBase extends TileEntity implements IComputerComponent {

    public void onStart(){}
    public void onShutDown(){}

    public boolean ConnectToWire(ForgeDirection direction){
        return false;
    }

    public byte getAddress(){
        return address;
    }

    public byte setByteAddress(ArrayList<Byte> bytes){
        byte largestByte = 0;

        for(int i = 0; i<bytes.size();i++){
            if(bytes.get(i)+1>largestByte){
                largestByte=bytes.get(i);
                largestByte++;
            }
        }

        if(largestByte==0){
            address=0;
            return 0;
        }

        if(!((largestByte+=1) >=255)){
            address = --largestByte;
            return largestByte;
        }

        throw new IndexOutOfBoundsException();
    }

    private byte address;

    public ArrayList<TileEntityWire> connectedWires;

    public void onBlockUpdate(){
        connectedWires = getWireConnections();
    }

    public void onPlace() {
        connectedWires = getWireConnections();
    }

    public ArrayList<TileEntityWire> getWireConnections(){
        ArrayList<TileEntityWire> result = new ArrayList<TileEntityWire>();

        if(ConnectToWire(ForgeDirection.UP)){
            if(worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof TileEntityWire) {
                result.add(((TileEntityWire) worldObj.getTileEntity(xCoord, yCoord + 1, zCoord)));
            }
        }
        if(ConnectToWire(ForgeDirection.DOWN)){
            if(worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof TileEntityWire) {
                result.add(((TileEntityWire) worldObj.getTileEntity(xCoord, yCoord - 1, zCoord)));
            }
        }
        if(ConnectToWire(ForgeDirection.NORTH)){
            if(worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof TileEntityWire) {
                result.add(((TileEntityWire) worldObj.getTileEntity(xCoord, yCoord, zCoord+1)));
            }
        }
        if(ConnectToWire(ForgeDirection.SOUTH)){
            if(worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof TileEntityWire) {
                result.add(((TileEntityWire) worldObj.getTileEntity(xCoord, yCoord, zCoord - 1)));
            }
        }
        if(ConnectToWire(ForgeDirection.EAST)){
            if(worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof TileEntityWire) {
                result.add(((TileEntityWire) worldObj.getTileEntity(xCoord+1, yCoord, zCoord)));
            }
        }
        if(ConnectToWire(ForgeDirection.WEST)){
            if(worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof TileEntityWire) {
                result.add(((TileEntityWire) worldObj.getTileEntity(xCoord-1, yCoord, zCoord)));
            }
        }

        return result;
    }

}
