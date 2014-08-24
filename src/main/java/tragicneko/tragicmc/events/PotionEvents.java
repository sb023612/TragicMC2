package tragicneko.tragicmc.events;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent.Start;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent.Tick;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.TragicBoss;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.network.MessageFlight;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionEvents {

	@SubscribeEvent
	public void flightSync(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayerMP)
		{
			TragicMC.net.sendTo(new MessageFlight(TragicNewConfig.allowFlight), (EntityPlayerMP) event.entity);
		}
	}

	@SubscribeEvent
	public void tragicPotionEffect(LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		World world = event.entityLiving.worldObj;

		if (TragicNewConfig.allowClarity && entity.isPotionActive(TragicPotions.Clarity.id))
		{
			if (TragicNewConfig.allowDisorientation && event.entityLiving.isPotionActive(TragicPotions.Disorientation))
			{
				event.entityLiving.removePotionEffect(TragicPotions.Disorientation.id);
			}

			if (event.entityLiving.isPotionActive(Potion.confusion))
			{
				event.entityLiving.removePotionEffect(Potion.confusion.id);
			}

			if (event.entityLiving.isPotionActive(Potion.blindness))
			{
				event.entityLiving.removePotionEffect(Potion.blindness.id);
			}

			if (world.isRemote)
			{
				double d0 = 8.0D + (8.0D * entity.getActivePotionEffect(TragicPotions.Clarity).getAmplifier());

				List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(d0, d0, d0));
				EntityLivingBase target;

				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i) instanceof EntityLivingBase)
					{
						target = (EntityLivingBase) list.get(i);
						if (target.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) target.setInvisible(false);
					}
				}
			}
		}

		if (TragicNewConfig.allowInhibit && event.entityLiving.isPotionActive(TragicPotions.Inhibit))
		{
			if (TragicNewConfig.allowImmunity && event.entityLiving.isPotionActive(TragicPotions.Immunity))
			{
				event.entityLiving.removePotionEffect(TragicPotions.Immunity.id);
			}

			if (event.entityLiving.isPotionActive(Potion.regeneration))
			{
				event.entityLiving.removePotionEffect(Potion.regeneration.id);
			}

			if (event.entityLiving.isPotionActive(Potion.damageBoost))
			{
				event.entityLiving.removePotionEffect(Potion.damageBoost.id);
			}

			if (event.entityLiving.isPotionActive(Potion.digSpeed)) 
			{
				event.entityLiving.removePotionEffect(Potion.digSpeed.id);
			} 

			if (event.entityLiving.isPotionActive(Potion.moveSpeed))
			{
				event.entityLiving.removePotionEffect(Potion.moveSpeed.id);
			}
		}

		if (TragicNewConfig.allowImmunity && event.entityLiving.isPotionActive(TragicPotions.Immunity))
		{
			if (TragicNewConfig.allowInhibit && event.entityLiving.isPotionActive(TragicPotions.Inhibit))
			{
				event.entityLiving.removePotionEffect(TragicPotions.Immunity.id);
			}

			if (TragicNewConfig.allowStun && event.entityLiving.isPotionActive(TragicPotions.Stun))
			{
				event.entityLiving.removePotionEffect(TragicPotions.Stun.id);
			}

			if (event.entityLiving.isPotionActive(Potion.poison))
			{
				event.entityLiving.removePotionEffect(Potion.poison.id);
			}

			if (event.entityLiving.isPotionActive(Potion.wither))
			{
				event.entityLiving.removePotionEffect(Potion.wither.id);
			}

			if (TragicNewConfig.allowCorruption && event.entityLiving.isPotionActive(TragicPotions.Corruption)) 
			{
				event.entityLiving.removePotionEffect(TragicPotions.Corruption.id);
			} 
		}

		if (TragicNewConfig.allowCorruption && entity.isPotionActive(TragicPotions.Corruption) && !world.isRemote)
		{
			if (entity instanceof TragicMob)
			{
				if (!((TragicMob)entity).isCorruptible)
				{
					entity.removePotionEffect(TragicPotions.Corruption.id);
				}
			}

			if (entity instanceof EntityPlayer && entity.ticksExisted % 40 == 0 && rand.nextBoolean())
			{
				if (!((EntityPlayer)entity).capabilities.isCreativeMode)
				{
					entity.attackEntityFrom(DamageSource.outOfWorld, 1.0F);
				}
			}

			if (entity instanceof EntityAnimal && entity.ticksExisted % 20 == 0 && rand.nextInt(8) == 0)
			{
				entity.attackEntityFrom(DamageSource.outOfWorld, 1.0F);
			}
		}

		if (TragicNewConfig.allowInvulnerability && event.entityLiving.isPotionActive(TragicPotions.Invulnerability))
		{
			if (event.entityLiving instanceof EntityPlayer)
			{
				if (!((EntityPlayer)event.entityLiving).capabilities.isCreativeMode)
				{
					if (TragicNewConfig.allowFlight && ((EntityPlayer)event.entityLiving).isPotionActive(TragicPotions.Flight))
					{
						event.entityLiving.removePotionEffect(TragicPotions.Flight.id);
					}
				}
			}
		}

		if (TragicNewConfig.allowFlight && entity.isPotionActive(TragicPotions.Flight.id))
		{
			int a = entity.getActivePotionEffect(TragicPotions.Flight).getDuration();

			if (entity instanceof EntityPlayer && !entity.isInWater() && !entity.onGround)
			{
				EntityPlayer player = (EntityPlayer) entity;

				if (!player.capabilities.isCreativeMode)
				{
					if (a > 20)
					{
						if (rand.nextInt(128) == 0 && player.motionY > 0.0)
						{
							player.addExhaustion(0.5F);
						}

						if (player.motionY <= 0.0)
						{
							if (player.isSneaking())
							{
								player.motionY -= 0.0612;
							}
							else
							{
								player.motionY = -0.215D;
							}
						}
						else if (player.motionY >= 0.4115)
						{
							if (rand.nextBoolean()) player.motionY *= 0.998647D;
						}

						if (player.motionY == 0.0D)
						{
							if (rand.nextInt(8) == 0) player.motionY += 0.25D;
							player.moveEntity(0.0D, player.motionY, 0.0D);
						}

						if (Math.abs(player.motionX) <= 0.4115) player.motionX *= 1.075D;
						if (Math.abs(player.motionZ) <= 0.4115) player.motionZ *= 1.075D;
					}
					else
					{
						player.motionY -= 0.2;
					}
				}
			}

			if (a == 0)
			{
				entity.removePotionEffect(TragicPotions.Flight.id);
			}
		}

		if (TragicNewConfig.allowAquaSuperiority && entity.isPotionActive(TragicPotions.AquaSuperiority.id))
		{
			if (entity instanceof EntityPlayer && entity.isInWater())
			{

				if (Math.abs(entity.motionX) <= 1.4115D) entity.motionX *= 1.2;
				if (Math.abs(entity.motionZ) <= 1.4115D) entity.motionZ *= 1.2;
				((EntityPlayer)entity).setAir(300);

				if (entity.motionY > 0.0)
				{
					if (Math.abs(entity.motionY) <= 0.6115D) entity.motionY *= 1.2;
				}
				else
				{
					if (entity.isSneaking())
					{
						entity.motionY -= 0.1;
					}
					else
					{
						entity.motionY *= 0.8;
					}
				}
			}
		}

		if (TragicNewConfig.allowFear && entity.isPotionActive(TragicPotions.Fear.id) && entity.ticksExisted % 2 == 0)
		{
			EntityLivingBase target = null;

			if (entity instanceof EntityLiving && ((EntityLiving) entity).getAttackTarget() != null)
			{
				target = ((EntityLiving) entity).getAttackTarget();
			}

			if (entity instanceof EntityMob)
			{
				((EntityMob) entity).setPathToEntity(null);
				((EntityMob) entity).setTarget(null);
				((EntityMob) entity).attackTime = 500;
				((EntityMob) entity).setLastAttacker(null);
			}

			double d0 = entity instanceof EntityMob ? 8.0D : 16.0D;
			List<EntityLivingBase> list = entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(d0, d0, d0));

			if (target != null)
			{
				double d1 = entity.posX - target.posX;
				double d2 = entity.posZ - target.posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				double d3 = 0.5D;
				double rate = entity.getDistanceToEntity(target) <= d0 / 3 ? 0.280000024529277D : (entity.getDistanceToEntity(target) >= d0 * 2 / 3 ? 0.07000257982D : 0.160000011920929D);
				int amp = entity.getActivePotionEffect(TragicPotions.Fear).getAmplifier();
				if (amp > 3) amp = 3;
				if (amp > 0)
				{
					for (int i = 0; i < amp; i++)
					{
						rate *= 1.015F;
					}
				}

				entity.motionX = -d1 / (double)f2 * d3 * rate + target.motionX * 0.60000000298023224D;
				entity.motionZ = -d2 / (double)f2 * d3 * rate + target.motionZ * 0.60000000298023224D;
				entity.moveEntity(entity.motionX, 0.0, entity.motionZ);
				entity.isSwingInProgress = true;
			}
			else
			{
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i) instanceof EntityLivingBase)
					{
						EntityLivingBase entity2 = (EntityLivingBase) list.get(i);

						if (entity.canEntityBeSeen(entity2))
						{
							double d1 = entity2.posX - entity.posX;
							double d2 = entity2.posZ - entity.posZ;
							float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
							double d3 = 0.5D;
							double rate = entity.getDistanceToEntity(entity2) <= d0 / 3 ? 0.280000024529277D : (entity.getDistanceToEntity(entity2) >= d0 * 2 / 3 ? 0.07000257982D : 0.160000011920929D);
							int amp = entity.getActivePotionEffect(TragicPotions.Fear).getAmplifier();
							if (amp > 3) amp = 3;
							if (amp > 0)
							{
								for (int j = 0; j < amp; j++)
								{
									rate *= 1.15F;
								}
							}

							entity.motionX = -d1 / (double)f2 * d3 * rate + entity.motionX * 0.60000000298023224D;
							entity.motionZ = -d2 / (double)f2 * d3 * rate + entity.motionZ * 0.60000000298023224D;
							entity.moveEntity(entity.motionX, 0.0, entity.motionZ);
							entity.isSwingInProgress = true;
							break;
						}
					}
				}
			}
		}

		if (TragicNewConfig.allowHarmony && event.entityLiving.isPotionActive(TragicPotions.Harmony))
		{
			if (event.entityLiving instanceof EntityEnderman || event.entityLiving instanceof EntityDragon 
					|| event.entityLiving instanceof EntityWither || event.entityLiving instanceof TragicBoss)
			{
				event.entityLiving.removePotionEffect(TragicPotions.Harmony.id);
			}
		}

		if (TragicNewConfig.allowHarmony && entity.isPotionActive(TragicPotions.Harmony.id))
		{
			if (entity instanceof EntityMob)
			{
				((EntityMob) entity).setPathToEntity(null);
				((EntityMob) entity).setTarget(null);
				((EntityMob) entity).attackTime = 500;
				((EntityMob) entity).setLastAttacker(null);
			}

			if (entity.getActivePotionEffect(TragicPotions.Harmony).getDuration() == 0)
			{
				entity.removePotionEffect(TragicPotions.Harmony.id);
			}
		}

		if (TragicNewConfig.allowMalnourish && entity.isPotionActive(TragicPotions.Malnourish.id))
		{
			if (entity instanceof EntityPlayerMP)
			{
				int amp = entity.getActivePotionEffect(TragicPotions.Malnourish).getAmplifier() * 2;
				FoodStats food = ((EntityPlayerMP)entity).getFoodStats();
				Boolean flag = entity.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration");

				if (amp > 10)
				{
					amp = 10;
				}

				if (amp <= 0)
				{
					amp = 1;
				}

				int f = food.getFoodLevel();
				int limit = amp >= 10 ? 9 : (amp > 6 ? 13 : (amp > 3 ? 15 : 19));

				if (entity.getHealth() >= entity.getMaxHealth() - amp)
				{						
					if (f >= limit)
					{
						if (f - amp < 0) f = amp;
						food.addStats(-amp, -amp);
					}

					int chance = Math.abs(MathHelper.ceiling_double_int(16 / amp) * 20);
					if (chance < 8) chance = 8;
					if (Math.abs(amp) >= 2 && rand.nextInt(chance) == 0) food.addExhaustion(0.5F);
				}
			}
		}

		if (TragicNewConfig.allowDisorientation && event.entityLiving.isPotionActive(TragicPotions.Disorientation))
		{
			float strafe = (float)rand.nextDouble() * MathHelper.getRandomIntegerInRange(rand, -2, 2);
			float forward = (float)rand.nextDouble() * MathHelper.getRandomIntegerInRange(rand, -2, 2);

			if (event.entityLiving.ticksExisted % 60 == 0)
			{
				event.entityLiving.moveEntityWithHeading(strafe, forward);
			}

			if (event.entityLiving instanceof EntityPlayer)
			{
				((EntityPlayer)event.entityLiving).cameraYaw = (float)rand.nextDouble() * MathHelper.getRandomIntegerInRange(rand, -1, 1) * 2.0F;
				((EntityPlayer)event.entityLiving).cameraPitch = (float)rand.nextDouble() * MathHelper.getRandomIntegerInRange(rand, -1, 1) * 2.0F;
			}
		}
		
		if (TragicNewConfig.allowCripple && event.entityLiving.isPotionActive(TragicPotions.Cripple))
		{
			if (event.entityLiving.getHealth() > event.entityLiving.getMaxHealth())
			{
				event.entityLiving.setHealth(event.entityLiving.getMaxHealth());
			}
		}
	}

	@SubscribeEvent
	public void livingJump(LivingJumpEvent event)
	{
		Boolean flag1 = false;
		Boolean flag2 = false;

		if (TragicNewConfig.allowStun)
		{
			flag1 = event.entityLiving.isPotionActive(TragicPotions.Stun.id);
		}

		if (TragicNewConfig.allowCripple)
		{
			flag2 = event.entityLiving.isPotionActive(TragicPotions.Cripple.id);
		}

		if (flag1)
		{
			event.entityLiving.motionY *= 0.0;
			event.entityLiving.motionZ *= 0.0;
			event.entityLiving.motionX *= 0.0;
		}

		if (flag2 && !flag1)
		{
			event.entityLiving.motionY *= 0.9;
		}
	}

	@SubscribeEvent
	public void potionFallEvent(LivingFallEvent event)
	{
		if (TragicNewConfig.allowFlight && event.entityLiving.isPotionActive(TragicPotions.Flight.id))
		{
			if (event.isCancelable())
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void potionDeathEvent(LivingDeathEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer && TragicNewConfig.allowResurrection)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (player.isPotionActive(TragicPotions.Resurrection.id))
			{
				if (event.isCancelable())
				{
					event.setCanceled(true);
				}

				float amp = (float)player.getActivePotionEffect(TragicPotions.Resurrection).getAmplifier();

				if (amp > 3.0F)
				{
					amp = 3.0F;
				}

				float percent = (float)((amp + 1) / 4);
				player.setHealth((player.getMaxHealth() * percent));

				for (int i = 0; i < 50; i++)
				{
					if (player.isPotionActive(i))
					{
						player.removePotionEffect(i);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onItemUsed(Start event)
	{
		if (TragicNewConfig.allowMalnourish && event.entityPlayer.isPotionActive(TragicPotions.Malnourish) && event.item.getItem() instanceof ItemFood)
		{
			int amp = event.entityPlayer.getActivePotionEffect(TragicPotions.Malnourish).getAmplifier() * 2;

			if (amp > 20)
			{
				amp = 20;
			}

			if (amp <= 0)
			{
				amp = 1;
			}

			if (event.entityPlayer.getHealth() >= event.entityPlayer.getMaxHealth() - amp)
			{
				if (event.isCancelable())
				{
					event.setCanceled(true);
				}
			}
		}

		if (TragicNewConfig.allowStun && event.entityPlayer.isPotionActive(TragicPotions.Stun))
		{
			if (event.isCancelable())
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void whileUsingItem(Tick event)
	{
		if (TragicNewConfig.allowStun && event.entityPlayer.isPotionActive(TragicPotions.Stun))
		{
			if (event.isCancelable())
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onDig(BreakEvent event)
	{
		if (event.getPlayer() != null && TragicNewConfig.allowStun && event.getPlayer().isPotionActive(TragicPotions.Stun))
		{
			if (event.isCancelable())
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onBreaking(BreakSpeed event)
	{
		if (TragicNewConfig.allowStun && event.entityPlayer.isPotionActive(TragicPotions.Stun))
		{
			if (event.isCancelable())
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onAttack(LivingAttackEvent event)
	{
		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase)
		{
			if (TragicNewConfig.allowHarmony && ((EntityLivingBase) event.source.getEntity()).isPotionActive(TragicPotions.Harmony))
			{
				if (event.isCancelable())
				{
					event.setCanceled(true);
				}
			}

			if (TragicNewConfig.allowStun && ((EntityLivingBase) event.source.getEntity()).isPotionActive(TragicPotions.Stun))
			{
				if (event.isCancelable())
				{
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent(priority=EventPriority.LOW)
	public void submissiveHurt(LivingHurtEvent event)
	{
		if (TragicNewConfig.allowSubmission && event.entityLiving.isPotionActive(TragicPotions.Submission))
		{
			double x = (event.entityLiving.getActivePotionEffect(TragicPotions.Submission).getAmplifier() + 2) / 3;

			if (x <= 0)
			{
				x = 0.1;
			}
			if (x > 3.0)
			{
				x = 3.0;
			}

			float f = (float) (event.ammount * x);
			event.ammount += f;

			event.entityLiving.motionX *= 1 + x;
			event.entityLiving.motionZ *= 1 + x;
			event.entityLiving.motionY *= 1 + x;
		}

		if (!(event.entityLiving instanceof EntityPlayer))
		{
			if (event.entityLiving.isPotionActive(Potion.confusion) || event.entityLiving.isPotionActive(Potion.blindness)
					|| TragicNewConfig.allowDisorientation && event.entityLiving.isPotionActive(TragicPotions.Disorientation)
					|| TragicNewConfig.allowFear && event.entityLiving.isPotionActive(TragicPotions.Fear))
			{
				event.ammount *= 1.05;
			}
		}
	}

	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void invulnerableHurt(LivingHurtEvent event)
	{
		if (TragicNewConfig.allowInvulnerability && event.entityLiving.isPotionActive(TragicPotions.Invulnerability))
		{
			if (event.isCancelable())
			{
				event.setCanceled(true);
			}

			if (event.source.getEntity() != null)
			{
				event.entityLiving.applyEntityCollision(event.source.getEntity());
			}

		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderHandWhileStunned(RenderHandEvent event)
	{
		if (TragicNewConfig.allowStun && Minecraft.getMinecraft().thePlayer.isPotionActive(TragicPotions.Stun))
		{
			if (event.isCancelable())
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void ignoreTargetsWithHarmony(LivingSetAttackTargetEvent event)
	{
		if (event.target != null && TragicNewConfig.allowHarmony && event.entityLiving.isPotionActive(TragicPotions.Harmony))
		{
			if (event.isCancelable())
			{
				event.setCanceled(true);
			}

			if (event.entityLiving instanceof EntityCreature && !(event.entityLiving instanceof EntityWither) && !(event.entityLiving instanceof EntityDragon)
					&& !(event.entityLiving instanceof TragicBoss))
			{
				((EntityCreature) event.entityLiving).setTarget(null);
				((EntityLiving) event.entityLiving).setAttackTarget(null);
			}
		}
	}

	@SubscribeEvent
	public void onCorruptedAttack(LivingAttackEvent event)
	{
		if (!TragicNewConfig.allowCorruption) return;

		if (event.entityLiving instanceof TragicMob)
		{
			TragicMob mob = (TragicMob)event.entityLiving;

			if (event.source.getEntity() instanceof EntityLivingBase)
			{
				EntityLivingBase source = (EntityLivingBase)event.source.getEntity();
				if (mob.isCorruptible)
				{
					if (source.isPotionActive(TragicPotions.Corruption.id))
					{
						mob.addPotionEffect(new PotionEffect(TragicPotions.Corruption.id, 240));
					}
				}
			}
		}
		else if (event.entityLiving instanceof EntityMob)
		{
			EntityMob mob = (EntityMob)event.entityLiving;

			if (event.source.getEntity() instanceof EntityLivingBase)
			{
				EntityLivingBase source = (EntityLivingBase)event.source.getEntity();

				if (source.isPotionActive(TragicPotions.Corruption.id))
				{
					mob.addPotionEffect(new PotionEffect(TragicPotions.Corruption.id, 240));
				}

			}
		}
		else if (event.entityLiving instanceof EntityLivingBase)
		{
			if (event.source.getEntity() instanceof EntityLivingBase)
			{
				EntityLivingBase source = (EntityLivingBase)event.source.getEntity();

				if (source.isPotionActive(TragicPotions.Corruption.id))
				{
					event.entityLiving.addPotionEffect(new PotionEffect(TragicPotions.Corruption.id, 240));
				}
			}
		}
	}
}
