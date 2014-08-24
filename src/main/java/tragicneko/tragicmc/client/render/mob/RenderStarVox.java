package tragicneko.tragicmc.client.render.mob;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelNorVox;
import tragicneko.tragicmc.entity.mob.EntityStarVox;

public class RenderStarVox extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("tragicmc:textures/mobs/StarVox_lowRes.png");
	
	private static float rgbR = 1.0F;
    private static float rgbG = 1.0F;
    private static float rgbB = 1.0F;

	public RenderStarVox() {
		super(new ModelNorVox(), 0.65F);
	}

	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
    {
		this.renderModel((EntityStarVox) par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
    }

	private void renderModel(EntityStarVox par1EntityLivingBase, float par2, 	float par3, float par4, float par5, float par6, float par7) 
	{
		this.bindEntityTexture(par1EntityLivingBase);

		if (!par1EntityLivingBase.isInvisible() && !par1EntityLivingBase.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
        {       
            GL11.glPushMatrix();
            
            this.setRGBThroughTextureID(par1EntityLivingBase.getTextureID());
            
            GL11.glColor4f(rgbR, rgbG, rgbB, 0.75F);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.2F);
            GL11.glPopMatrix();
            GL11.glDepthMask(true);
            
        }
        else
        {
            this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityLivingBase);
        }
		
	}

	private void setRGBThroughTextureID(int textureID) {
		switch(textureID)
		{
		default:
		case 0:
			rgbR = 0.75F;
			rgbG = 0.75F;
			rgbB = 0.75F;
			break;
		case 1:
			rgbR = 0.75F;
			rgbG = 0.25F;
			rgbB = 0.25F;
			break;
		case 2:
			rgbR = 0.25F;
			rgbG = 0.75F;
			rgbB = 0.25F;
			break;
		case 3:
			rgbR = 0.25F;
			rgbG = 0.25F;
			rgbB = 0.75F;
			break;
		case 4:
			rgbR = 0.25F;
			rgbG = 0.75F;
			rgbB = 0.75F;
			break;
		case 5:
			rgbR = 0.75F;
			rgbG = 0.75F;
			rgbB = 0.25F;
			break;
		case 6:
			rgbR = 0.75F;
			rgbG = 0.25F;
			rgbB = 0.75F;
			break;
		case 7:
			rgbR = 0.25F;
			rgbG = 0.25F;
			rgbB = 0.25F;
			break;
		}
		
		rgbR *= 2.55F;
		rgbG *= 2.55F;
		rgbB *= 2.55F;
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return texture;
	}

}
