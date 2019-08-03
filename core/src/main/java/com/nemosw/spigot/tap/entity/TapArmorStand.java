package com.nemosw.spigot.tap.entity;

import com.nemosw.mox.math.Vector;
import org.bukkit.entity.ArmorStand;

public interface TapArmorStand extends TapLivingEntity
{

    @Override
    ArmorStand getBukkitEntity();

    Vector getHeadPos();

    void setMarker(boolean marker);

    boolean isMarker();

    void setBasePlate(boolean basePlate);

    boolean hasBasePlate();

    void setArms(boolean arms);

    boolean hasArms();

    void setSmall(boolean small);

    boolean isSmall();

    void setHeadPose(float x, float y, float z);

    Vector getHeadPose();

    void setBodyPose(float x, float y, float z);

    Vector getBodyPose();

    void setLeftArmPose(float x, float y, float z);

    Vector getLeftArmPose();

    void setRightArmPose(float x, float y, float z);

    Vector getRightArmPose();

    void setLeftLegPose(float x, float y, float z);

    Vector getLeftLegPose();

    void setRightLegPose(float x, float y, float z);

    Vector getRightLegPose();

}
