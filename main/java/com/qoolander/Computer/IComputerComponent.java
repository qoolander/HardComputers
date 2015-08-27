package com.qoolander.Computer;

import com.qoolander.Computer.TileEntities.TileEntityWire;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

/**
 * Created by Sam on 24/08/2015.
 */
public interface IComputerComponent {

    boolean ConnectToWire(ForgeDirection side);

    public byte getAddress();

    public ArrayList<TileEntityWire> getWireConnections();

}
