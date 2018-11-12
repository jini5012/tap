package com.nemosw.spigot.tap.scoreboard;

public interface Objective
{
    Scoreboard getScoreboard();

    String getName();

    String getDisplayName();

    void setDisplayName(String displayName);

    DisplaySlot getDisplaySlot();

    void setDisplaySlot(DisplaySlot slot);

    Score getScore(String name);

    Score registerScore(String name);

    Score registerScore(String name, int score);

    Score unregisterScore(String name);

    void clear();

    void unregister();
}
