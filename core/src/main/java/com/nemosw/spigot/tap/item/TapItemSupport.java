package com.nemosw.spigot.tap.item;

import com.nemosw.spigot.tap.nbt.NBTCompound;
import org.bukkit.inventory.ItemStack;

public interface TapItemSupport
{

    TapItem getItem(int id);

    TapItem getItem(String name);

    TapItemStack newItemStack(TapItem item, int amount, int data);

    TapItemStack fromItemStack(ItemStack itemStack);

    TapItemStack loadItemStack(NBTCompound compound);

}
