package com.nemosw.spigot.tap.item;

import com.nemosw.spigot.tap.LibraryLoader;
import com.nemosw.spigot.tap.util.nbt.NBTCompound;
import org.bukkit.inventory.ItemStack;

public abstract class TapItemSupport
{

    private static final TapItemSupport INSTANCE = LibraryLoader.load(TapItemSupport.class);

    public static TapItemSupport getInstance()
    {
        return INSTANCE;
    }

    protected TapItemSupport()
    {}

    public abstract TapItem getItem(int id);

    public abstract TapItem getItem(String name);

    public abstract TapItemStack newItemStack(TapItem item, int amount, int data);

    public abstract TapItemStack fromItemStack(ItemStack itemStack);

    public abstract TapItemStack loadItemStack(NBTCompound compound);

}
