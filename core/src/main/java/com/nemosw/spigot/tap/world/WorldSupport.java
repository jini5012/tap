package com.nemosw.spigot.tap.world;

import com.nemosw.spigot.tap.LibraryLoader;
import org.bukkit.Chunk;
import org.bukkit.World;

public abstract class WorldSupport
{

    private static final WorldSupport INSTANCE = LibraryLoader.load(WorldSupport.class);

    public static WorldSupport getInstance()
    {
        return INSTANCE;
    }

    public abstract TapWorld fromWorld(World world);

    public abstract TapChunk fromChunk(Chunk chunk);

}
