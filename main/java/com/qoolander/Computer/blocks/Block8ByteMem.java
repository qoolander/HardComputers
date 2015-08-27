package com.qoolander.Computer.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class Block8ByteMem extends BlockMem {

    @SideOnly(Side.CLIENT)
    public IIcon sideIcon;
    public IIcon topIcon;

    public Block8ByteMem(String unlocalizedName) {
        super(unlocalizedName);
        super.ByteCount = 8;
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
