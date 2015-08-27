package com.qoolander.Computer.blocks;

import com.qoolander.Computer.ComputerMain;
import com.qoolander.Computer.TileEntities.TileEntityMar;
import com.qoolander.Computer.TileEntities.TileEntityOutput;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Sam on 25/08/2015.
 */
public class BlockOutput extends BlockComputerBase{
    IIcon topTex;
    IIcon frontTex;
    IIcon sideTex;

    public BlockOutput(String unlocalizedName){
        super(unlocalizedName);
    }



    public boolean canProvidePower()
    {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
    {
        int power = 0;
        TileEntity te = world.getTileEntity(x, y, z);
        if(te instanceof TileEntityOutput)
        {
            TileEntityOutput output = ((TileEntityOutput)te);

            if(ForgeDirection.getOrientation(side).getOpposite() == ForgeDirection.getOrientation(output.getBlockMetadata())) {
                power = output.outValue;
            }
        }
        return power;
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
        TileEntityOutput result = new TileEntityOutput();

        return result;
    }
}
