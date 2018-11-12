package com.nemosw.spigot.tap.profile;

import com.nemosw.spigot.tap.LibraryLoader;

import java.util.UUID;

public abstract class ProfileManager
{
    static final ProfileManager INSTANCE = LibraryLoader.load(ProfileManager.class);

    protected ProfileManager() {}

    protected abstract Profile getProfile(String name);

    protected abstract Profile getProfile(UUID uniqueId);
}
