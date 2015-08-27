package com.qoolander.Computer.render;

import com.qoolander.Computer.ComputerMain;
import com.qoolander.Computer.TileEntities.TileEntityWire;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

/**
 * Created by Sam on 24/08/2015.
 */
public class TileEntityRenderWire extends TileEntitySpecialRenderer {

    ResourceLocation texture = new ResourceLocation(ComputerMain.MODID, "textures/model/pipe.png");
    boolean drawInside = true;
    float pixel = 1F/16F;
    float texturePixel = 1F/32F;

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double translationX, double translationY, double translationZ, float f){
        GL11.glTranslated(translationX, translationY, translationZ);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.bindTexture(texture);
        if (drawInside) GL11.glDisable(GL11.GL_CULL_FACE);

        TileEntityWire wire = (TileEntityWire) tileEntity;
        if(!wire.isOnlyOpposites(wire.connections)) {
            drawCore(tileEntity);

            for(int i = 0; i < wire.connections.length; i++){
                if(wire.connections[i] != null){
                    drawConnection(wire.connections[i]);
                }
            }
        }else{
            for(int i = 0; i < wire.connections.length; i++){
                if(wire.connections[i] != null){
                    drawStraight(wire.connections[i]);
                    break;
                }
            }
        }

        if (drawInside) GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glTranslated(-translationX, -translationY, -translationZ);
    }

    public void drawStraight(ForgeDirection direction){
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        {
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            if(direction.equals(ForgeDirection.UP) || direction.equals(ForgeDirection.DOWN)){

            }
            else if(direction.equals(ForgeDirection.SOUTH) || direction.equals(ForgeDirection.NORTH)){
                 GL11.glRotatef(90, 1, 0, 0);
            }else if(direction.equals(ForgeDirection.WEST) || direction.equals(ForgeDirection.EAST)) {
                GL11.glRotatef(90, 0,0, 1);
            }
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);


            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 0, 1-11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 1-11 * pixel / 2, 26 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 1-11 * pixel / 2, 26 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 0, 1-11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(11 * pixel / 2, 0, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 26 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2, 1, 11 * pixel / 2, 26 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2, 0, 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(11 * pixel / 2, 0, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 26 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2, 1, 11 * pixel / 2, 26 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2, 0, 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(1-11 * pixel / 2, 0, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel / 2, 1, 11 * pixel / 2, 26 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2, 1, 1-11 * pixel / 2,26 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2,0,1-11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);


            tessellator.addVertexWithUV(11 * pixel/2, 0, 1-11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel/2, 1, 1-11 * pixel / 2, 26 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 26 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 0, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);


        }
        tessellator.draw();


        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        if(direction.equals(ForgeDirection.UP) || direction.equals(ForgeDirection.DOWN)){

        }
        else if(direction.equals(ForgeDirection.SOUTH) || direction.equals(ForgeDirection.NORTH)){
            GL11.glRotatef(-90, 1, 0, 0);
        }else if(direction.equals(ForgeDirection.WEST) || direction.equals(ForgeDirection.EAST)) {
            GL11.glRotatef(-90, 0,0, 1);
        }
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    }

    public void drawConnection(ForgeDirection direction){



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


            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 1-11 * pixel / 2, 5 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 1-11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 1-11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1-11 * pixel / 2, 1-11 * pixel / 2, 5 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(11 * pixel / 2, 1-11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2, 1, 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2, 1-11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(11 * pixel / 2, 1-11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2, 1, 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2, 1-11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 0 * texturePixel);

            tessellator.addVertexWithUV(1-11 * pixel / 2, 1-11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel / 2, 1, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2, 1, 1-11 * pixel / 2,10 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel/2,1-11 * pixel / 2,1-11 * pixel / 2, 5 * texturePixel, 0 * texturePixel);


            tessellator.addVertexWithUV(11 * pixel/2, 1-11 * pixel / 2, 1-11 * pixel / 2, 5 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel/2, 1, 1-11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1-11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 5 * texturePixel);



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

    public void drawCore(TileEntity tileEntity){
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        {
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 1-11 * pixel / 2, 5 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1-11 * pixel / 2, 1-11 * pixel / 2, 5 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1-11 * pixel / 2, 1-11 * pixel / 2, 0 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 1-11 * pixel / 2, 0 * texturePixel, 5 * texturePixel);

            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1-11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel / 2, 1-11 * pixel / 2, 1-11 * pixel / 2, 0 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel / 2, 11 * pixel / 2, 1-11 * pixel / 2, 0 * texturePixel, 5 * texturePixel);

            tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1-11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1-11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel, 5 * texturePixel);

            tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 1-11 * pixel / 2, 5 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1-11 * pixel / 2, 1-11 * pixel / 2, 5 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1-11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel, 5 * texturePixel);

            tessellator.addVertexWithUV(1-11 * pixel / 2, 1-11 * pixel / 2, 1-11 * pixel / 2, 5 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel / 2, 1-11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1-11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 1-11 * pixel / 2, 1-11 * pixel / 2, 0 * texturePixel, 5 * texturePixel);

            tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 1-11 * pixel / 2, 5 * texturePixel, 5 * texturePixel);
            tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel, 0 * texturePixel);
            tessellator.addVertexWithUV(1-11 * pixel / 2, 11 * pixel / 2, 1-11 * pixel / 2, 0 * texturePixel, 5 * texturePixel);

        }
        tessellator.draw();
    }
}
