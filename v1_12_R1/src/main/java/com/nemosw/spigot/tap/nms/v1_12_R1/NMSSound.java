package com.nemosw.spigot.tap.nms.v1_12_R1;

import com.nemosw.spigot.tap.Sound;
import net.minecraft.server.v1_12_R1.SoundEffect;

public class NMSSound extends Sound
{
	
	public final SoundEffect soundEffect;
	
	protected NMSSound(String name, SoundEffect soundEffect)
	{
		super(name);
		
		this.soundEffect = soundEffect;
	}

}
