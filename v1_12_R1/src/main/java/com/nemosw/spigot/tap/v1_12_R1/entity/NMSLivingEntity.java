package com.nemosw.spigot.tap.v1_12_R1.entity;

import com.nemosw.spigot.tap.entity.TapLivingEntity;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityLiving;
import org.bukkit.entity.LivingEntity;

public class NMSLivingEntity extends NMSEntity implements TapLivingEntity
{
	
	protected final EntityLiving living;
	
	NMSLivingEntity(Entity entity)
	{
		super(entity);
		
		this.living = (EntityLiving) entity;
	}
	
	@Override
	public EntityLiving getEntity()
	{
		return living;
	}
	
	@Override
	public LivingEntity getBukkitEntity()
	{
		return (LivingEntity) super.getBukkitEntity(); 
	}

	@Override
	public float getEyeHeight()
	{
		return this.living.getHeadHeight();
	}
	
	@Override
	public double getHealth()
	{
		return this.living.getHealth();
	}
	
}
