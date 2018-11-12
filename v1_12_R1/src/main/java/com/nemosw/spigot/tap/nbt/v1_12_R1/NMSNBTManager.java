package com.nemosw.spigot.tap.nbt.v1_12_R1;

import com.nemosw.spigot.tap.util.nbt.NBTCompound;
import com.nemosw.spigot.tap.util.nbt.NBTList;
import com.nemosw.spigot.tap.util.nbt.NBTManager;
import net.minecraft.server.v1_12_R1.*;

import java.io.IOException;
import java.io.InputStream;

public final class NMSNBTManager extends NBTManager
{
	@Override
	public NBTCompound load(InputStream in)
	{
		try
		{
			return new NMSNBTCompound(NBTCompressedStreamTools.a(in));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public NMSNBTCompound newCompound()
	{
		return new NMSNBTCompound(new NBTTagCompound());
	}

	@Override
	public NBTList newList()
	{
		return new NMSNBTList(new NBTTagList());
	}
	
	@Override
	public NMSNBTCompound fromJson(String linear)
	{
		try
		{
			return new NMSNBTCompound(MojangsonParser.parse(linear));
		}
		catch (MojangsonParseException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
}
