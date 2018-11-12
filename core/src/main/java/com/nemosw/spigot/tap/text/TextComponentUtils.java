package com.nemosw.spigot.tap.text;

import com.nemosw.spigot.tap.LibraryLoader;
import org.bukkit.Achievement;
import org.bukkit.Statistic;
import org.bukkit.advancement.Advancement;

public abstract class TextComponentUtils
{

    private static TextComponentUtils INSTANCE = LibraryLoader.load(TextComponentUtils.class);

    public static TextComponentUtils getInstance()
    {
        return INSTANCE;
    }

    @Deprecated
    public abstract String getAchievementName(Achievement achievement);

    public abstract String getAdvancementName(Advancement advancement);

    public abstract String getStatisticName(Statistic stat);

    public abstract TextComponent jsonToComponent(String json);

    public abstract TextComponent fromJsonLenient(String json);

}
