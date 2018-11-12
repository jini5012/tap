package com.nemosw.spigot.tap.math;

import com.nemosw.mox.math.Vector;
import com.nemosw.spigot.tap.LibraryLoader;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.function.Predicate;

public abstract class MathSupport
{

    private static final MathSupport INSTANCE = LibraryLoader.load(MathSupport.class);

    public static MathSupport getInstance()
    {
        return INSTANCE;
    }

    public abstract BoundingBox getBoundingBox(Entity entity);

    public abstract BoundingBox newBoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);

    public abstract RayTraceResult rayTraceBlock(World world, Vector from, Vector to, int options);

    public abstract RayTraceResult rayTraceEntity(World world, Entity entity, Vector from, Vector to, double expand, Predicate<Entity> selector);

    public abstract RayTraceResult rayTrace(World world, Entity entity, Vector from, Vector to, int options, double expand, Predicate<Entity> selector);

    public abstract RayTracer newRayTraceCalculator(World world, Vector from, Vector to);

}
