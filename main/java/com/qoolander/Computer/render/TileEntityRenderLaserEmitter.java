package com.qoolander.Computer.render;

import com.qoolander.Computer.ComputerMain;
import com.qoolander.Computer.TileEntities.TileEntityLaserEmitter;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

/**
 * Created by Sam on 31/08/2015.
 */
public class TileEntityRenderLaserEmitter extends TileEntitySpecialRenderer {

    ResourceLocation texture = new ResourceLocation(ComputerMain.MODID, "textures/blocks/Laser.png");

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double translationX, double translationY, double translationZ, float f){
        this.bindTexture(texture);

        GL11.glTranslated(translationX, translationY, translationZ);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);

        drawCube();

        TileEntityLaserEmitter myTe = (TileEntityLaserEmitter) tileEntity;
        if(myTe.active())
            drawLaser(ForgeDirection.getOrientation(myTe.getBlockMetadata()), myTe.range, myTe.r, myTe.b, myTe.g);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glTranslated(-translationX, -translationY, -translationZ);
    }

    private void drawCube(){
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        {
            tessellator.addVertexWithUV(1, 0, 1, 1, 0);
            tessellator.addVertexWithUV(1, 0, 0, 0, 0);
            tessellator.addVertexWithUV(1, 1, 0, 0, 1);
            tessellator.addVertexWithUV(1, 1, 1, 1, 1);

            tessellator.addVertexWithUV(0, 1, 1, 1, 1);
            tessellator.addVertexWithUV(0, 1, 0, 0, 1);
            tessellator.addVertexWithUV(0, 0, 0, 0, 0);
            tessellator.addVertexWithUV(0, 0, 1, 1, 0);

            tessellator.addVertexWithUV(1, 0, 0, 0, 1);
            tessellator.addVertexWithUV(0, 0, 0, 0, 0);
            tessellator.addVertexWithUV(0, 1, 0, 1, 0);
            tessellator.addVertexWithUV(1, 1, 0, 1, 1);

            tessellator.addVertexWithUV(1, 1, 1, 1, 1);
            tessellator.addVertexWithUV(0, 1, 1, 1, 0);
            tessellator.addVertexWithUV(0, 0, 1, 0, 0);
            tessellator.addVertexWithUV(1, 0, 1, 0, 1);

            tessellator.addVertexWithUV(1, 1, 0, 1, 0);
            tessellator.addVertexWithUV(0, 1, 0, 1, 0);
            tessellator.addVertexWithUV(0, 1, 1, 0, 1);
            tessellator.addVertexWithUV(1, 1, 1, 0, 1);

            tessellator.addVertexWithUV(1, 0, 1, 0, 1);
            tessellator.addVertexWithUV(0, 0, 1, 0, 1);
            tessellator.addVertexWithUV(0, 0, 0, 1, 0);
            tessellator.addVertexWithUV(1, 0, 0, 1, 0);
        }
        tessellator.draw();
    }

    private void drawLaser(ForgeDirection direction, int range, float r, float g, float b){
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

        int bright = 0xF0;
        int brightX = bright % 65536;
        int brightY = bright / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, brightX, brightY);

        GL11.glBegin(GL11.GL_TRIANGLES);
        {
            GL11.glColor4f(r, g, b, 1);

            //Inner Laser
            GL11.glVertex3f(0.55F, 1, 0.44F);
            GL11.glVertex3f(0.44F, 1, 0.44F);
            GL11.glVertex3f(0.55F, 1 + range, 0.44F);
            GL11.glVertex3f(0.55F, 1+range, 0.44F);
            GL11.glVertex3f(0.44F, 1, 0.44F);
            GL11.glVertex3f(0.44F, 1+range, 0.44F);

            GL11.glVertex3f(0.55F, 1+range, 0.55F);
            GL11.glVertex3f(0.44F, 1, 0.55F);
            GL11.glVertex3f(0.55F, 1, 0.55F);
            GL11.glVertex3f(0.44F, 1+range, 0.55F);
            GL11.glVertex3f(0.44F, 1, 0.55F);
            GL11.glVertex3f(0.55F, 1+range, 0.55F);

            GL11.glVertex3f(0.55F, 1, 0.55F);
            GL11.glVertex3f(0.55F, 1, 0.44F);
            GL11.glVertex3f(0.55F, 1+range, 0.55F);
            GL11.glVertex3f(0.55F, 1+range, 0.55F);
            GL11.glVertex3f(0.55F, 1, 0.44F);
            GL11.glVertex3f(0.55F, 1+range, 0.44F);

            GL11.glVertex3f(0.44F, 1+range, 0.55F);
            GL11.glVertex3f(0.44F, 1, 0.44F);
            GL11.glVertex3f(0.44F, 1, 0.55F);
            GL11.glVertex3f(0.44F, 1+range, 0.55F);
            GL11.glVertex3f(0.44F, 1+range, 0.44F);
            GL11.glVertex3f(0.44F, 1, 0.44F);

            GL11.glColor4f(r*255, g, b, 0.4F);
            //Outer glow
            GL11.glVertex3f(0.66F, 1, 0.33F);
            GL11.glVertex3f(0.33F, 1, 0.33F);
            GL11.glVertex3f(0.66F, 1 + range, 0.33F);
            GL11.glVertex3f(0.66F, 1+range, 0.33F);
            GL11.glVertex3f(0.33F, 1, 0.33F);
            GL11.glVertex3f(0.33F, 1+range, 0.33F);

            GL11.glVertex3f(0.66F, 1+range, 0.66F);
            GL11.glVertex3f(0.33F, 1, 0.66F);
            GL11.glVertex3f(0.66F, 1, 0.66F);
            GL11.glVertex3f(0.33F, 1+range, 0.66F);
            GL11.glVertex3f(0.33F, 1, 0.66F);
            GL11.glVertex3f(0.66F, 1+range, 0.66F);

            GL11.glVertex3f(0.66F, 1, 0.66F);
            GL11.glVertex3f(0.66F, 1, 0.33F);
            GL11.glVertex3f(0.66F, 1+range, 0.66F);
            GL11.glVertex3f(0.66F, 1+range, 0.66F);
            GL11.glVertex3f(0.66F, 1, 0.33F);
            GL11.glVertex3f(0.66F, 1+range, 0.33F);

            GL11.glVertex3f(0.33F, 1+range, 0.66F);
            GL11.glVertex3f(0.33F, 1, 0.33F);
            GL11.glVertex3f(0.33F, 1, 0.66F);
            GL11.glVertex3f(0.33F, 1+range, 0.66F);
            GL11.glVertex3f(0.33F, 1+range, 0.33F);
            GL11.glVertex3f(0.33F, 1, 0.33F);



        }
        GL11.glEnd();

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
