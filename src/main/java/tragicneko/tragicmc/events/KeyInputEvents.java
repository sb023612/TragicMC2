package tragicneko.tragicmc.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import org.lwjgl.input.Keyboard;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.client.ClientProxy;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.network.MessageGui;
import tragicneko.tragicmc.network.MessageUseDoomsday;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyInputEvents {

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		if (Minecraft.getMinecraft().inGameHasFocus)
		{
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

			if (ClientProxy.openAmuletGui.getIsKeyPressed() && TragicNewConfig.allowAmulets)
			{
				TragicMC.net.sendToServer(new MessageGui(TragicMC.idAmuletGui));
			}

			if (ClientProxy.useSpecial.getIsKeyPressed() && TragicNewConfig.allowDoomsdays)
			{
				TragicMC.net.sendToServer(new MessageUseDoomsday(player.getCurrentEquippedItem()));
			}
		}
	}

	@SubscribeEvent
	public void onClientUpdate(LivingUpdateEvent event)
	{
		if (!Minecraft.getMinecraft().inGameHasFocus) return;

		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

		if (TragicNewConfig.allowFlight && Keyboard.isCreated() && player.isPotionActive(TragicPotions.Flight.id) && player.ticksExisted % 2 == 0)
		{	
			PotionEffect effect = player.getActivePotionEffect(TragicPotions.Flight);

			if (effect.getDuration() >= 40)
			{
				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				{
					if (player.isSprinting())
					{
						player.motionY = 0.215D;
					}
					else
					{
						player.motionY = 0.135D;
					}
				}
				else if (player.isSneaking())
				{
					player.motionY = -0.455D;
				}
			}
		}
	}
}
