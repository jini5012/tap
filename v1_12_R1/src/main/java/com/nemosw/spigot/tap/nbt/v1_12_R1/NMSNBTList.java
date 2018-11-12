package com.nemosw.spigot.tap.nbt.v1_12_R1;

import com.nemosw.spigot.tap.util.nbt.NBTCompound;
import com.nemosw.spigot.tap.util.nbt.NBTList;
import net.minecraft.server.v1_12_R1.*;

public final class NMSNBTList extends NBTList
{

	private final NBTTagList list;

	public NMSNBTList(NBTTagList list)
	{
		this.list = list;
	}

	@Override
	public void addCompound(NBTCompound compound)
	{
		this.list.add(((NMSNBTCompound) compound).getHandle());
	}

	@Override
	public void addDouble(double value)
	{
		this.list.add(new NBTTagDouble(value));
	}

	@Override
	public void addFloat(float value)
	{
		this.list.add(new NBTTagFloat(value));
	}

	@Override
	public void addIntArray(int[] value)
	{
		this.list.add(new NBTTagIntArray(value));
	}

	@Override
	public void addString(String value)
	{
		this.list.add(new NBTTagString(value));
	}

	@Override
	public NMSNBTCompound getCompound(int i)
	{
		return new NMSNBTCompound(this.list.get(i));
	}

	@Override
	public double getDouble(int i)
	{
		return this.list.f(i);
	}

	@Override
	public float getFloat(int i)
	{
		return this.list.g(i);
	}

	@Override
	public int[] getIntArray(int i)
	{
		return this.list.d(i);
	}

	@Override
	public String getString(int i)
	{
		return this.list.getString(i);
	}

	@Override
	public int size()
	{
		return this.list.size();
	}

	@Override
	public StringBuilder toString(StringBuilder builder)
	{
		return NBTWriters.write(this.list, builder);
	}

	public NBTTagList getHandle()
    {
        return list;
    }

}
