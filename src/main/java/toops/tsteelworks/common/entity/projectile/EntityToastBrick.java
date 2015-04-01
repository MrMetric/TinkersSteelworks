package toops.tsteelworks.common.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tconstruct.library.tools.AbilityHelper;

public class EntityToastBrick extends EntityBrick {
	public EntityToastBrick(World world) {
		super(world);

		setKnockbackStrength(1);
	}

	public EntityToastBrick(World world, double x, double y, double z) {
		super(world, x, y, z);

		setKnockbackStrength(1);
	}

	public EntityToastBrick(World world, EntityLivingBase entity) {
		super(world, entity);

		setKnockbackStrength(1);
	}

	@Override
	protected void dropOnImpact() {
		if (Math.random() < 0.02) {
			AbilityHelper.spawnItemAtEntity(this, new ItemStack(Items.bread, 1, 0), 0);
		}
	}
}
