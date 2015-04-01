package toops.tsteelworks.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import toops.tsteelworks.common.core.TSContent;
import toops.tsteelworks.common.entity.projectile.EntityBrick;
import toops.tsteelworks.common.entity.projectile.EntityScorchedBrick;
import toops.tsteelworks.common.entity.projectile.EntityToastBrick;

public class EntityToaster extends EntitySilverfish implements IRangedAttackMob {
	public EntityToaster(World world) {
		super(world);

		setSize(0.4F, 1.8F);
		getNavigator().setAvoidsWater(true);
	}

	/**
	 * Attack the specified entity using a ranged attack.
	 */
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targetentity, float par2) {
		final EntityBrick brick = new EntityToastBrick(worldObj, this);

		final double d0 = targetentity.posX - posX;
		final double d1 = (targetentity.posY + targetentity.getEyeHeight()) - 1.100000023841858D - brick.posY;
		final double d2 = targetentity.posZ - posZ;
		final float f1 = MathHelper.sqrt_double((d0 * d0) + (d2 * d2)) * 0.2F;
		brick.setThrowableHeading(d0, d1 + f1, d2, 1.6F, 12.0F);
		playSound("random.bow", 1.0F, 1.0F / ((getRNG().nextFloat() * 0.4F) + 0.8F));
		worldObj.spawnEntityInWorld(brick);
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	public boolean isAIEnabled() {
		return true;
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isWet()) {
			attackEntityFrom(DamageSource.drown, 1.0F);
		}
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound() {
		return "mob.irongolem.hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound() {
		return "mob.irongolem.death";
	}

	@Override
	protected String getLivingSound() {
		return null;
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	@Override
	protected Item getDropItem() {
		return Items.bread;
	}
}
