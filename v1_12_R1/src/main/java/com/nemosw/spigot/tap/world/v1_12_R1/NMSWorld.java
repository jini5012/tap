package com.nemosw.spigot.tap.world.v1_12_R1;

import com.nemosw.spigot.tap.block.TapBlockData;
import com.nemosw.spigot.tap.block.v1_12_R1.NMSBlockData;
import com.nemosw.spigot.tap.block.v1_12_R1.NMSBlockSupport;
import com.nemosw.spigot.tap.world.TapWorld;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.IBlockData;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

public final class NMSWorld implements TapWorld
{

	public final World world;

	NMSWorld(World world)
	{
		this.world = world;
	}
	
	@Override
	public CraftWorld getWorld()
	{
		return this.world.getWorld();
	}
	
	@Override
	public NMSChunk getChunk(int x, int z)
	{
		return NMSWorldSupport.getInstance().wrapChunk(this.world.getChunkAt(x, z));
	}

    @Override
    public NMSBlockData getBlock(int x, int y, int z)
    {
        return NMSBlockSupport.getInstance().wrapBlockData(this.world.getType(new BlockPosition(x, y, z)));
    }

	@Override
	public boolean setBlock(int x, int y, int z, TapBlockData block, boolean applyPhysics)
	{
		BlockPosition position = new BlockPosition(x, y, z);
		IBlockData blockData = ((NMSBlockData) block).getBlockData();

		if (applyPhysics)
		{
			this.world.setTypeAndData(position, blockData, 3);
		}
		else
		{
			IBlockData old = this.world.getType(position);
			boolean success = this.world.setTypeAndData(position, blockData, 18);

			if (success)
				this.world.notify(position, old, blockData, 3);

			return success;
		}

		return true;
	}

}
