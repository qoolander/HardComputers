package com.qoolander.Computer.blocks;

import com.qoolander.Computer.TileEntities.TileEntityMar;
import com.qoolander.Computer.TileEntities.TileEntityNVPS;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Sam on 31/08/2015.
 */
public class BlockNVPS extends BlockComputerBase {
    IIcon topTex;
    IIcon frontTex;
    IIcon sideTex;

    public BlockNVPS(String unlocalizedName){
        super(unlocalizedName);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        frontTex = reg.registerIcon(this.textureName +"_front");
        topTex = reg.registerIcon(this.textureName +"_top");
        sideTex = reg.registerIcon(this.textureName +"_side");
    }

    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.topTex : (par1 == 0 ? this.topTex : (par1 != par2 ? this.sideTex : this.frontTex));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        TileEntityNVPS result = new TileEntityNVPS();

        return result;
    }
}
