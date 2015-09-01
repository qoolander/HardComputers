package com.qoolander.Computer.blocks;

import com.qoolander.Computer.TileEntities.TileEntityLaserEmitter;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Sam on 01/09/2015.
 */
public class BlockBasicBlueLaser extends BlockLaserEmitter{
    public BlockBasicBlueLaser(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        TileEntityLaserEmitter result = new TileEntityLaserEmitter(0,0,1);

        return result;
    }
}
