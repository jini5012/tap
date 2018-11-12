package com.nemosw.spigot.tap.text;

public final class ClickEvent
{
    private enum Action
    {
        OPEN_URL("open_url"),
        OPEN_FILE("open_file"),
        RUN_COMMAND("run_command"),
        SUGGEST_COMMAND("suggest_command");

        final String name;

        Action(String name)
        {
            this.name = name;
        }
    }

    private final TextComponentObject object;

    private Action action;
    private String value;

    ClickEvent(TextComponentObject object)
    {
        this.object = object;
    }

    private void set(Action action, String value)
    {
        this.action = action;
        this.value = value;
    }

    public TextComponentObject openURL(String url)
    {
        set(Action.OPEN_URL, url);

        return this.object;
    }

    public TextComponentObject runCommand(String command)
    {
        set(Action.RUN_COMMAND, command);

        return this.object;
    }

    public TextComponentObject suggestCommand(String command)
    {
        set(Action.SUGGEST_COMMAND, command);

        return this.object;
    }

    void toString(StringBuilder builder)
    {
        builder.append("{action:").append(this.action.name).append(",value:\"").append(this.value).append("\"}");
    }
}
