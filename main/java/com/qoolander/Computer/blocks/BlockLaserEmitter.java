package com.qoolander.Computer.blocks;

import com.qoolander.Computer.ComputerMain;
import com.qoolander.Computer.TileEntities.TileEntityLaserEmitter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public class BlockLaserEmitter extends BlockLaserBase {
    public BlockLaserEmitter(String unlocalizedName){
        super(unlocalizedName);
    }

    /*@Override
    public void onNeighborBlockChange(World w, int x, int y, int z, Block p_149695_5_) {
        ((TileEntityLaserEmitter) w.getTileEntity(x, y, z)).onBlockUpdate();
    }*/

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        TileEntityLaserEmitter result = new TileEntityLaserEmitter(1,1,1);

        return result;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z){
        TileEntityLaserEmitter wire = (TileEntityLaserEmitter)world.getTileEntity(x, y, z);
        if(wire!=null) {
            float minX = 0;
            float maxX = 1;
            float minY = 0;
            float maxY = 1;
            float minZ = 0;
            float maxZ = 1;

            if(wire.active()) {
                ForgeDirection direction = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
                if (direction == ForgeDirection.SOUTH) {
                    maxZ+=wire.range;
                } else if (direction == ForgeDirection.NORTH) {
                    minZ-=wire.range;
                } else if (direction == ForgeDirection.EAST) {
                    minX+=wire.range;
                } else if (direction == ForgeDirection.WEST) {
                    minX-=wire.range;
                }
            }

            this.setBlockBounds(minX, minY,minZ, maxX, maxY, maxZ);

        }
        return AxisAlignedBB.getBoundingBox(x+this.minX, y+this.minY, z+this.minZ, x+this.maxX, y+this.maxY, z+this.maxZ);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z){
        TileEntityLaserEmitter wire = (TileEntityLaserEmitter)world.getTileEntity(x, y, z);
        if(wire!=null) {
            float minX = 0;
            float maxX = 1;
            float minY = 0;
            float maxY = 1;
            float minZ = 0;
            float maxZ = 1;

            if(wire.active()) {
                ForgeDirection direction = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
                if (direction == ForgeDirection.SOUTH) {
                    maxZ+=wire.range;
                } else if (direction == ForgeDirection.NORTH) {
                    minZ-=wire.range;
                } else if (direction == ForgeDirection.EAST) {
                    minX+=wire.range;
                } else if (direction == ForgeDirection.WEST) {
                    minX-=wire.range;
                }
            }

            this.setBlockBounds(minX, minY,minZ, maxX, maxY, maxZ);

        }
        return AxisAlignedBB.getBoundingBox(x+this.minX, y+this.minY, z+this.minZ, x+this.maxX, y+this.maxY, z+this.maxZ);
    }

    public int getRenderType(){
        return -1;
    }

    public boolean isOpaqueCube(){
        return false;
    }

    public boolean renderAsNormalBlock(){
        return false;
    }
}
