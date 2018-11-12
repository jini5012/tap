package com.nemosw.spigot.tap;

import com.nemosw.spigot.tap.nms.FireworkEffectUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.Color;

import java.util.ArrayList;

public interface FireworkEffect
{

    static Builder builder()
    {
        return new Builder();
    }

    final class Builder
    {
        private boolean trail;

        private boolean flicker;

        private Type type = Type.SMALL_BALL;

        private final ArrayList<Color> colors = new ArrayList<>();

        private final ArrayList<Color> fadeColors = new ArrayList<>();

        public Builder trail(boolean trail)
        {
            this.trail = trail;

            return this;
        }

        public Builder flicker(boolean flicker)
        {
            this.flicker = flicker;

            return this;
        }

        public Builder type(Type type)
        {
            this.type = type == null ? Type.SMALL_BALL : type;

            return this;
        }

        private static void notNull(Color color)
        {
            Validate.notNull(color, "Color cannot be null");
        }

        private static void addColor(ArrayList<Color> colorList, Color color)
        {
            notNull(color);

            colorList.clear();
            colorList.add(color);
        }

        private static void addColor(ArrayList<Color> colorList, Color[] colors)
        {
            colorList.clear();

            for (Color color : colors)
            {
                notNull(color);

                colorList.add(color);
            }
        }

        public Builder color(Color color)
        {
            addColor(this.colors, color);

            return this;
        }

        public Builder color(Color... colors)
        {
            addColor(this.colors, colors);

            return this;
        }

        public Builder fadeColor(Color color)
        {
            addColor(this.fadeColors, color);

            return this;
        }

        public Builder fadeColor(Color... colors)
        {
            addColor(this.fadeColors, colors);

            return this;
        }

        public boolean isFlicker()
        {
            return this.flicker;
        }

        public boolean isTrail()
        {
            return this.trail;
        }

        public Type getType()
        {
            return this.type;
        }

        private static int[] getColors(ArrayList<Color> colorList)
        {
            int size = colorList.size();
            int[] colors = new int[size];

            for (int i = 0; i < size; i++)
                colors[i] = colorList.get(i).asRGB();

            return colors;
        }

        public int[] getColors()
        {
            return getColors(this.colors);
        }

        public int[] getFadeColors()
        {
            return getColors(this.fadeColors);
        }

        public FireworkEffect build()
        {
            return FireworkEffectUtils.getInstance().build(this);
        }
    }

    enum Type
    {
        SMALL_BALL(0), LARGE_BALL(1), STAR(2), CREEPER(3), BURST(4);

        final byte id;

        Type(int id)
        {
            this.id = (byte) id;
        }

        public final byte getId()
        {
            return this.id;
        }

        public static Type getById(byte b)
        {
            Type[] types = values();
            for (int i = 0; i < types.length; i++)
                if (types[i].id == b)
                    return types[i];
            return null;
        }
    }

}
