package com.nemosw.spigot.tap.packet;

import com.nemosw.mox.math.Vector;
import com.nemosw.mox.math.VectorSpace;
import com.nemosw.spigot.tap.*;

public interface EffectPacket
{

    Packet effect(Effect effect, int x, int y, int z, int data);

    Packet explosion(double x, double y, double z, float radius, VectorSpace records, Vector push);

    Packet particle(Particle particle, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float particleData, int particleCount, int... data);

    Packet firework(FireworkEffect firework, double x, double y, double z);

    Packet sound(Sound sound, SoundCategory category, double x, double y, double z, float volume, float pitch);

    Packet thunderbolt(double x, double y, double z);

}
