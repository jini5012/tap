package com.nemosw.spigot.tap.text;

import java.util.Iterator;
import java.util.LinkedList;

public final class TextComponentBuilder
{
    private final LinkedList<Message> list = new LinkedList<>();

    TextComponentBuilder()
    {}

    public TextComponentBuilder text(String text)
    {
        this.list.add(new Message(text));

        return this;
    }

    public TextComponentObject object()
    {
        TextComponentObject object = new TextComponentObject();
        this.list.add(object);

        return object;
    }

    public TextComponent build()
    {
        return TextComponentUtils.getInstance().fromJsonLenient(toString());
    }

    @Override
    public String toString()
    {
        LinkedList<Message> list = this.list;
        int size = list.size();

        if (size == 0)
            return "";

        StringBuilder builder = new StringBuilder();

        if (size == 1)
            list.getFirst().toString(builder);
        else
        {
            builder.append('[');

            Iterator<Message> iter = list.iterator();

            while (true)
            {
                iter.next().toString(builder);

                if (!iter.hasNext())
                    break;

                builder.append(',');
            }

            builder.append(']');
        }

        return builder.toString();
    }
}
