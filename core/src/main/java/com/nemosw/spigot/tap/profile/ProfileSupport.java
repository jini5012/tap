package com.nemosw.spigot.tap.profile;

import com.nemosw.spigot.tap.LibraryLoader;

import java.util.UUID;

public abstract class ProfileSupport
{

    private static final ProfileSupport INSTANCE = LibraryLoader.load(ProfileSupport.class);

    public static ProfileSupport getInstance()
    {
        return INSTANCE;
    }

    protected ProfileSupport() {}

    public abstract Profile getProfile(String name);

    public abstract Profile getProfile(UUID uniqueId);

}
