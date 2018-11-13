package com.nemosw.spigot.tap.text;

import com.nemosw.spigot.tap.item.TapItem;
import com.nemosw.spigot.tap.item.TapItemStack;
import org.bukkit.Achievement;
import org.bukkit.Statistic;

import java.util.ArrayList;

public final class TextComponentObject extends Message
{
    enum Type
    {
        TEXT("text"),
        TRANSLATE("translate");

        final String name;

        Type(String name)
        {
            this.name = name;
        }
    }

    Type type;

    String text;

    String insertion;

    TextColor textColor;

    boolean bold;

    boolean strikethrough;

    boolean underlined;

    boolean italic;

    boolean obfuscated;

    ClickEvent clickEvent;

    HoverEvent hoverEvent;

    ArrayList<Message> extras;

    TextComponentObject() {}

    public TextComponentObject text(String text)
    {
        this.type = Type.TEXT;
        this.text = text;

        return this;
    }

    public TextComponentObject translate(String name)
    {
        this.type = Type.TRANSLATE;
        this.text = name;

        return this;
    }

    public TextComponentObject translate(Achievement ach)
    {
        return translate(TextComponentUtils.getInstance().getAchievementName(ach));
    }

    public TextComponentObject translate(Statistic stat)
    {
        return translate(TextComponentUtils.getInstance().getStatisticName(stat));
    }

    public TextComponentObject translate(TapItemStack item)
    {
        return translate(item.getUnlocalizedName());
    }

    public TextComponentObject translate(TapItem item)
    {
        return translate(item.getUnlocalizedName());
    }

    public TextComponentObject name(TapItemStack item)
    {
        String displayName = item.getDisplayName();

        if (displayName != null)
            text(displayName);
        else
            translate(item.getUnlocalizedName());

        return this;
    }

    public TextComponentObject insertion(String insertion)
    {
        this.insertion = insertion;

        return this;
    }

    public TextComponentObject color(TextColor textColor)
    {
        this.textColor = textColor;

        return this;
    }

    public TextComponentObject bold()
    {
        this.bold = true;

        return this;
    }

    public TextComponentObject strikethrough()
    {
        this.strikethrough = true;

        return this;
    }

    public TextComponentObject underlined()
    {
        this.underlined = true;

        return this;
    }

    public TextComponentObject italic()
    {
        this.italic = true;

        return this;
    }

    public TextComponentObject obfuscated()
    {
        this.obfuscated = true;

        return this;
    }

    public ClickEvent clickEvent()
    {
        return this.clickEvent = new ClickEvent(this);
    }

    public HoverEvent hoverEvent()
    {
        return this.hoverEvent = new HoverEvent(this);
    }

    private ArrayList<Message> extras()
    {
        if (this.extras == null)
            this.extras = new ArrayList<>();

        return this.extras;
    }

    public TextComponentObject extra(String text)
    {
        extras().add(new Message(text));

        return this;
    }

    public TextComponentObject extra()
    {
        TextComponentObject extra = new TextComponentObject();
        extras().add(extra);

        return extra;
    }

    @Override
    void toString(StringBuilder builder)
    {
        builder.append('{').append(this.type.name).append(":\"").append(this.text).append('\"');

        if (this.textColor != null)
            builder.append(",textColor:").append(this.textColor.name);

        if (this.insertion != null)
            builder.append(",insertion:").append(this.insertion);

        if (this.bold)
            builder.append(",bold:true");

        if (this.strikethrough)
            builder.append(",strikethrough:true");

        if (this.underlined)
            builder.append(",underlined:true");

        if (this.italic)
            builder.append(",italic:true");

        if (this.obfuscated)
            builder.append(",obfuscated:true");

        if (this.clickEvent != null)
            this.clickEvent.toString(builder.append(",clickEvent:"));

        if (this.hoverEvent != null)
            this.hoverEvent.toString(builder.append(",hoverEvent:"));

        if (this.extras != null)
        {
            builder.append(",extra:[");

            ArrayList<Message> extras = this.extras;
            int size = extras.size();
            int i = 0;

            while (true)
            {
                extras.get(i++).toString(builder);

                if (i >= size)
                    break;
                else
                    builder.append(',');
            }

            builder.append(']');
        }

        builder.append('}');
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        toString(builder);

        return builder.toString();
    }
}
