package com.nemosw.spigot.tap.packet.v1_12_R1;

import com.nemosw.spigot.tap.item.TapItem;
import com.nemosw.spigot.tap.item.v1_12_R1.NMSItem;
import com.nemosw.spigot.tap.packet.ItemPacket;
import net.minecraft.server.v1_12_R1.PacketPlayOutSetCooldown;

public class NMSItemPacket implements ItemPacket
{

	@Override
	public NMSPacket cooldown(TapItem item, int cooldownTicks)
	{
		return new NMSPacketFixed(new PacketPlayOutSetCooldown(((NMSItem) item).getHandle(), cooldownTicks));
	}

}
