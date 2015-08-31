package com.qoolander.LaserDisplay;

import com.qoolander.Computer.ComputerMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Sam on 31/08/2015.
 */
public class CreativeTabLaser extends CreativeTabs {
    public CreativeTabLaser(String tabLabel)
    {
        super(tabLabel);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return Item.getItemFromBlock(LaserMain.laserEmitter);
    }
}
