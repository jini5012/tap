package com.nemosw.spigot.tap.profile.v1_12_R1;

import com.mojang.authlib.GameProfile;
import com.nemosw.spigot.tap.profile.Profile;
import com.nemosw.spigot.tap.profile.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;

import java.util.UUID;

public final class NMSProfileManager extends ProfileManager
{
	@Override
	protected Profile getProfile(String name)
	{
		GameProfile profile = ((CraftServer) Bukkit.getServer()).getServer().getUserCache().getProfile(name);
		
		return profile == null ? null : new NMSProfile(profile);
	}
	
	@Override
	protected Profile getProfile(UUID uniqueId)
	{
		GameProfile profile = ((CraftServer) Bukkit.getServer()).getServer().getUserCache().a(uniqueId);
		
		return profile == null ? null : new NMSProfile(profile);
	}
}
