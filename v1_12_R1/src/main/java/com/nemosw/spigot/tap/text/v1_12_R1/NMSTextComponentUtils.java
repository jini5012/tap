package com.nemosw.spigot.tap.text.v1_12_R1;

import com.nemosw.spigot.tap.text.TextComponent;
import com.nemosw.spigot.tap.text.TextComponentUtils;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import org.bukkit.Achievement;
import org.bukkit.Statistic;
import org.bukkit.advancement.Advancement;
import org.bukkit.craftbukkit.v1_12_R1.CraftStatistic;
import org.bukkit.craftbukkit.v1_12_R1.advancement.CraftAdvancement;

@SuppressWarnings("deprecation")
public class NMSTextComponentUtils extends TextComponentUtils
{

    @Override
    public String getAchievementName(Achievement achievement)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getAdvancementName(Advancement advancement) {return ((CraftAdvancement) advancement).getHandle().getName().toString();}

    @Override
    public String getStatisticName(Statistic stat)
    {
        return CraftStatistic.getNMSStatistic(stat).name;
    }

    @Override
    public TextComponent jsonToComponent(String json)
    {
        return new NMSTextComponent(ChatSerializer.a(json));
    }

    @Override
    public TextComponent fromJsonLenient(String json)
    {
        return new NMSTextComponent(ChatSerializer.b(json));
    }

}
