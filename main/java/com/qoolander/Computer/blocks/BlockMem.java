package com.qoolander.Computer.blocks;

import com.qoolander.Computer.ComputerMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

/**
 * Created by Sam on 24/08/2015.
 */
public class BlockMem extends Block {

    protected BlockMem(String unlocalizedName) {
        super(Material.ground);
        this.setBlockName(unlocalizedName);
        this.setCreativeTab(ComputerMain.tabMyMod);
        this.setBlockTextureName(ComputerMain.MODID + ":" + unlocalizedName);
    }

    public int ByteCount;
}
