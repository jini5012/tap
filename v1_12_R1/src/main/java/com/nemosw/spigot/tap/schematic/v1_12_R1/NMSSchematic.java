package com.nemosw.spigot.tap.schematic.v1_12_R1;

import com.nemosw.spigot.tap.nbt.v1_12_R1.NMSNBTCompound;
import com.nemosw.spigot.tap.util.nbt.NBTCompound;
import com.nemosw.spigot.tap.util.schematic.Schematic;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

@SuppressWarnings("deprecation")
public final class NMSSchematic extends Schematic
{
	
	@Override
	public NBTCompound save(org.bukkit.World world, int x, int y, int z, int width, int height, int length)
	{
		if (width < 1)
			throw new IllegalArgumentException("width must be greater than 0");
		if (width > 32767)
			throw new IllegalArgumentException("width cannot be greater than 32767");
		if (height < 1)
			throw new IllegalArgumentException("height must be greater than 0");
		if (height > 32767)
			throw new IllegalArgumentException("height cannot be greater than 32767");
		if (y + height > 255)
			throw new IllegalArgumentException("maximum Y cannot be greater than 255");
		if (length < 1)
			throw new IllegalArgumentException("length must be greater than 0");
		if (length > 32767)
			throw new IllegalArgumentException("length cannot be greater than 32767");

		int size = size(width, height, length);
		byte[] blocks = new byte[size];
		byte[] data = new byte[size];

		WorldServer w = ((CraftWorld) world).getHandle();

		for (int blockX = 0; blockX < width; blockX++)
			for (int blockY = 0; blockY < height; blockY++)
				for (int blockZ = 0; blockZ < length; blockZ++)
				{
					IBlockData blockData = w.getType(new BlockPosition(x + blockX, y + blockY, z + blockZ));
					Block block = blockData.getBlock();

					int index = (blockY * length + blockZ) * width + blockX;

					blocks[index] = (byte) Block.getId(block);
					data[index] = (byte) block.toLegacyData(blockData);
				}

		NBTTagList entities = new NBTTagList();

		for (Entity entity : w.getEntities(null, new AxisAlignedBB(x, y, z, x + width, y + height, z + length)))
		{
			NBTTagCompound entityTag = new NBTTagCompound();

			if (entity.d(entityTag))
			{
				NBTTagList pos = new NBTTagList();
				pos.add(new NBTTagDouble(entity.locX - x));
				pos.add(new NBTTagDouble(entity.locY - y));
				pos.add(new NBTTagDouble(entity.locZ - z));
				entityTag.set("Pos", pos);

				entityTag.remove("UUID");
				entityTag.remove("UUIDMost");
				entityTag.remove("UUIDLeast");

				entities.add(entityTag);
			}
		}

		NBTTagList tileEntities = new NBTTagList();

		for (TileEntity tileEntity : w.getTileEntities(x, y, z, x + width, y + height, z + length))
		{
			NBTTagCompound tileEntityTag = new NBTTagCompound();
			tileEntity.save(tileEntityTag);

			BlockPosition pos = tileEntity.getPosition();
			tileEntityTag.setInt("x", pos.getX() - x);
			tileEntityTag.setInt("y", pos.getY() - y);
			tileEntityTag.setInt("z", pos.getZ() - z);

			tileEntities.add(tileEntityTag);
		}

		NBTTagCompound schematic = new NBTTagCompound();

		schematic.setShort("Width", (short) width);
		schematic.setShort("Height", (short) height);
		schematic.setShort("Length", (short) length);
		schematic.setString("Materials", "Alpha");
		schematic.setByteArray("Blocks", blocks);
		schematic.setByteArray("Data", data);
		schematic.set("Entities", entities);
		schematic.set("TileEntities", tileEntities);

		return new NMSNBTCompound(schematic);
	}

	@Override
	public void load(World world, int x, int y, int z, NBTCompound schematic)
	{
		int width = schematic.getShort("Width");
		int height = schematic.getShort("Height");
		int length = schematic.getShort("Length");
		byte[] blocks = schematic.getByteArray("Blocks");
		byte[] data = schematic.getByteArray("Data");
		int size = blocks.length;

		if (width * height * length != size || size != data.length)
			throw new IllegalArgumentException("mismatch schematic data size");

		WorldServer w = ((CraftWorld) world).getHandle();

		for (int blockY = 0; blockY < height; blockY++)
			for (int blockZ = 0; blockZ < length; blockZ++)
				for (int blockX = 0; blockX < width; blockX++)
				{
					int index = (blockY * length + blockZ) * width + blockX;

					w.setTypeAndData(new BlockPosition(x + blockX, y + blockY, z + blockZ), Block.getById(blocks[index] & 0xFF).fromLegacyData(data[index] & 0xFF), 3);
				}

		NBTTagCompound tag = ((NMSNBTCompound) schematic).getHandle();
		NBTTagList entities = (NBTTagList) tag.get("Entities");

		for (int i = 0, j = entities.size(); i < j; i++)
		{
			NBTTagCompound entityTag = entities.get(i);

			Entity entity = EntityTypes.a(entityTag, w);

			if (entity != null)
			{
				entity.lastX = entity.N = entity.locX += x;
				entity.lastY = entity.M = entity.locY += y;
				entity.lastZ = entity.O = entity.locZ += z;
				entity.setPosition(entity.locX, entity.locY, entity.locZ);

				w.addEntity(entity, SpawnReason.CUSTOM);
			}
		}

		NBTTagList tileEntities = (NBTTagList) tag.get("TileEntities");

		for (int i = 0, j = tileEntities.size(); i < j; i++)
		{
			NBTTagCompound tileEntityTag = tileEntities.get(i);
			TileEntity tileEntity = TileEntity.create(w, tileEntityTag);

			if (tileEntity != null)
			{
				BlockPosition pos = tileEntity.getPosition();

				tileEntity.setPosition(new BlockPosition(pos.getX() + x, pos.getY() + y, pos.getZ() + z));
				w.setTileEntity(tileEntity.getPosition(), tileEntity);
			}
		}
	}
	
}
