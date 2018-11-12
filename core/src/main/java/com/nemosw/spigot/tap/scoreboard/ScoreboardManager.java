package com.nemosw.spigot.tap.scoreboard;

import com.nemosw.spigot.tap.LibraryLoader;

public abstract class ScoreboardManager
{
    private static final ScoreboardManager INSTANCE = LibraryLoader.load(ScoreboardManager.class);

    public static ScoreboardManager getInstance()
    {
        return INSTANCE;
    }

    public abstract Scoreboard newScoreboard();
}
