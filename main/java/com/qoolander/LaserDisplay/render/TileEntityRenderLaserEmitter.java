package com.qoolander.LaserDisplay.render;

import com.qoolander.Computer.TileEntities.TileEntityWire;
import com.qoolander.LaserDisplay.tileEntities.TileEntityLaserEmitter;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

/**
 * Created by Sam on 31/08/2015.
 */
public class TileEntityRenderLaserEmitter extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double translationX, double translationY, double translationZ, float f){
        GL11.glTranslated(translationX, translationY, translationZ);
        GL11.glDisable(GL11.GL_LIGHTING);

        TileEntityLaserEmitter myTe = ((TileEntityLaserEmitter) tileEntity);

        if(myTe.active) drawLaser(ForgeDirection.getOrientation(myTe.getBlockMetadata()), myTe.range, myTe.r, myTe.b, myTe.g);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glTranslated(-translationX, -translationY, -translationZ);
    }

    private void drawLaser(ForgeDirection direction, int range, float r, float g, float b){


        GL11.glColor3f(r,g,b);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        {
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            if(direction.equals(ForgeDirection.UP)){

            }else if(direction.equals(ForgeDirection.DOWN)){
                GL11.glRotatef(180, 1, 0, 0);
            }
            else if(direction.equals(ForgeDirection.SOUTH)){
                GL11.glRotatef(90, 1, 0, 0);
            }
            else if(direction.equals(ForgeDirection.NORTH)){
                GL11.glRotatef(270, 1, 0, 0);
            }else if(direction.equals(ForgeDirection.WEST)) {
                GL11.glRotatef(90, 0,0, 1);
            }else if(direction.equals(ForgeDirection.EAST)) {
                GL11.glRotatef(270, 0,0, 1);
            }
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

            tessellator.addVertex(0.66, 1, 0.33);
            tessellator.addVertex(0.33, 1, 0.33);
            tessellator.addVertex(0.33, 1+range, 0.33);
            tessellator.addVertex(0.66, 1+range, 0.33);

            tessellator.addVertex(0.66, 1+range, 0.66);
            tessellator.addVertex(0.33, 1+range, 0.66);
            tessellator.addVertex(0.33, 1, 0.66);
            tessellator.addVertex(0.66, 1, 0.66);

            tessellator.addVertex(0.66, 1, 0.66);
            tessellator.addVertex(0.66, 1, 0.33);
            tessellator.addVertex(0.66, 1+range, 0.33);
            tessellator.addVertex(0.66, 1+range, 0.66);

            tessellator.addVertex(0.33, 1+range, 0.66);
            tessellator.addVertex(0.33, 1+range, 0.33);
            tessellator.addVertex(0.33, 1, 0.33);
            tessellator.addVertex(0.33, 1, 0.66);


        }
        tessellator.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if(direction.equals(ForgeDirection.UP)){

        }else if(direction.equals(ForgeDirection.DOWN)){
            GL11.glRotatef(-180, 1, 0, 0);
        }
        else if(direction.equals(ForgeDirection.SOUTH)){
            GL11.glRotatef(-90, 1, 0, 0);
        }
        else if(direction.equals(ForgeDirection.NORTH)){
            GL11.glRotatef(-270, 1, 0, 0);
        }else if(direction.equals(ForgeDirection.WEST)) {
            GL11.glRotatef(-90, 0,0, 1);
        }else if(direction.equals(ForgeDirection.EAST)) {
            GL11.glRotatef(-270, 0,0, 1);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    }
}
