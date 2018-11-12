package com.nemosw.spigot.tap.text;

import com.nemosw.spigot.tap.item.TapItemStack;
import com.nemosw.spigot.tap.util.nbt.NBTCompound;
import org.bukkit.Achievement;

public final class HoverEvent
{
    private enum Action
    {
        SHOW_TEXT("show_text"),
        SHOW_ACHIEVEMENT("show_achievement"),
        SHOW_ITEM("show_item");

        final String name;

        Action(String name)
        {
            this.name = name;
        }
    }

    private final TextComponentObject object;

    private Action action;
    private Value value;

    HoverEvent(TextComponentObject object)
    {
        this.object = object;
    }

    private void set(Action action, Value value)
    {
        this.action = action;
        this.value = value;
    }

    public TextComponentObject showText(String text)
    {
        set(Action.SHOW_TEXT, new ValueText(text));

        return this.object;
    }

    public TextComponentObject showAchievement(Achievement ach)
    {
        set(Action.SHOW_ACHIEVEMENT, new ValueText(TextComponentUtils.getInstance().getAchievementName(ach)));

        return this.object;
    }

    public TextComponentObject showItem(TapItemStack item)
    {
        set(Action.SHOW_ITEM, new ValueItem(item));

        return this.object;
    }

    void toString(StringBuilder builder)
    {
        this.value.toString(builder.append("{action:").append(this.action.name).append(",value:"));
        builder.append('}');
    }

    private static abstract class Value
    {
        public abstract void toString(StringBuilder builder);
    }

    private static class ValueText extends Value
    {
        final String text;

        ValueText(String text)
        {
            this.text = text;
        }

        @Override
        public void toString(StringBuilder builder)
        {
            builder.append('\"').append(this.text).append('\"');
        }
    }

    private static class ValueItem extends Value
    {
        final TapItemStack item;

        ValueItem(TapItemStack item)
        {
            this.item = item;
        }

        @Override
        public void toString(StringBuilder builder)
        {
            TapItemStack item = this.item;

            builder.append("\"{id:").append("\\\"").append(item.getItem().getTextureId()).append("\\\",Count:1");

            int data = item.getData();
            if (data > 0)
                builder.append(",Data:").append(data);

            NBTCompound tag = item.getTag();

            if (tag != null)
            {
                builder.append(",tag:");
                int offset = builder.length();
                tag.toString(builder);

                while (offset < builder.length())
                {
                    if (builder.charAt(offset) == '\"')
                    {
                        builder.insert(offset, '\\');
                        offset += 2;
                    }
                    else
                        offset++;
                }
            }

            builder.append("}\"");
        }
    }
}
