package com.qoolander.Computer.TileEntities;

import com.qoolander.Computer.IComputerComponent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

/**
 * Created by Sam on 24/08/2015.
 */
public class TileEntityWire extends TileEntity{

    /* UP, DOWN, NORTH, EAST, SOUTH, WEST*/
    public ForgeDirection[] connections = new ForgeDirection[6];

    public TileEntityWire(){
        connectedTileEntities = new ArrayList<TileEntity>();
    }

    public ArrayList<TileEntity> connectedTileEntities;

    public void updateEntity(){
        this.updateConnections();
    }

    public void updateConnections(){
        connectedTileEntities = new ArrayList<TileEntity>();
        if(isConnection(ForgeDirection.DOWN , this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord))){ connections[0] = ForgeDirection.UP; connectedTileEntities.add(this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord));}
        else connections[0] = null;
        if(isConnection(ForgeDirection.UP , this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord))){ connections[1] = ForgeDirection.DOWN; connectedTileEntities.add(this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord));}
        else connections[1] = null;
        if(isConnection(ForgeDirection.SOUTH, this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1))){ connections[2] = ForgeDirection.NORTH; connectedTileEntities.add(this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1));}
        else connections[2] = null;
        if(isConnection(ForgeDirection.WEST, this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord))){ connections[3] = ForgeDirection.EAST; connectedTileEntities.add(this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord));}
        else connections[3] = null;
        if(isConnection(ForgeDirection.NORTH, this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1))){ connections[4] = ForgeDirection.SOUTH; connectedTileEntities.add(this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1));}
        else connections[4] = null;
        if(isConnection(ForgeDirection.WEST, this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord))){ connections[5] = ForgeDirection.WEST; connectedTileEntities.add(this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord));}
        else connections[5] = null;
    }

    public boolean isConnection(ForgeDirection direction, TileEntity te){

        if(te instanceof TileEntityWire){
            return true;
        }

        if(te instanceof  ComputerTileEntityBase){
            if(((ComputerTileEntityBase) te).ConnectToWire(direction)){
                return true;
            }
        }

        return false;
    }

    public boolean isOnlyOpposites(ForgeDirection[] directions){
        ForgeDirection mainDirection = null;
        boolean isOpposite = false;

        for(int i = 0; i<directions.length; i++){
            if(mainDirection == null && directions[i]!=null){
                mainDirection = directions[i];
            }

            if(directions[i] != null && directions[i]!=mainDirection){
                if(!isOpposite(mainDirection, directions[i])) return false;
                else isOpposite = true;
            }
        }

        return isOpposite;
    }

    public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection){
        if((firstDirection.equals(ForgeDirection.NORTH) && secondDirection.equals(ForgeDirection.SOUTH)) || firstDirection.equals(ForgeDirection.SOUTH) && secondDirection.equals(ForgeDirection.NORTH)) return true;
        if((firstDirection.equals(ForgeDirection.EAST) && secondDirection.equals(ForgeDirection.WEST)) || firstDirection.equals(ForgeDirection.EAST) && secondDirection.equals(ForgeDirection.WEST)) return true;
        if((firstDirection.equals(ForgeDirection.UP) && secondDirection.equals(ForgeDirection.DOWN)) || firstDirection.equals(ForgeDirection.DOWN) && secondDirection.equals(ForgeDirection.UP)) return true;

        return false;
    }
}
