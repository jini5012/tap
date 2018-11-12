package com.nemosw.spigot.tap.util.nbt;

public abstract class NBTList
{
    public static NBTList newInstance()
    {
        return NBTManager.INSTANCE.newList();
    }

    public abstract void addIntArray(int[] value);

    public abstract void addFloat(float value);

    public abstract void addDouble(double value);

    public abstract void addString(String value);

    public abstract void addCompound(NBTCompound compound);

    public abstract int[] getIntArray(int i);

    public abstract float getFloat(int i);

    public abstract double getDouble(int i);

    public abstract String getString(int i);

    public abstract NBTCompound getCompound(int i);

    public abstract int size();

    public abstract StringBuilder toString(StringBuilder builder);

    public final String toString()
    {
        return toString(new StringBuilder()).toString();
    }


}
