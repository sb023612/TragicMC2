package tragicneko.tragicmc.client.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.items.armor.TragicArmor;
import tragicneko.tragicmc.items.weapons.ItemJack;
import tragicneko.tragicmc.items.weapons.ItemScythe;
import tragicneko.tragicmc.items.weapons.ItemShield;
import tragicneko.tragicmc.items.weapons.TragicWeapon;
import tragicneko.tragicmc.items.weapons.WeaponCelestialAegis;
import tragicneko.tragicmc.items.weapons.WeaponCelestialLongbow;
import tragicneko.tragicmc.items.weapons.WeaponHuntersBow;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDoom extends Gui
{
	private Minecraft mc;
	private int buffer;
	private int width;

	private FontRenderer fontRenderer;

	private static final ResourceLocation texturepath = new ResourceLocation("tragicmc:textures/gui/doom_neko.png");
	private static final ResourceLocation texturepath2 = new ResourceLocation("tragicmc:textures/gui/doom_pink.png");
	private static final ResourceLocation texturepath3 = new ResourceLocation("tragicmc:textures/gui/doom_tentacle.png");
	private static final ResourceLocation texturepath4 = new ResourceLocation("tragicmc:textures/gui/doom_pokemon.png");

	public GuiDoom(Minecraft mc) {
		super();
		this.mc = mc;
	}

	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onRenderOverlay(RenderGameOverlayEvent event)
	{
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE || Minecraft.getMinecraft().gameSettings.showDebugInfo) return;

		PropertyDoom props = PropertyDoom.get(this.mc.thePlayer);
		if (props == null || props.getMaxDoom() == 0) {
			return; 
		}

		int xPos = 1;
		int yPos = 1;
		this.mc.getTextureManager().bindTexture(getTextureFromConfig());

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		float trans = TragicNewConfig.guiTransparency / 100.0F;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, trans);
		GL11.glDisable(GL11.GL_ALPHA_TEST);

		drawTexturedModalRect(xPos, yPos, 0, 0, 56, 9);

		width++;

		if (width > 30)
		{
			width = 0;
		}

		if (!TragicNewConfig.allowAnimatedGui) width = 0;

		if (props.getCurrentCooldown() > 0)
		{
			drawTexturedModalRect(xPos + 3, yPos + 3, width / 3, 12, 49, 3);

			String s = "Cooling Down... " + props.getCurrentCooldown() ;
			yPos += 6;
			Color color = new Color(0x27, 0xb8, 0xdc);

			this.mc.fontRenderer.drawString(s, xPos + 1, yPos, 0);
			this.mc.fontRenderer.drawString(s, xPos - 1, yPos, 0);
			this.mc.fontRenderer.drawString(s, xPos, yPos + 1, 0);
			this.mc.fontRenderer.drawString(s, xPos, yPos - 1, 0);
			this.mc.fontRenderer.drawString(s, xPos, yPos, color.getRGB());
		}
		else
		{
			buffer++;
			if (!TragicNewConfig.allowAnimatedGui) buffer = 0;
			int manabarwidth = (int)(((float) props.getCurrentDoom() / props.getMaxDoom()) * 49);

			drawTexturedModalRect(xPos + 3, yPos + 3, width / 3, 9, manabarwidth, 3);

			String s = "Doom: " + props.getCurrentDoom() + "/" + props.getMaxDoom();
			yPos += 6;
			Color color = new Color(0x96, 0x30, 0x30);
			boolean flag = false;

			if (this.mc.thePlayer.getCurrentEquippedItem() != null)
			{
				ItemStack stack = this.mc.thePlayer.getCurrentEquippedItem();

				if (stack.getItem() instanceof WeaponHuntersBow)
				{
					if (((WeaponHuntersBow)stack.getItem()).doomsday.doesCurrentDoomMeetRequirement(props))
					{
						flag = true;
					}
				}
				else if (stack.getItem() instanceof WeaponCelestialLongbow)
				{
					if (((WeaponCelestialLongbow)stack.getItem()).doomsday.doesCurrentDoomMeetRequirement(props))
					{
						flag = true;
					}
				}
				else if (stack.getItem() instanceof TragicWeapon)
				{
					if (stack.getItem() instanceof WeaponCelestialAegis)
					{
						if (((WeaponCelestialAegis)stack.getItem()).doomsday2.doesCurrentDoomMeetRequirement(props))
						{
							flag = true;
						}
						else if (((WeaponCelestialAegis)stack.getItem()).doomsday.doesCurrentDoomMeetRequirement(props));
						{
							long time = mc.theWorld.getWorldTime();
							if (mc.theWorld.canBlockSeeTheSky((int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ) && time >= 16000 && time <= 18000)
							{
								flag = true;
							}
						}
					}
					else if (((TragicWeapon)stack.getItem()).doomsday.doesCurrentDoomMeetRequirement(props))
					{
						flag = true;
					}
				}
				else if (stack.getItem() instanceof ItemShield)
				{
					if (((ItemShield)stack.getItem()).doomsday.doesCurrentDoomMeetRequirement(props))
					{
						flag = true;
					}
				}
				else if (stack.getItem() instanceof ItemScythe)
				{
					if (((ItemScythe)stack.getItem()).doomsday != null && ((ItemScythe)stack.getItem()).doomsday.doesCurrentDoomMeetRequirement(props))
					{
						flag = true;
					}
				}
				else if (stack.getItem() instanceof ItemJack)
				{
					if (((ItemJack)stack.getItem()).doomsday != null && ((ItemJack)stack.getItem()).doomsday.doesCurrentDoomMeetRequirement(props))
					{
						flag = true;
					}
				}

				if (flag)
				{
					if (buffer > 10 && buffer <= 20)
					{
						color = new Color(0xff, 0xf3, 0x68);
					}
					else
					{
						color = new Color(0xff, 0xb8, 0x68);
					}
				}
			}
			else
			{
				flag = false;
				boolean flag2 = false;
				Doomsday[] doomsdays = new Doomsday[4];
				ItemStack stack;
				EntityPlayer player = mc.thePlayer;
				Doomsday doomsday = null;

				for (int i = 0; i < 4; i++)
				{
					stack = player.getCurrentArmor(i);

					if (stack != null && stack.getItem() instanceof TragicArmor)
					{
						if (i == 0)
						{
							doomsday = ((TragicArmor)stack.getItem()).doomsday;
						}

						doomsdays[i] = ((TragicArmor)stack.getItem()).doomsday;
					}
				}

				for (int i = 0; i < 4; i++)
				{
					if (doomsdays[i] == null)
					{
						flag2 = false;
					}
					else if (doomsdays[i] == doomsday)
					{
						flag2 = true;
					}
					else
					{
						flag2 = false;
					}
				}

				if (flag2 && doomsday != null && doomsday.doesCurrentDoomMeetRequirement(props)) flag = true;

				if (flag)
				{					
					if (buffer >= 10 && buffer <= 20)
					{
						color = new Color(0xff, 0xf3, 0x68);
					}
					else
					{
						color = new Color(0xff, 0xb8, 0x68);
					}
				}
			}

			if (buffer > 20)
			{
				buffer = 0;
			}

			this.mc.fontRenderer.drawString(s, xPos + 1, yPos, 0);
			this.mc.fontRenderer.drawString(s, xPos - 1, yPos, 0);
			this.mc.fontRenderer.drawString(s, xPos, yPos + 1, 0);
			this.mc.fontRenderer.drawString(s, xPos, yPos - 1, 0);
			this.mc.fontRenderer.drawString(s, xPos, yPos, color.getRGB());
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
	}

	public static ResourceLocation getTextureFromConfig()
	{
		switch(TragicNewConfig.guiTexture)
		{
		case 0:
			return texturepath3;
		case 1:
			return texturepath2;
		case 2:
			return texturepath;
		default:
			return texturepath4;
		}
	}
}
