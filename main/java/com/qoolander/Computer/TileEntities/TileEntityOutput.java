package com.qoolander.Computer.TileEntities;

import com.qoolander.Computer.IComputerComponent;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Sam on 25/08/2015.
 */
public class TileEntityOutput extends ComputerTileEntityBase {
    public TileEntityOutput(){

    }

    public int outValue = 0;

    public boolean ConnectToWire(ForgeDirection direction){
        return true;
    }

    public void setData(byte data){
        outValue = (int)data;
        outValue = outValue&0xFF;
    }
}
