package com.qoolander.LaserDisplay.proxy;


import com.qoolander.LaserDisplay.render.TileEntityRenderLaserEmitter;
import com.qoolander.LaserDisplay.tileEntities.TileEntityLaserEmitter;
import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * Created by Sam on 24/08/2015.
 */
public class ClientProxy extends CommonProxy {
    public void registerProxies(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaserEmitter.class, new TileEntityRenderLaserEmitter());
    }
}
