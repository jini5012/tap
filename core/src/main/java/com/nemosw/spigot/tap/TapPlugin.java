package com.nemosw.spigot.tap;

import com.nemosw.spigot.tap.command.CommandManager;
import com.nemosw.spigot.tap.debug.CommandDebug;
import com.nemosw.spigot.tap.debug.DebugManager;
import com.nemosw.spigot.tap.init.Tap;
import org.bukkit.plugin.java.JavaPlugin;

public final class TapPlugin extends JavaPlugin
{

    private static TapPlugin instance;

    public static TapPlugin getInstance()
    {
        return instance;
    }

    private CommandManager commandManager;

    private DebugManager debugManager;

    @Override
    public void onLoad()
    {
        Tap.init();
    }

    @Override
    public void onEnable()
    {
        setupCommands();
        setupDebugger();

        instance = this;
    }

    private void setupCommands()
    {
        commandManager = new CommandManager().addHelp("help").register(getCommand("tap"));
    }

    private void setupDebugger()
    {
        debugManager = new DebugManager();
        commandManager.addComponent("debug", new CommandDebug(debugManager));
    }

    public CommandManager getCommandManager()
    {
        return commandManager;
    }

    public DebugManager getDebugManager()
    {
        return debugManager;
    }

}
