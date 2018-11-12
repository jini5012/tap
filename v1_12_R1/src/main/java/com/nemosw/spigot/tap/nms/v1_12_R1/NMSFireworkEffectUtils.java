package com.nemosw.spigot.tap.nms.v1_12_R1;

import com.nemosw.spigot.tap.FireworkEffect;
import com.nemosw.spigot.tap.nms.FireworkEffectUtils;

public class NMSFireworkEffectUtils extends FireworkEffectUtils
{

	@Override
	public FireworkEffect build(FireworkEffect.Builder builder)
	{
		return new NMSFireworkEffect(builder);
	}

}
