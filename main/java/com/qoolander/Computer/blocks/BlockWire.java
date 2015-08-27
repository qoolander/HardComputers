package com.qoolander.Computer.blocks;

import com.qoolander.Computer.TileEntities.TileEntityWire;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * Created by Sam on 24/08/2015.
 */
public class BlockWire extends BlockContainer {

    float pixel=1F/16F;


    public BlockWire(){
        super(Material.ground);


        this.setBlockBounds(5.5F*pixel, 5.5F*pixel, 5.5F*pixel, 1-5.5F*pixel, 1-5.5F*pixel, 1-5.5F*pixel);
        this.useNeighborBrightness = true;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z){
        TileEntityWire wire = (TileEntityWire)world.getTileEntity(x, y, z);
        if(wire!=null) {
            float minX = 5.5F*pixel-(wire.connections[5]!=null?(5.5F*pixel):0);
            float maxX = 1-5.5F*pixel+(wire.connections[3]!=null?(5.5F*pixel):0);
            float minY = 5.5F*pixel-(wire.connections[1]!=null?(5.5F*pixel):0);
            float maxY = 1-5.5F*pixel+(wire.connections[0]!=null?(5.5F*pixel):0);
            float minZ = 5.5F*pixel-(wire.connections[2]!=null?(5.5F*pixel):0);
            float maxZ = 1-5.5F*pixel+(wire.connections[4]!=null?(5.5F*pixel):0);


            this.setBlockBounds(minX, minY,minZ, maxX, maxY, maxZ);

        }
        return AxisAlignedBB.getBoundingBox(x+this.minX, y+this.minY, z+this.minZ, x+this.maxX, y+this.maxY, z+this.maxZ);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z){
        TileEntityWire wire = (TileEntityWire)world.getTileEntity(x, y, z);
        if(wire!=null) {
            float minX = 5.5F*pixel-(wire.connections[5]!=null?(5.5F*pixel):0);
            float maxX = 1-5.5F*pixel+(wire.connections[3]!=null?(5.5F*pixel):0);
            float minY = 5.5F*pixel-(wire.connections[1]!=null?(5.5F*pixel):0);
            float maxY = 1-5.5F*pixel+(wire.connections[0]!=null?(5.5F*pixel):0);
            float minZ = 5.5F*pixel-(wire.connections[2]!=null?(5.5F*pixel):0);
            float maxZ = 1-5.5F*pixel+(wire.connections[4]!=null?(5.5F*pixel):0);


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

    public TileEntity createNewTileEntity(World world, int i){
        return new TileEntityWire();
    }
}
