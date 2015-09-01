package com.qoolander.Computer.TileEntities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import java.util.Random;

/**
 * Created by Sam on 31/08/2015.
 */
public class TileEntityLaserEmitter extends TileEntityLaserBase {

    public TileEntityLaserEmitter(float _r,float _g, float _b){
        r=_r;
        g=_g;
        b=_b;
    }

    public float r,g,b = 0;

    public int range = 5;

    public void onPlace(){
    }

    public boolean active(){
        return this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
    }
}
