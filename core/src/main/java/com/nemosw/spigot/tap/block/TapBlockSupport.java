package com.nemosw.spigot.tap.block;

import com.nemosw.spigot.tap.LibraryLoader;
import org.bukkit.World;
import org.bukkit.block.Block;

public abstract class TapBlockSupport
{

    private static final TapBlockSupport INSTANCE = LibraryLoader.load(TapBlockSupport.class);

    public static TapBlockSupport getInstance()
    {
        return INSTANCE;
    }

    public abstract TapBlock getBlock(int id);

    public abstract TapBlock getBlock(String name);

    public final TapBlockData getBlockData(Block block)
    {
        return getBlockData(block.getWorld(), block.getX(), block.getY(), block.getZ());
    }

    public abstract TapBlockData getBlockData(World world, int x, int y, int z);

}
