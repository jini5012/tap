package com.nemosw.spigot.tap.packet;

import com.nemosw.spigot.tap.ChatType;
import com.nemosw.spigot.tap.text.TextComponent;

public interface InfoPacket
{

    Packet chat(TextComponent text, ChatType type);

    default Packet chat(String text, ChatType type)
    {
        return chat(TextComponent.text(text), type);
    }

    Packet playerListHeaderFooter(TextComponent header, TextComponent footer);

    default Packet playerListHeaderFooter(String header, String footer)
    {
        return playerListHeaderFooter(TextComponent.text(header), TextComponent.text(footer));
    }

}
