package com.nemosw.spigot.tap.text;

import org.bukkit.ChatColor;

public enum TextColor
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

    TextColor()
    {
        this.name = name().toLowerCase();
    }

    private static final TextColor[] values = values();

    public static TextColor getByChatColor(ChatColor color)
    {
        int ordinal = color.ordinal();

        return ordinal < values.length ? values[ordinal] : null;
    }
}
