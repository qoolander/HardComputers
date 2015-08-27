package com.qoolander.Computer.blocks;

import com.qoolander.Computer.ComputerMain;
import com.qoolander.Computer.TileEntities.ComputerTileEntityBase;
import com.qoolander.Computer.TileEntities.TileEntityMar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by Sam on 26/08/2015.
 */
public class BlockComputerBase extends BlockContainer {

    public BlockComputerBase(String unlocalizedName){
        super(Material.rock);
        this.setBlockName(unlocalizedName);
        this.setCreativeTab(ComputerMain.tabMyMod);
        this.setBlockTextureName(ComputerMain.MODID + ":" + unlocalizedName);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack)
    {
        int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        ((ComputerTileEntityBase)world.getTileEntity(x, y, z)).onPlace();
    }

    @Override
    public void onNeighborBlockChange(World w, int x, int y, int z, Block p_149695_5_) {
        ((ComputerTileEntityBase) w.getTileEntity(x, y, z)).onBlockUpdate();
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        ComputerTileEntityBase result = new ComputerTileEntityBase();

        return result;
    }

}
