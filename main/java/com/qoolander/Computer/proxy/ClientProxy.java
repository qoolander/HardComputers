package com.qoolander.Computer.proxy;

import com.qoolander.Computer.TileEntities.TileEntityWire;
import com.qoolander.Computer.render.TileEntityRenderWire;
import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * Created by Sam on 24/08/2015.
 */
public class ClientProxy extends CommonProxy{
    public void registerProxies(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWire.class, new TileEntityRenderWire());
    }
}
