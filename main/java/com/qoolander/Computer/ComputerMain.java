package com.qoolander.Computer;

import com.qoolander.Computer.TileEntities.TileEntityCore;
import com.qoolander.Computer.TileEntities.TileEntityMar;
import com.qoolander.Computer.TileEntities.TileEntityOutput;
import com.qoolander.Computer.TileEntities.TileEntityWire;
import com.qoolander.Computer.blocks.*;
import com.qoolander.Computer.proxy.CommonProxy;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import net.minecraft.util.Vec3;

import java.util.ArrayList;

@Mod(modid = com.qoolander.Computer.ComputerMain.MODID, version = com.qoolander.Computer.ComputerMain.VERSION)
public class ComputerMain
{
    @SidedProxy(clientSide="com.qoolander.Computer.proxy.ClientProxy", serverSide="com.qoolander.Computer.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final String MODID = "computer";
    public static final String VERSION = "1.0";

    public static Block byteMem;
    public static Block eightByteMem;
    public static Block mar;
    public static Block blockWire;
    public static Block blockOutput;
    public static Block blockCore;

    public static CreativeTabs tabMyMod = new CreativeTabsMyMod("Computer");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        byteMem = new BlockByteMem("byteMem");
        eightByteMem = new Block8ByteMem("8byteMem");
        mar = new BlockMar("mar");
        blockWire = new BlockWire().setBlockName("blockWire").setCreativeTab(tabMyMod);
        blockOutput = new BlockOutput("blockOutput");
        blockCore = new BlockCore("blockCore");
        GameRegistry.registerBlock(byteMem, byteMem.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(mar, mar.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockWire, blockWire.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(eightByteMem, eightByteMem.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockOutput, blockOutput.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockCore, blockCore.getUnlocalizedName().substring(5));
        GameRegistry.registerTileEntity(TileEntityMar.class, "mar_tile_entity");
        GameRegistry.registerTileEntity(TileEntityWire.class, "computer_wire");
        GameRegistry.registerTileEntity(TileEntityOutput.class, "output_tile_entity");
        GameRegistry.registerTileEntity(TileEntityCore.class, "core_tile_entity");

        proxy.registerProxies();
    }

    public static boolean Vec3Contains(ArrayList<Vec3> array, Vec3 vec3){
        for(int i = 0; i<array.size(); i++){
            if (Vec3Equals(array.get(i), vec3)){
                return true;
            }
        }

        return false;
    }

    public static boolean Vec3Equals(Vec3 a, Vec3 b){
        return a.xCoord==b.xCoord && a.yCoord==b.yCoord && a.zCoord==b.zCoord;
    }
}

