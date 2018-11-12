package com.nemosw.spigot.tap.entity;

import org.bukkit.entity.LivingEntity;

public interface TapLivingEntity extends TapEntity
{

    LivingEntity getBukkitEntity();

    float getEyeHeight();

    double getHealth();

}
