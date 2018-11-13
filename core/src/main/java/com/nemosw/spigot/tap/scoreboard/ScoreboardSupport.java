package com.nemosw.spigot.tap.scoreboard;

import com.nemosw.spigot.tap.LibraryLoader;

public abstract class ScoreboardSupport
{
    private static final ScoreboardSupport INSTANCE = LibraryLoader.load(ScoreboardSupport.class);

    public static ScoreboardSupport getInstance()
    {
        return INSTANCE;
    }

    public abstract Scoreboard newScoreboard();
}
