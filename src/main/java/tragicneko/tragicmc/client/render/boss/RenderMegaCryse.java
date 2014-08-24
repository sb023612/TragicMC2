package tragicneko.tragicmc.client.render.boss;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelMegaCryse;

public class RenderMegaCryse extends RenderLiving {
	
	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/Cryse_lowRes.png");
	private static final float scale = 1.5F;
	
	public RenderMegaCryse() {
		super(new ModelMegaCryse(), 0.375F * scale);
	}
	
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
		GL11.glScalef(this.scale, this.scale, this.scale);
    }
	
	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
    {
		this.bindEntityTexture(par1EntityLivingBase);

		if (!par1EntityLivingBase.isInvisible() && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
        {       
            GL11.glPushMatrix();
            GL11.glColor4f(0.8F, 0.8F, 1.0F, 0.65F);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glPopMatrix();
            GL11.glDepthMask(true);
        }
        else
        {
            this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityLivingBase);
        }
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return texture;
	}

}
