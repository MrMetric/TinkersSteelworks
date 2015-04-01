package toops.tsteelworks.client.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderToaster extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("tsteelworks", "textures/mob/toaster.png");

	public RenderToaster() {
		super(new ModelChicken(), 0.3F);
	}

	public ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}

	@Override
	protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_) {
		return 0;
	}
}
