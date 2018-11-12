package com.nemosw.spigot.tap.text;

public abstract class TextComponent
{

    public static TextComponent text(String text)
    {
        return TextComponentUtils.getInstance().fromJsonLenient("{\"text\":\"" + text + "\"}");
    }

    public static TextComponentBuilder builder()
    {
        return new TextComponentBuilder();
    }

}
