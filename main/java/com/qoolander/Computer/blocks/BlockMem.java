package com.qoolander.Computer.blocks;

import com.qoolander.Computer.ComputerMain;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Sam on 24/08/2015.
 */
public class BlockMem extends Block {

    protected BlockMem(String unlocalizedName) {
        super(Material.ground);
        this.setBlockName(unlocalizedName);
        this.setCreativeTab(ComputerMain.tabComputer);
        this.setBlockTextureName(ComputerMain.MODID + ":" + unlocalizedName);
    }

    public int ByteCount;
}
