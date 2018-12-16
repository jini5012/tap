package com.nemosw.spigot.tap.v1_12_R1.math;

import com.google.common.collect.Lists;
import com.nemosw.mox.math.Vector;
import com.nemosw.spigot.tap.math.BoundingBox;
import com.nemosw.spigot.tap.math.RayTracer;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.nemosw.spigot.tap.v1_12_R1.math.NMSMathSupport.*;


@SuppressWarnings({"unchecked", "StaticPseudoFunctionalStyleMethod", "ConstantConditions", "Convert2MethodRef", "Guava"})
public final class NMSRayTracer implements RayTracer
{

    private World world;

    private Vec3D from, to;

    NMSRayTracer(World world, Vec3D from, Vec3D to)
    {
        this.world = world;
        this.from = from;
        this.to = to;
    }

    @Override
    public org.bukkit.World getWorld()
    {
        return world.getWorld();
    }

    @Override
    public NMSRayTracer setWorld(org.bukkit.World world)
    {
        if (world == null)
            throw new NullPointerException("World cannot be null");

        this.world = ((CraftWorld) world).getHandle();

        return this;
    }

    @Override
    public Vector getFrom(Vector v)
    {
        return copyToVector(from, v);
    }

    @Override
    public Vector getFrom()
    {
        return toVector(from);
    }

    @Override
    public NMSRayTracer setFrom(double x, double y, double z)
    {
        from = new Vec3D(x, y, z);

        return this;
    }

    @Override
    public NMSRayTracer setFrom(Vector v)
    {
        from = toVec3D(v);

        return this;
    }

    @Override
    public Vector getTo(Vector v)
    {
        return copyToVector(to, v);
    }

    @Override
    public Vector getTo()
    {
        return toVector(to);
    }

    @Override
    public NMSRayTracer setTo(double x, double y, double z)
    {
        to = new Vec3D(x, y, z);

        return this;
    }

    @Override
    public NMSRayTracer setTo(Vector v)
    {
        to = toVec3D(v);

        return this;
    }

    @Override
    public NMSRayTraceResult setToRayTraceBlock(int option)
    {
        NMSRayTraceResult result = rayTraceBlock(option);

        if (result != null)
        {
            Vec3D pos = result.getHandle().pos;
            setTo(pos.x, pos.y, pos.z);
        }

        return result;
    }

    @Override
    public double getLength()
    {
        Vec3D from = this.from, to = this.to;

        double x = from.x - to.x;
        double y = from.y - to.y;
        double z = from.z - to.z;

        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public NMSBoundingBox toBox()
    {
        return new NMSBoundingBox(new AxisAlignedBB(from.x, from.y, from.z, to.x, to.y, to.z));
    }

    @Override
    public <T extends org.bukkit.entity.Entity> List<T> getEntitiesInBox(org.bukkit.entity.Entity exclusion, Predicate<org.bukkit.entity.Entity> selector)
    {
        Vec3D from = this.from, to = this.to;
        List<Entity> entities = NMSMathSupport.getEntitiesInBox(world, unwrapEntity(exclusion), new AxisAlignedBB(from.x, from.y, from.z, to.x, to.y, to.z), toNMSSelector(selector));

        return (List<T>) Lists.transform(entities, entity -> entity.getBukkitEntity());
    }

    @Override
    public NMSRayTraceResult rayTraceBlock(int option)
    {
        return wrapResult(NMSMathSupport.rayTraceBlock(world, from, to, option));
    }

    @Override
    public NMSRayTraceResult calculate(BoundingBox box)
    {
        return wrapResult(((NMSBoundingBox) box).getHandle().b(from, to));
    }

    @Override
    public NMSRayTraceResult calculate(org.bukkit.entity.Entity entity, double expand)
    {
        return wrapResult(((CraftEntity) entity).getHandle().getBoundingBox().grow(expand, expand, expand).b(from, to));
    }

    private List<Entity> getNMSEntities(org.bukkit.entity.Entity exclusion, double expand, com.google.common.base.Predicate<Entity> selector)
    {
        return NMSMathSupport.getEntitiesInBox(world, unwrapEntity(exclusion), new AxisAlignedBB(from.x, from.y, from.z, to.x, to.y, to.z).grow(expand, expand, expand), selector);
    }

    @Override
    public NMSRayTraceResult rayTraceEntity(org.bukkit.entity.Entity exclusion, double expand, Predicate<org.bukkit.entity.Entity> selector)
    {
        List<Entity> entities = getNMSEntities(exclusion, expand, toNMSSelector(selector));

        return wrapResult(calculateRayTrace(entities, from, to, expand));
    }

    @Override
    public List<NMSRayTraceResult> rayTraceEntities(org.bukkit.entity.Entity exclusion, double expand, Predicate<org.bukkit.entity.Entity> selector)
    {
        List<Entity> entities = getNMSEntities(exclusion, expand, toNMSSelector(selector));

        ArrayList<NMSRayTraceResult> results = new ArrayList<>(Math.min(10, entities.size()));
        Vec3D from = this.from, to = this.to;

        for (Entity entity : entities)
        {
            AxisAlignedBB box = entity.getBoundingBox().grow(expand, expand, expand);
            MovingObjectPosition result = box.b(from, to);

            if (result != null)
            {
                result.entity = entity;
                results.add(new NMSRayTraceResult(result));
            }
            else if (box.b(from))
            {
                result = new MovingObjectPosition(from, null);
                result.entity = entity;
                results.add(new NMSRayTraceResult(result));
            }
        }

        return results;
    }
}
