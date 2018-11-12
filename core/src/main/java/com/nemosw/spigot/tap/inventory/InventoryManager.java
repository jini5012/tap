package com.nemosw.spigot.tap.inventory;

import com.nemosw.spigot.tap.LibraryLoader;
import org.bukkit.inventory.Inventory;

public abstract class InventoryManager
{

    static InventoryManager INSTANCE = LibraryLoader.load(InventoryManager.class);

    protected InventoryManager()
    {}

    protected abstract TapInventory fromInventory(Inventory inventory);

}
