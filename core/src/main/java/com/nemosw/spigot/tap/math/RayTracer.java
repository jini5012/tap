package com.nemosw.spigot.tap.math;

import com.nemosw.mox.math.Vector;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.function.Predicate;

public interface RayTracer
{

    World getWorld();

    RayTracer setWorld(World world);

    Vector getFrom(Vector v);

    Vector getFrom();

    RayTracer setFrom(double x, double y, double z);

    RayTracer setFrom(Vector v);

    Vector getTo(Vector v);

    Vector getTo();

    RayTracer setTo(double x, double y, double z);

    RayTracer setTo(Vector v);

    RayTraceResult setToRayTraceBlock(int option);

    double getLength();

    BoundingBox toBox();

    <T extends Entity> List<T> getEntitiesInBox(Entity exclusion, Predicate<Entity> selector);

    RayTraceResult rayTraceBlock(int option);

    RayTraceResult calculate(BoundingBox box);

    RayTraceResult calculate(Entity entity, double expand);

    RayTraceResult rayTraceEntity(Entity exclusion, double expand, Predicate<Entity> selector);

    List<? extends RayTraceResult> rayTraceEntities(Entity exclusion, double expand, Predicate<Entity> selector);

}
