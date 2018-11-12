package com.nemosw.spigot.tap.nms.v1_12_R1;

import com.nemosw.spigot.tap.Sound;
import com.nemosw.spigot.tap.nms.SoundUtils;
import net.minecraft.server.v1_12_R1.MinecraftKey;
import net.minecraft.server.v1_12_R1.SoundEffect;

public class NMSSoundUtils extends SoundUtils
{
	
	@Override
	public Sound fromName(String name)
	{
		MinecraftKey key = new MinecraftKey(name);
		SoundEffect soundEffect = SoundEffect.a.get(key);
		
		if (soundEffect == null)
			soundEffect = new SoundEffect(key);
		
		return new NMSSound(name, soundEffect);
	}
	
}
