package com.qoolander.Computer.TileEntities;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Sam on 28/08/2015.
 */
public class TileEntityRom extends ComputerTileEntityBase {
    public boolean ConnectToWire(ForgeDirection direction){
        return true;
    }

    public byte[] instructions;

    public void onPlace(){
        super.onPlace();

        this.onStart();
    }

    public void onStart(){
        instructions = new byte[20];

        instructions[0] = 0x21; //DATA r1;
        instructions[1] = 0x1;  //= 1

        instructions[2] = 0x22; //DATA r2;
        instructions[3] = 0xF;  //= 15

        instructions[4] = 0x20; //DATA r0;
        instructions[5] = 0x0;  //= 0

        instructions[6] = 0x23; //DATA r3;
        instructions[7] = 0x3;  //= 3 (output address)

        instructions[8] = 0x7F;  //Set IO address to r3
        instructions[9] = 0x78;  //set IO data to r0
        instructions[10] = -127; //(1000 0001) Add: r0 = r0 + r1
        instructions[11] = -14;  //cmp r0 with r2
        instructions[12] = 0x54; //JUMP if A greater flag is on to
        instructions[13] = 0xF; //address 16
        instructions[14] = 0x40; //JUMP to
        instructions[15] = 8;    //9

        instructions[16] = 0x20; //DATA r0;
        instructions[17] = 0x0;  //= 0
        instructions[18] = 0x40; //JUMP to
        instructions[19] = 8;    //9
    }
}
