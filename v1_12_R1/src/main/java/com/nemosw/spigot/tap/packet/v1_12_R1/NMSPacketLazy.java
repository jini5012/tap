package com.nemosw.spigot.tap.packet.v1_12_R1;

import net.minecraft.server.v1_12_R1.PacketPlayOutCustomPayload;
import net.minecraft.server.v1_12_R1.PlayerConnection;

@FunctionalInterface
public interface NMSPacketLazy extends NMSPacket
{
	
	@Override
	default void send(PlayerConnection conn)
	{
		conn.sendPacket(create());
	}
	
	PacketPlayOutCustomPayload create();

}
