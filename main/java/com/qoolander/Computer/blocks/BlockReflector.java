package com.qoolander.Computer.blocks;

import com.qoolander.Computer.TileEntities.TileEntityLaserEmitter;
import com.qoolander.Computer.TileEntities.TileEntityLaserReflector;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Sam on 01/09/2015.
 */
public class BlockReflector extends BlockLaserBase {
    public BlockReflector(String unlocalizedName){
        super(unlocalizedName);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        TileEntityLaserReflector result = new TileEntityLaserReflector();

        return result;
    }
}
