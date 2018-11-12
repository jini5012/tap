package com.nemosw.spigot.tap.command;

import com.nemosw.spigot.tap.LibraryLoader;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.List;

public abstract class EntitySelector
{

    private static final EntitySelector INSTANCE = LibraryLoader.load(EntitySelector.class);

    public static EntitySelector getInstance()
    {
        return INSTANCE;
    }

    public abstract <T extends Entity> List<T> matchEntities(CommandSender sender, String token, Class<? extends Entity> target);

}
