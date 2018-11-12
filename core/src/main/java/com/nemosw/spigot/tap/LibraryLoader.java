package com.nemosw.spigot.tap;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.ConstructorUtils;
import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * NMS Library의 로드를 돕는 클래스입니다.
 * <p>
 * x_x_Rx 로 패키지 이름을 정하면 해당 클래스가 로딩됩니다.
 *
 * @author tap
 */
public final class LibraryLoader
{

    @SuppressWarnings("unchecked")
    public static <T> T load(String packageName, String className, Class<T> type, Object... initargs)
    {
        Class[] parameterTypes = ClassUtils.toClass(initargs);

        String nmsClassName = packageName + '.' + getBukkitVersion() + '.' + className;

        try
        {
            Class<? extends T> nmsClass = Class.forName(nmsClassName, true, type.getClassLoader()).asSubclass(type);
            Constructor<?> constructor = ConstructorUtils.getMatchingAccessibleConstructor(nmsClass, parameterTypes);

            if (constructor == null)
                throw new UnsupportedOperationException(type.getName() + " does not have Constructor for [" + StringUtils.join(parameterTypes, ", ") + "]");

            return (T) constructor.newInstance(initargs);
        }
        catch (ClassNotFoundException e)
        {
            throw new UnsupportedOperationException(type.getName() + " does not support this version (" + getMinecraftVersion() + ")", e);
        }
        catch (IllegalAccessException e)
        {
            throw new UnsupportedOperationException(type.getName() + " constructor is not visible");
        }
        catch (InstantiationException e)
        {
            throw new UnsupportedOperationException(type.getName() + " is abstract class");
        }
        catch (InvocationTargetException e)
        {
            throw new UnsupportedOperationException(type.getName() + " has an error occurred while creating the instance", e);
        }
    }

    public static <T> T load(Class<T> type, Object... initargs)
    {
        String name = type.getSimpleName();

        if (name.startsWith("Tap"))
            name = name.substring(3);

        return load(type.getPackage().getName(), "NMS" + name, type, initargs);
    }

    /**
     * 버킷 버전을 반환합니다.
     *
     * @return 버킷 버전
     */
    public static String getBukkitVersion()
    {
        Matcher matcher = Pattern.compile("v\\d+_\\d+_R\\d+").matcher(Bukkit.getServer().getClass().getPackage().getName());

        return matcher.find() ? matcher.group() : null;
    }

    /**
     * 마인크래프트 버전을 반환합니다.
     *
     * @return 마인크래프트 버전
     */
    public static String getMinecraftVersion()
    {
        Matcher matcher = Pattern.compile("(\\(MC: )([\\d\\.]+)(\\))").matcher(Bukkit.getVersion());

        return matcher.find() ? matcher.group(2) : null;
    }

    private LibraryLoader()
    {}
}
