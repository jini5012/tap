package com.nemosw.spigot.tap.nms.v1_12_R1;


import com.nemosw.spigot.tap.FireworkEffect;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;

public final class NMSFireworkEffect implements FireworkEffect
{
	
	static final Item ITEM_FIRE_WORK = Item.getById(401);
	
	public static final EntityFireworks ENTITY_FIRE_WORK = new EntityFireworks(((CraftServer) Bukkit.getServer()).getServer().getWorld());
	
	static final int ID = ENTITY_FIRE_WORK.getId();

	public final DataWatcher watcher;

	NMSFireworkEffect(FireworkEffect.Builder builder)
	{
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagCompound fw = new NBTTagCompound();
		NBTTagCompound ex = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		fw.set("Explosions", list);
		tag.set("Fireworks", fw);
		ex.setByte("Trail", (byte) (builder.isTrail() ? 1 : 0));
		ex.setByte("Flicker", (byte) (builder.isFlicker() ? 1 : 0));
		ex.setByte("Type", builder.getType().getId());
		ex.setIntArray("Colors", builder.getColors());
		ex.setIntArray("FadeColors", builder.getFadeColors());
		list.add(ex);

		ItemStack item = new ItemStack(ITEM_FIRE_WORK, 1, 0);
		item.setTag(tag);

		DataWatcher watcher = new DataWatcher(ENTITY_FIRE_WORK);
		watcher.register(EntityFireworks.FIREWORK_ITEM, item);

		this.watcher = watcher;
	}
	
}
