package com.nemosw.spigot.tap.scoreboard;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public interface Scoreboard
{
    Objective registerObjective(String name);

    Objective unregisterObjective(String name);

    Objective getObjective(String name);

    Objective[] getObjectives();

    void clearSlot(DisplaySlot slot);

    Set<Score> resetScores(String name);

    Team registerTeam(String name);

    Team unregisterTeam(String name);

    Team getTeam(String name);

    Team getEntryTeam(String name);

    Team[] getTeams();

    void registerPlayer(Player player);

    void unregisterPlayer(Player player);

    void unregisterAllPlayers();

    List<Player> getRegisteredPlayers();

    void clear();
}
