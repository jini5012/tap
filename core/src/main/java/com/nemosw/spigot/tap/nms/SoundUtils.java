package com.nemosw.spigot.tap.nms;

import com.nemosw.spigot.tap.LibraryLoader;
import com.nemosw.spigot.tap.Sound;

public abstract class SoundUtils
{

    static final SoundUtils INSTANCE = LibraryLoader.load(SoundUtils.class);

    public static SoundUtils getInstance()
    {
        return INSTANCE;
    }

    public abstract Sound fromName(String name);

}
