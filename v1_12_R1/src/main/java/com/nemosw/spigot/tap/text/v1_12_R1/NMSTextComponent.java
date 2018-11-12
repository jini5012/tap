package com.nemosw.spigot.tap.text.v1_12_R1;

import com.nemosw.spigot.tap.text.TextComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;

public class NMSTextComponent extends TextComponent
{
	
	public final IChatBaseComponent component;

	public NMSTextComponent(IChatBaseComponent component)
	{
		this.component = component;
	}
	
}
