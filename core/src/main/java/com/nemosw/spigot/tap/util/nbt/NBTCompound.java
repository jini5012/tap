package com.nemosw.spigot.tap.util.nbt;


import com.google.gson.JsonObject;
import com.nemosw.tools.gson.JsonIO;

import java.io.*;

/**
 * net.minecraft.server.NBTTagCompound 클래스의 래퍼 클래스입니다.
 *
 * @author tap
 */
public abstract class NBTCompound
{
    /**
     * 새로 생성된 NBTCompound를 반환합니다.
     *
     * @return 생성된 NBTCompound
     */
    public static NBTCompound newInstance()
    {
        return NBTManager.INSTANCE.newCompound();
    }

    /**
     * bytes로부터 읽어온 NBTCompound를 반환합니다.
     *
     * @param bytes
     * @return 불러온 NBTCompound
     */
    public static NBTCompound load(byte[] bytes)
    {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        return NBTManager.INSTANCE.load(in);
    }

    /**
     * InputStream으로부터 읽어온 NBTCompound를 반환합니다.
     *
     * @param in
     * @return 읽어온 NBTCompound
     */
    public static NBTCompound load(InputStream in)
    {
        return NBTManager.INSTANCE.load(in);
    }

    /**
     * File로부터 읽어온 NBTCompound를 반환합니다.
     *
     * @param file
     * @return 읽어온 NBTCompound
     */
    public static NBTCompound load(File file) throws IOException
    {
        InputStream in = null;

        try
        {
            in = new FileInputStream(file);
            return NBTManager.INSTANCE.load(in);
        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
        }
    }

    public static NBTCompound fromJson(JsonObject json)
    {
        return fromJson(json.toString());
    }

    public static NBTCompound fromJson(String linear)
    {
        return NBTManager.INSTANCE.fromJson(linear);
    }

    protected NBTCompound()
    {}

    /**
     * 키에 지정된 boolean을 가져옵니다.
     *
     * @param name 키 이름
     * @return 키에 지정된 boolean값, 지정된 값이 없을경우 null
     * @throws ClassCastException 키에 지정된 값이 boolean형태가 아닐 경우 발생합니다.
     */
    public final boolean getBoolean(String name)
    {
        return getByte(name) != 0;
    }

    /**
     * 키에 지정된 byte를 가져옵니다.
     *
     * @param name 키 이름
     * @return 키에 지정된 byte값 지정된 값이 없을경우 null
     * @throws ClassCastException 키에 지정된 값이 byte형태가 아닐 경우 발생합니다.
     */
    public abstract byte getByte(String name);

    /**
     * byte[]을 가져옵니다.
     *
     * @param name 키 이름
     * @return 값
     */
    public abstract byte[] getByteArray(String name);

    /**
     * short를 가져옵니다.
     *
     * @param name 키 이름
     * @return 값
     */
    public abstract short getShort(String name);

    /**
     * int를 가져옵니다.
     *
     * @param name 키 이름
     * @return 값
     */
    public abstract int getInt(String name);

    /**
     * int[]를 가져옵니다.
     *
     * @param name 키 이름
     * @return 값
     */
    public abstract int[] getIntArray(String name);

    /**
     * long을 가져옵니다.
     *
     * @param name 키 이름
     * @return 값
     */
    public abstract long getLong(String name);

    /**
     * float을 가져옵니다.
     *
     * @param name 키 이름
     * @return 값
     */
    public abstract float getFloat(String name);

    /**
     * double을 가져옵니다.
     *
     * @param name 키 이름
     * @return 값
     */
    public abstract double getDouble(String name);

    /**
     * string을 가져옵니다.
     *
     * @param name 키 이름
     * @return 값
     */
    public abstract String getString(String name);

    /**
     * NBTList를 가져옵니다.
     *
     * @param name 키 이름
     * @return 값
     */
    public abstract NBTList getList(String name);

    /**
     * NBTCompound를 가져옵니다.
     *
     * @param name 키 이름
     * @return 값
     */
    public abstract NBTCompound getCompound(String name);

    /**
     * NBTCompound가 비어있는지 확인합니다.
     *
     * @return 결과
     */
    public abstract boolean isEmpty();

    /**
     * 키가 있는지 확인합니다.
     *
     * @param name
     * @return
     */
    public abstract boolean contains(String name);

    public final void setBoolean(String name, boolean value)
    {
        setByte(name, value ? (byte) 1 : (byte) 0);
    }

    public abstract void setByte(String name, byte value);

    public abstract void setByteArray(String name, byte[] value);

    public abstract void setShort(String name, short value);

    public abstract void setInt(String name, int value);

    public abstract void setIntArray(String name, int[] value);

    public abstract void setLong(String name, long value);

    public abstract void setFloat(String name, float value);

    public abstract void setDouble(String name, double value);

    public abstract void setString(String name, String value);

    public abstract void setList(String name, NBTList list);

    public abstract void setCompound(String name, NBTCompound compound);

    public abstract void remove(String name);

    public abstract int hashCode();

    public abstract boolean equals(Object obj);

    public abstract NBTCompound copy();

    public abstract void save(OutputStream out) throws IOException;

    public final byte[] save()
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try
        {
            save(out);
        }
        catch (IOException impossible)
        {
            throw new AssertionError(impossible);
        }

        return out.toByteArray();
    }

    public final void save(File file) throws IOException
    {
        FileOutputStream out = null;

        try
        {
            File temp = new File(file.getPath() + ".tmp");

            out = new FileOutputStream(temp);

            save(out);
            out.close();

            file.delete();
            temp.renameTo(file);
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public final JsonObject toJson()
    {
        return (JsonObject) JsonIO.getParser().parse(toString());
    }

    public final String toString()
    {
        return toString(new StringBuilder()).toString();
    }

    public abstract StringBuilder toString(StringBuilder builder);

}
