package com.nemosw.spigot.tap.text;

import org.bukkit.ChatColor;

public enum Color
{
    BLACK,
    DARK_BLUE,
    DARK_GREEN,
    DARK_AQUA,
    DARK_RED,
    DARK_PURPLE,
    GOLD,
    GRAY,
    DARK_GRAY,
    BLUE,
    GREEN,
    AQUA,
    RED,
    LIGHT_PURPLE,
    YELLOW,
    WHITE;

    final String name;

    Color()
    {
        this.name = name().toLowerCase();
    }

    private static final Color[] values = values();

    public static Color getByChatColor(ChatColor color)
    {
        int ordinal = color.ordinal();

        return ordinal < values.length ? values[ordinal] : null;
    }
}
