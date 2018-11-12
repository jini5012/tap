package com.nemosw.spigot.tap.block;

public interface TapBlock
{

    TapBlock AIR = TapBlockSupport.getInstance().getBlock("air");

    String getTextureId();

    int getId();

    TapBlockData getBlockData();

    TapBlockData getBlockData(int data);

}
