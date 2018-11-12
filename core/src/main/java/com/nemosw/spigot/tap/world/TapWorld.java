package com.nemosw.spigot.tap.world;

import com.nemosw.spigot.tap.block.TapBlockData;

public interface TapWorld
{

    static TapWorld from(org.bukkit.World world)
    {
        return WorldSupport.getInstance().fromWorld(world);
    }

    org.bukkit.World getWorld();

    TapChunk getChunk(int x, int z);

    TapBlockData getBlock(int x, int y, int z);

    default boolean setBlock(int x, int y, int z, TapBlockData blockData)
    {
        return setBlock(x, y, z, blockData, true);
    }

    boolean setBlock(int x, int y, int z, TapBlockData block, boolean applyPhysics);

}
