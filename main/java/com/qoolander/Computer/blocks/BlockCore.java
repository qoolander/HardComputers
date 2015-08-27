package com.qoolander.Computer.blocks;

import com.qoolander.Computer.ComputerMain;
import com.qoolander.Computer.TileEntities.TileEntityCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Sam on 25/08/2015.
 */
public class BlockCore extends BlockComputerBase {
    public BlockCore(String unlocalizedName){
        super(unlocalizedName);
    }

    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntityCore();
    }
}
