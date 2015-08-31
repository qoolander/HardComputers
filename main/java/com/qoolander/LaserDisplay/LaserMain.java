package com.qoolander.LaserDisplay;

import com.qoolander.LaserDisplay.blocks.BlockLaserEmitter;
import com.qoolander.LaserDisplay.proxy.CommonProxy;
import com.qoolander.LaserDisplay.tileEntities.TileEntityLaserEmitter;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;

@Mod(modid = LaserMain.MODID, version = LaserMain.VERSION)
public class LaserMain
{
    public static final String MODID = "laserdisplay";
    public static final String VERSION = "1.0";

    public static Block laserEmitter;

    public static CreativeTabs tabLaser = new CreativeTabLaser("Computer");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        laserEmitter = new BlockLaserEmitter("laserEmitter");

        GameRegistry.registerBlock(laserEmitter, laserEmitter.getUnlocalizedName().substring(5));

        GameRegistry.registerTileEntity(TileEntityLaserEmitter.class, "laser_tile_entity");

        proxy.registerProxies();
    }

    @SidedProxy(clientSide="com.qoolander.LaserDisplay.proxy.ClientProxy", serverSide="com.qoolander.LaserDisplay.proxy.CommonProxy")
    public static CommonProxy proxy;
}