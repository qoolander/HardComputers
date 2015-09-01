package com.qoolander.Computer.proxy;

import com.qoolander.Computer.TileEntities.TileEntityLaserEmitter;
import com.qoolander.Computer.TileEntities.TileEntityWire;
import com.qoolander.Computer.render.TileEntityRenderLaserEmitter;
import com.qoolander.Computer.render.TileEntityRenderWire;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
    public void registerProxies(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWire.class, new TileEntityRenderWire());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaserEmitter.class, new TileEntityRenderLaserEmitter());
    }
}
