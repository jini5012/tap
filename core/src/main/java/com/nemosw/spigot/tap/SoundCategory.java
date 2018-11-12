package com.nemosw.spigot.tap;

public enum SoundCategory
{
    MASTER, MUSIC, RECORDS, WEATHER, BLOCKS, HOSTILE, NEUTRAL, PLAYERS, AMBIENT, VOICE;

    public static void main(String[] args)
    {
        for (SoundCategory s : values())
        {
            System.out.println("categories[SoundCategory." + s.name() + ".ordinal()] = " + s.name());
        }
    }
}
