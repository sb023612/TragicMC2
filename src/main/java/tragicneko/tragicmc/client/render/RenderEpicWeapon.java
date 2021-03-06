package tragicneko.tragicmc.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.client.model.ModelBlock;
import tragicneko.tragicmc.client.model.weapon.ModelButcher;
import tragicneko.tragicmc.client.model.weapon.ModelCelestialAegis;
import tragicneko.tragicmc.client.model.weapon.ModelDragonFang;
import tragicneko.tragicmc.client.model.weapon.ModelParanoia;
import tragicneko.tragicmc.client.model.weapon.ModelReaperScythe;
import tragicneko.tragicmc.client.model.weapon.ModelSplinter;
import tragicneko.tragicmc.client.model.weapon.ModelThardus;
import tragicneko.tragicmc.client.model.weapon.ModelTitan;

public class RenderEpicWeapon implements IItemRenderer {

	private final String path = "tragicmc:textures/weapons/";
	private final Minecraft mc;
	public final ModelBase model;
	public final ResourceLocation texture;
	
	public ModelBase[] models = new ModelBase[] {new ModelReaperScythe(), new ModelButcher(), new ModelDragonFang(), new ModelThardus(), new ModelSplinter(), new ModelParanoia(),
		new ModelCelestialAegis(), new ModelTitan(), new ModelBlock(), new ModelBlock()};
	
	public ResourceLocation[] textures = new ResourceLocation[] {new ResourceLocation(path + "ReaperScythe_lowRes.png"), new ResourceLocation(path + "Butcher_lowRes.png"),
		new ResourceLocation(path + "DragonFang_lowRes.png"), new ResourceLocation(path + "Thardus_lowRes.png"), new ResourceLocation(path + "Splinter_lowRes.png"),
		new ResourceLocation(path + "Paranoia_lowRes.png"), new ResourceLocation(path + "CelestialAegis_lowRes.png"), new ResourceLocation(path + "Titan_lowRes.png"),
		new ResourceLocation(path + "TragicHellraiser_lowRes.png"), new ResourceLocation(path + "Sentinel_lowRes.png")};
	
	private final ResourceLocation itemGlint = new ResourceLocation("textures/misc/enchanted_item_glint.png");

	public RenderEpicWeapon(int i, Minecraft mc)
	{
		this.model = this.getModelFromItem(i);
		this.texture = this.getTextureFromItem(i);
		this.mc = mc;
	}

	private ResourceLocation getTextureFromItem(int i) {
		return textures[i];
	}

	private ModelBase getModelFromItem(int i) {
		return models[i];
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		GL11.glScalef(0.065F, 0.065F, 0.065F);
		mc.renderEngine.bindTexture(texture);
		model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
		GL11.glPopMatrix();
	}

}
