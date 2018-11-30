package com.nemosw.spigot.tap.world;

import com.nemosw.spigot.tap.LibraryLoader;
import com.nemosw.spigot.tap.nbt.NBTCompound;
import org.bukkit.World;

public abstract class Schematic
{
    private static final Schematic INSTANCE = LibraryLoader.load(Schematic.class);

    public static Schematic getInstance()
    {
        return INSTANCE;
    }

    public abstract NBTCompound save(World world, int x, int y, int z, int width, int height, int length);

    public abstract void load(World world, int x, int y, int z, NBTCompound schematic);

    protected static int size(int width, int height, int length)
    {
        long size = (long) width * height * length;

        if (size > Integer.MAX_VALUE)
            throw new IllegalArgumentException("schematic size is too large!");

        return (int) size;
    }
}
