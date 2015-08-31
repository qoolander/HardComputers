package com.qoolander.LaserDisplay.tileEntities;

import net.minecraft.tileentity.TileEntity;

/**
 * Created by Sam on 31/08/2015.
 */
public class TileEntityLaserEmitter extends LaserTileEntityBase {
    public boolean active;

    public float r,g,b = 0;

    public int range = 5;

    public void onPlace(){
        super.onPlace();
        active = this.worldObj.getBlockPowerInput(xCoord, yCoord, zCoord)>0;
    }

    public void onBlockUpdate(){
        super.onBlockUpdate();
        active = this.worldObj.getBlockPowerInput(xCoord, yCoord, zCoord)>0;
    }
}
