package com.nemosw.spigot.tap.world;

import com.nemosw.spigot.tap.block.TapBlockData;

public interface TapChunk
{

    static TapChunk from(org.bukkit.Chunk chunk)
    {
        return WorldSupport.getInstance().fromChunk(chunk);
    }

    TapWorld getWorld();

    int getX();

    int getZ();

    TapBlockData getBlockData(int x, int y, int z);

}
