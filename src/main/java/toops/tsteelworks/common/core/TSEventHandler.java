package toops.tsteelworks.common.core;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import toops.tsteelworks.common.entity.EntityToaster;
import toops.tsteelworks.lib.ModsData;

public class TSEventHandler {
	@SubscribeEvent
	public void bucketFill(FillBucketEvent evt) {
		if (evt.current.getItem() == Items.bucket && evt.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			int hitX = evt.target.blockX;
			int hitY = evt.target.blockY;
			int hitZ = evt.target.blockZ;

			if (evt.entityPlayer != null && !evt.entityPlayer.canPlayerEdit(hitX, hitY, hitZ, evt.target.sideHit, evt.current)) {
				return;
			}

			Block fluidBlock = evt.world.getBlock(hitX, hitY, hitZ);
			ItemStack bucket = null;

			if (fluidBlock == ModsData.Fluids.steamBlock) {
				bucket = ModsData.Fluids.bucketSteam;
			} else if (fluidBlock == ModsData.Fluids.moltenLimestone) {
				bucket = ModsData.Fluids.bucketLimestone;
			} else if (fluidBlock == ModsData.Fluids.liquidCement) {
				bucket = ModsData.Fluids.bucketCement;
			}

			if (bucket == null)
				return;

			if (evt.entityPlayer == null || !evt.entityPlayer.capabilities.isCreativeMode) {
				evt.result = bucket.copy();
				evt.setResult(Event.Result.ALLOW);
			}

			evt.world.setBlockToAir(hitX, hitY, hitZ);
		}
	}

	@SubscribeEvent
	public void createToaster(ItemExpireEvent event) {
		if (!ConfigCore.toasterEnabled || event.isCanceled()) return;
		if (!event.entityItem.getEntityItem().getItem().equals(Items.bread)) return;

		final double x = event.entityItem.posX;
		final double y = event.entityItem.posY;
		final double z = event.entityItem.posZ;
		final World world = event.entityItem.worldObj;

		final EntityToaster toaster = new EntityToaster(world);
		toaster.setPosition(x, y, z);
		world.spawnEntityInWorld(toaster);
	}
}
