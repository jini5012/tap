package com.nemosw.spigot.tap.packet.v1_12_R1;

import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PlayerConnection;

public final class NMSPacketFixed implements NMSPacket
{

	public final Packet<?> packet;

	public NMSPacketFixed(Packet<?> packet)
	{
		this.packet = packet;
	}

	@Override
	public void send(PlayerConnection conn)
	{
		conn.sendPacket(packet);
	}

}
