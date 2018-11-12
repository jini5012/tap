package com.nemosw.spigot.tap.profile;

import java.util.UUID;

public abstract class Profile
{
    public static Profile getProfile(String name)
    {
        return ProfileManager.INSTANCE.getProfile(name);
    }

    public static Profile getProfile(UUID id)
    {
        return ProfileManager.INSTANCE.getProfile(id);
    }

    protected Profile() {}

    public abstract UUID getUniqueId();

    public abstract String getName();

    public abstract boolean isLegacy();

    public abstract String toString();
}
