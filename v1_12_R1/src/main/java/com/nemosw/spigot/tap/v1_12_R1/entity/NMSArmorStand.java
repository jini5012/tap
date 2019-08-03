package com.nemosw.spigot.tap.v1_12_R1.entity;

import com.nemosw.mox.math.Vector;
import com.nemosw.spigot.tap.entity.TapArmorStand;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityArmorStand;
import net.minecraft.server.v1_12_R1.Vector3f;
import org.bukkit.entity.ArmorStand;

public class NMSArmorStand extends NMSLivingEntity implements TapArmorStand
{

    private final EntityArmorStand armorStand;

    NMSArmorStand(Entity entity)
    {
        super(entity);

        this.armorStand = (EntityArmorStand) entity;
    }

    private static Vector toVector(Vector3f v)
    {
        return new Vector(v.getX(), v.getY(), v.getZ());
    }

    @Override
    public EntityArmorStand getHandle()
    {
        return this.armorStand;
    }

    @Override
    public ArmorStand getBukkitEntity()
    {
        return (ArmorStand) this.armorStand.getBukkitEntity();
    }

    @Override
    public Vector getHeadPos()
    {
        return toVector(armorStand.headPose);
    }

    @Override
    public void setHeadPose(float x, float y, float z)
    {
        this.armorStand.setHeadPose(new Vector3f(x, y, z));
    }

    @Override
    public void setBodyPose(float x, float y, float z)
    {
        this.armorStand.setBodyPose(new Vector3f(x, y, z));
    }

    @Override
    public void setLeftArmPose(float x, float y, float z)
    {
        this.armorStand.setLeftArmPose(new Vector3f(x, y, z));
    }

    @Override
    public void setRightArmPose(float x, float y, float z)
    {
        this.armorStand.setRightArmPose(new Vector3f(x, y, z));
    }

    @Override
    public void setLeftLegPose(float x, float y, float z)
    {
        this.armorStand.setLeftLegPose(new Vector3f(x, y, z));
    }

    @Override
    public void setRightLegPose(float x, float y, float z)
    {
        this.armorStand.setRightLegPose(new Vector3f(x, y, z));
    }

    @Override
    public boolean isMarker()
    {
        return armorStand.isMarker();
    }

    @Override
    public void setMarker(boolean marker)
    {
        armorStand.setMarker(marker);
    }

    @Override
    public void setBasePlate(boolean basePlate)
    {
        armorStand.setBasePlate(basePlate);
    }

    @Override
    public boolean hasBasePlate()
    {
        return armorStand.hasBasePlate();
    }

    @Override
    public void setArms(boolean arms)
    {
        armorStand.setArms(arms);
    }

    @Override
    public boolean hasArms()
    {
        return armorStand.hasArms();
    }

    @Override
    public boolean isSmall()
    {
        return armorStand.isSmall();
    }

    @Override
    public void setSmall(boolean small)
    {
        armorStand.setSmall(small);
    }

    @Override
    public Vector getHeadPose()
    {
        return toVector(armorStand.headPose);
    }

    @Override
    public Vector getBodyPose()
    {
        return toVector(armorStand.bodyPose);
    }

    @Override
    public Vector getLeftArmPose()
    {
        return toVector(armorStand.leftArmPose);
    }

    @Override
    public Vector getRightArmPose()
    {
        return toVector(armorStand.rightArmPose);
    }

    @Override
    public Vector getLeftLegPose()
    {
        return toVector(armorStand.leftLegPose);
    }

    @Override
    public Vector getRightLegPose()
    {
        return toVector(armorStand.rightLegPose);
    }
}
