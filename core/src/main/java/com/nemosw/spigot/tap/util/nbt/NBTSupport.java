package com.nemosw.spigot.tap.util.nbt;

import com.nemosw.spigot.tap.LibraryLoader;

import java.io.InputStream;

public abstract class NBTSupport
{

    private static final NBTSupport INSTANCE = LibraryLoader.load(NBTSupport.class);

    public static NBTSupport getInstance()
    {
        return INSTANCE;
    }

    public abstract NBTCompound loadCompound(InputStream in);

    public abstract NBTCompound loadCompound(byte[] bytes);

    public abstract NBTCompound fromJsonString(String json);

    public abstract NBTCompound newCompound();

    public abstract NBTList newList();

}
