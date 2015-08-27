package com.qoolander.Computer.blocks;

import com.qoolander.Computer.ComputerMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockByteMem extends BlockMem {

    @SideOnly(Side.CLIENT)
    public IIcon sideIcon;
    public IIcon topIcon;

    public BlockByteMem(String unlocalizedName) {
        super(unlocalizedName);
        super.ByteCount = 1;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        sideIcon = reg.registerIcon(this.textureName +"_side");
        topIcon = reg.registerIcon(this.textureName +"_top");
    }


    @Override
    public IIcon getIcon(int side, int meta) {
        if(side==1||side==0){
            return topIcon;
        }else{
            return sideIcon;
        }
    }

}
