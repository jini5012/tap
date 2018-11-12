package com.nemosw.spigot.tap.nms;

import com.nemosw.spigot.tap.FireworkEffect;
import com.nemosw.spigot.tap.LibraryLoader;

public abstract class FireworkEffectUtils
{

    private static final FireworkEffectUtils INSTANCE = LibraryLoader.load(FireworkEffectUtils.class);

    public static FireworkEffectUtils getInstance()
    {
        return INSTANCE;
    }

    public abstract FireworkEffect build(FireworkEffect.Builder builder);

}
