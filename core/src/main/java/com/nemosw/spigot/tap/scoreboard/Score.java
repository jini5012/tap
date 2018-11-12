package com.nemosw.spigot.tap.scoreboard;

public interface Score
{
    Objective getObjective();

    String getName();

    int getScore();

    void setScore(int score);

    void unregister();
}
