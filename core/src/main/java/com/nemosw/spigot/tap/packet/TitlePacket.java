package com.nemosw.spigot.tap.packet;

import com.nemosw.spigot.tap.text.TextComponent;

public interface TitlePacket
{

    Packet reset();

    default Packet title(String text)
    {
        return title(TextComponent.text(text));
    }

    Packet title(TextComponent text);

    default Packet subtitle(String text)
    {
        return subtitle(TextComponent.text(text));
    }

    Packet subtitle(TextComponent text);

    Packet show(int fadeIn, int stay, int fadeOut);

    default Packet compound(String title, String subtitle, int fadeIn, int stay, int fadeOut)
    {
        return compound(TextComponent.text(title == null ? "" : title), subtitle == null ? null : TextComponent.text(subtitle), fadeIn, stay, fadeOut);
    }

    Packet compound(TextComponent title, TextComponent subtitle, int fadeIn, int stay, int fadeOut);

}
