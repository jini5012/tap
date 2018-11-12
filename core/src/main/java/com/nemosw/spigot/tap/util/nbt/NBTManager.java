package com.nemosw.spigot.tap.util.nbt;

import com.nemosw.spigot.tap.LibraryLoader;

import java.io.InputStream;

public abstract class NBTManager
{
    static final NBTManager INSTANCE = LibraryLoader.load(NBTManager.class);

    protected NBTManager()
    {}

    protected abstract NBTCompound load(InputStream in);

    protected abstract NBTCompound newCompound();

    protected abstract NBTList newList();

    protected abstract NBTCompound fromJson(String linear);
}
