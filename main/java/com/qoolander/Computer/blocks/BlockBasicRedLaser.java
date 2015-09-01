package com.qoolander.Computer.blocks;

import com.qoolander.Computer.TileEntities.TileEntityLaserEmitter;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Sam on 01/09/2015.
 */
public class BlockBasicRedLaser extends BlockLaserEmitter{
    public BlockBasicRedLaser(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        TileEntityLaserEmitter result = new TileEntityLaserEmitter(1,0,0);

        return result;
    }
}
