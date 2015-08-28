package com.qoolander.Computer.blocks;

import com.qoolander.Computer.TileEntities.TileEntityRom;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRom extends BlockComputerBase{
    public BlockRom(String unlocalizedName){
        super(unlocalizedName);
    }

    public TileEntity createNewTileEntity(World world, int meta){ return new TileEntityRom(); }
}
