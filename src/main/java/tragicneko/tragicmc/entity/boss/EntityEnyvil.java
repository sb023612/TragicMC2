package tragicneko.tragicmc.entity.boss;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.EntityDarkCrystal;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.entity.projectile.EntityDarkLightning;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.DamageHelper;
import tragicneko.tragicmc.util.WorldHelper;

public class EntityEnyvil extends TragicBoss implements IMultiPart {

	public EntityPart[] enyvilParts;

	public EntityPart enyvilEye;
	public EntityPart enyvilShell;
	public EntityPart enyvilShell2;
	public EntityPart enyvilShell3;

	public EntityPart enyvilClaw;
	public EntityPart enyvilClaw2;

	public EntityDarkCrystal crystal;

	public EntityEnyvil(World par1World) {
		super(par1World);
		this.enyvilParts = new EntityPart[] {this.enyvilEye = new EntityPart(this, "enyvilEye", 1.0F, 1.0F), this.enyvilShell = new EntityPart(this, "enyvilShell", 2.5F, 1.5F),
				this.enyvilShell2 = new EntityPart(this, "enyvilShell2", 2.5F, 1.0F), this.enyvilClaw = new EntityPart(this, "enyvilClaw", 1.0F, 1.0F),
				this.enyvilClaw2 = new EntityPart(this, "enyvilClaw2", 1.0F, 1.0F), this.enyvilShell3 = new EntityPart(this, "enyvilShell3", 1.5F, 2.5F)};
		this.setSize(5.5F, 5.5F);
		this.setHealth(this.getMaxHealth());
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityGolem.class, 0, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true));
		this.isImmuneToFire = true;
		this.stepHeight = 2.5F;
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEAD;
	}

	public boolean canRenderOnFire()
	{
		return false;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0)); //attack time
		this.dataWatcher.addObject(17, Integer.valueOf(0)); //hurt time
		this.dataWatcher.addObject(18, Integer.valueOf(0)); //dark lightning ticks
		this.dataWatcher.addObject(19, Integer.valueOf(0)); //dark energy spray
		this.dataWatcher.addObject(20, Integer.valueOf(0)); //tractor beam
		this.dataWatcher.addObject(21, Integer.valueOf(0)); //laser shots
		this.dataWatcher.addObject(22, Integer.valueOf(0)); //thunderstorm
		this.dataWatcher.addObject(23, Integer.valueOf(0)); //current crystal id
		this.dataWatcher.addObject(24, Integer.valueOf(0)); //slam
		this.dataWatcher.addObject(25, Integer.valueOf(0)); //client-side sync for current target
	}

	public int getAttackTime()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	private void setAttackTime(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	private void decrementAttackTime()
	{
		int pow = this.getAttackTime();
		this.setAttackTime(--pow);
	}

	public int getHurtTime()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	private void setHurtTime(int i)
	{
		this.dataWatcher.updateObject(17, i);
	}

	private void decrementHurtTime()
	{
		int pow = this.getHurtTime();
		this.setHurtTime(--pow);
	}

	public int getLightningTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	private void setLightningTicks(int i)
	{
		this.dataWatcher.updateObject(18, i);
	}

	private void decrementLightningTicks()
	{
		int pow = this.getLightningTicks();
		this.setLightningTicks(--pow);
	}

	public int getDarkEnergyTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	private void setDarkEnergyTicks(int i)
	{
		this.dataWatcher.updateObject(19, i);
	}

	private void decrementDarkEnergyTicks()
	{
		int pow = this.getDarkEnergyTicks();
		this.setDarkEnergyTicks(--pow);
	}

	public int getTractorBeamTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	private void setTractorBeamTicks(int i)
	{
		this.dataWatcher.updateObject(20, i);
	}

	private void decrementTractorBeamTicks()
	{
		int pow = this.getTractorBeamTicks();
		this.setTractorBeamTicks(--pow);
	}

	public int getLaserTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	private void setLaserTicks(int i)
	{
		this.dataWatcher.updateObject(21, i);
	}

	private void decrementLaserTicks()
	{
		int pow = this.getLaserTicks();
		this.setLaserTicks(--pow);
	}

	public int getThunderstormTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(22);
	}

	private void setThunderstormTicks(int i)
	{
		this.dataWatcher.updateObject(22, i);
	}

	private void decrementThunderstormTicks()
	{
		int pow = this.getThunderstormTicks();
		this.setThunderstormTicks(--pow);
	}

	public int getCrystalID()
	{
		return this.dataWatcher.getWatchableObjectInt(23);
	}

	private void setCrystalID(int i)
	{
		this.dataWatcher.updateObject(23, i);
	}

	private boolean hasCrystal()
	{
		return this.crystal != null && !this.crystal.isDead;
	}

	public int getSlamTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(24);
	}

	private void setSlamTicks(int i)
	{
		this.dataWatcher.updateObject(24, i);
	}

	private void decrementSlamTicks()
	{
		int pow = this.getSlamTicks();
		this.setSlamTicks(--pow);
	}

	public int getAttackTargetID()
	{
		return this.dataWatcher.getWatchableObjectInt(25);
	}

	private void setAttackTargetID(int i)
	{
		this.dataWatcher.updateObject(25, i);
	}

	public EntityLivingBase getClientSideTarget()
	{
		Entity entity = this.worldObj.getEntityByID(this.getAttackTargetID());
		return (EntityLivingBase) (entity instanceof EntityLivingBase ? entity : null);
	}

	private boolean canUseNewAbility()
	{
		return this.hasCrystal() && this.getLightningTicks() == 0 && this.getDarkEnergyTicks() == 0 && this.getTractorBeamTicks() == 0 && this.getLaserTicks() == 0 && this.getThunderstormTicks() == 0 && this.getSlamTicks() == 0;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(450.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.276);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(24.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48.0);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		if (!this.worldObj.isRemote && TragicNewConfig.allowMobStatueDrops && rand.nextInt(100) <= TragicNewConfig.mobStatueDropChance && this.getAllowLoot()) this.entityDropItem(new ItemStack(TragicItems.MobStatue, 1, 14), 0.4F);

		if (!this.worldObj.isRemote)
		{
			List<EntityDarkCrystal> list = this.worldObj.getEntitiesWithinAABB(EntityDarkCrystal.class, this.boundingBox.expand(128.0D, 128.0D, 128.0D));
			Iterator ite = list.iterator();
			EntityDarkCrystal crystal;

			while (ite.hasNext())
			{
				crystal = (EntityDarkCrystal) ite.next();
				crystal.attackEntityFrom(DamageSource.causeMobDamage(this), 10000.0F);
			}
		}
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		if (!this.worldObj.isRemote) this.createNewCrystals();
		return super.onSpawnWithEgg(data);
	}

	public void onLivingUpdate()
	{		
		this.fallDistance = 0.0F;
		super.onLivingUpdate();

		if (!this.canUseNewAbility()) this.motionX = this.motionY = this.motionZ = 0.0D;
		if (!this.canUseNewAbility()) this.motionY = -0.1D;

		if (this.getTractorBeamTicks() > 0) this.tractorBeamEntity();
		if (this.getLaserTicks() > 0 && this.getLaserTicks() % 50 == 0) this.useLasers();

		if (worldObj.isRemote)
		{
			if (this.hasCrystal())
			{
				for (int i = 0; i < 3; i++)
				{
					this.worldObj.spawnParticle("witchMagic", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 1.455D), this.posY + 2.415D + rand.nextDouble(),
							this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 1.455D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
				}

				if (this.getSlamTicks() > 0)
				{
					for (int i = 0; i < 12; i++)
					{
						this.worldObj.spawnParticle("portal", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 4.455D), this.posY + 2.415D + rand.nextDouble(),
								this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 4.455D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
					}

					if (this.getSlamTicks() == 5)
					{
						for (int i = 0; i < 12; i++)
						{
							this.worldObj.spawnParticle("portal", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 4.455D), this.posY + 2.415D + rand.nextDouble(),
									this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 4.455D), rand.nextFloat() - rand.nextFloat(), 0.155F * this.rand.nextFloat(), rand.nextFloat() - rand.nextFloat());
						}
					}
				}
			}
			return;
		}

		for (int i = 0; i < this.enyvilParts.length; i++)
		{
			if (enyvilParts[i].isBurning()) enyvilParts[i].extinguish();
			enyvilParts[i].onUpdate();
		}

		float f1 = this.rotationYaw * (float)Math.PI / 180.0F;
		float f2 = MathHelper.sin(f1);
		float f3 = MathHelper.cos(f1);
		double d = 1.225D;

		this.enyvilEye.setPosition(this.posX - (f2 * 0.025D), this.posY + 3.5D, this.posZ - (f3 * 0.025D));
		this.enyvilShell.setPosition(this.posX - (f2 * 0.25D), this.posY + 4.5D, this.posZ + (f3 * 0.25D));
		this.enyvilShell2.setPosition(this.posX - (f2 * 0.25D), this.posY + 2.0D, this.posZ + (f3 * 0.25D));
		this.enyvilShell3.setPosition(this.posX - (f2 * 0.95D), this.posY + 2.5D, this.posZ - (f3 * 0.95D));
		this.enyvilClaw.setPosition(this.posX + (f3 * 0.875D), this.posY, this.posZ + (f2 * 0.875D));
		this.enyvilClaw2.setPosition(this.posX - (f3 * 0.875D), this.posY, this.posZ - (f2 * 0.875D));

		this.decrementValues();
		this.updateCrystal();
		this.updateTargetInfo();

		if (this.getAttackTarget() == null)
		{
			if (this.getLightningTicks() > 0) this.setLightningTicks(0);
			if (this.getDarkEnergyTicks() > 0) this.setDarkEnergyTicks(0);
			if (this.getTractorBeamTicks() > 0) this.setTractorBeamTicks(0);
			if (this.getLaserTicks() > 0) this.setLaserTicks(0);
			if (this.getThunderstormTicks() > 0) this.setThunderstormTicks(0);
		}
		else
		{
			if (this.canUseNewAbility() && this.isEntityInRange(this.getAttackTarget(), 6.0F, 20.0F) && rand.nextInt(48) == 0) this.setLightningTicks(100);
			if (this.getLightningTicks() > 0 && this.getLightningTicks() % 20 == 0) this.useDarkLightning();

			if (this.canUseNewAbility() && this.isEntityInRange(this.getAttackTarget(), 5.0F, 16.0F) && rand.nextInt(96) == 0) this.setDarkEnergyTicks(160);
			if (this.getDarkEnergyTicks() > 0) this.useDarkEnergySpray();

			if (this.canUseNewAbility() && this.isEntityInRange(this.getAttackTarget(), 12.0F, 28.0F) && rand.nextInt(128) == 0 && this.canEntityBeSeen(this.getAttackTarget())) this.setTractorBeamTicks(200);

			if (this.canUseNewAbility() && this.isEntityInRange(this.getAttackTarget(), 8.0F, 24.0F) && rand.nextInt(64) == 0 && !this.canEntityBeSeen(this.getAttackTarget())) this.setLaserTicks(200);

			if (this.canUseNewAbility() && this.isEntityInRange(this.getAttackTarget(), 6.0F, 18.0F) && rand.nextInt(256) == 0) this.setThunderstormTicks(120);
			if (this.getThunderstormTicks() > 0 && this.getThunderstormTicks() % 10 == 0) this.createThunderstorm();

			if (this.canUseNewAbility() && this.getDistanceToEntity(this.getAttackTarget()) <= 6.0F && rand.nextInt(48) == 0) this.setSlamTicks(40);
			if (this.getSlamTicks() == 4) this.useSlam();

			if (this.ticksExisted % 60 == 0 && rand.nextInt(4) == 0 && this.getDistanceToEntity(this.getAttackTarget()) <= 20.0F)
			{
				for (int meow = 0; meow < 4 + rand.nextInt(3); meow++)
				{
					double d0 = this.posX + getIntegerInRange(6, 22);
					double d1 = this.posZ + getIntegerInRange(6, 22);
					double d2 = this.worldObj.getTopSolidOrLiquidBlock((int) d0, (int) d1);
					this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, d0, d2, d1));
					this.worldObj.createExplosion(this, d0, d2, d1, rand.nextFloat() * 3.0F + 1.5F, this.getMobGriefing());
				}
			} 
		}

		if (this.isCollidedHorizontally && this.getMobGriefing()) this.destroyBlocks();
	}

	private void destroyBlocks() {
		ArrayList<int[]> list = WorldHelper.getBlocksInSphericalRange(this.worldObj, this.width - 0.725D, this.posX, this.posY + this.height / 2.0D + 1.2D, this.posZ);
		int[] coords;
		Block block;

		for (int i = 0; i < list.size(); i++)
		{
			coords = list.get(i);
			block = this.worldObj.getBlock(coords[0], coords[1], coords[2]);

			if (!block.isAir(this.worldObj, coords[0], coords[1], coords[2]) && block.canEntityDestroy(this.worldObj, coords[0], coords[1], coords[2], new EntityDragon(this.worldObj)))
			{
				this.worldObj.func_147480_a(coords[0], coords[1], coords[2], true);
			}
		}

	}

	private void updateTargetInfo() {
		if (this.getAttackTarget() == null) 
		{
			this.setAttackTargetID(0);
		}
		else if (!this.getAttackTarget().isDead)
		{
			this.setAttackTargetID(this.getAttackTarget().getEntityId());
		}
		else
		{
			this.setAttackTargetID(0);
		}
	}

	private void decrementValues() {
		if (this.getAttackTime() > 0) this.decrementAttackTime();
		if (this.getHurtTime() > 0) this.decrementHurtTime();
		if (this.getLightningTicks() > 0) this.decrementLightningTicks();
		if (this.getDarkEnergyTicks() > 0) this.decrementDarkEnergyTicks();
		if (this.getTractorBeamTicks() > 0) this.decrementTractorBeamTicks();
		if (this.getLaserTicks() > 0) this.decrementLaserTicks();
		if (this.getThunderstormTicks() > 0) this.decrementThunderstormTicks();
		if (this.getSlamTicks() > 0) this.decrementSlamTicks();
	}

	private void updateCrystal() {
		Entity entity = this.worldObj.getEntityByID(this.getCrystalID());
		if (entity != null && entity instanceof EntityDarkCrystal && !entity.isDead)
		{
			this.crystal = (EntityDarkCrystal) entity;
		}
		else
		{
			this.crystal = null;
		}

		if (this.crystal != null)
		{
			if (this.ticksExisted % 10 == 0 && this.getHealth() < this.getMaxHealth()) this.heal(1.0F);
			this.crystal.motionX = this.motionX;
			this.crystal.motionZ = this.motionZ;

			if (this.getDistanceToEntity(this.crystal) >= 18.0F)
			{
				this.crystal.setPosition(this.posX + getIntegerInRange(4, 8), this.posY + Math.abs(getIntegerInRange(4, 8)), this.posZ + getIntegerInRange(4, 8));
				this.crystal.playSound("mob.endermen.portal", 0.4F, 0.4F);
			}
		}
		else
		{
			float f = 32.0F;
			List list = this.worldObj.getEntitiesWithinAABB(EntityDarkCrystal.class, this.boundingBox.expand((double)f, (double)f, (double)f));
			EntityDarkCrystal crystal = null;
			double d0 = Double.MAX_VALUE;
			Iterator iterator = list.iterator();

			while (iterator.hasNext())
			{
				EntityDarkCrystal crystal1 = (EntityDarkCrystal)iterator.next();
				double d1 = crystal1.getDistanceSqToEntity(this);

				if (d1 < d0)
				{
					d0 = d1;
					crystal = crystal1;
				}
				crystal1.motionX = this.motionX;
				crystal1.motionZ = this.motionZ;
			}

			this.crystal = crystal;
			if (crystal != null) this.setCrystalID(crystal.getEntityId());

			if (!this.hasCrystal() && rand.nextInt(16) == 0)
			{
				this.createNewCrystals();
			}
		}
	}

	public void createNewCrystals() {
		List<EntityDarkCrystal> list = this.worldObj.getEntitiesWithinAABB(EntityDarkCrystal.class, this.boundingBox.expand(64.0D, 64.0D, 64.0D));
		if (list.size() >= 5) return;

		for (int i = 0; i < rand.nextInt(3) + 2; i++)
		{
			EntityDarkCrystal crystal = new EntityDarkCrystal(this.worldObj, this);
			crystal.setPosition(this.posX + getIntegerInRange(4, 12), this.posY + Math.abs(getIntegerInRange(4, 12)), this.posZ + getIntegerInRange(4, 12));
			this.worldObj.spawnEntityInWorld(crystal);
		}
	}

	private void useDarkLightning() {
		if (!this.canEntityBeSeen(this.getAttackTarget())) return;

		double d0 = this.getAttackTarget().posX - this.posX;
		double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = this.getAttackTarget().posZ - this.posZ;

		float f = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.4875F;

		EntityDarkLightning lightning = new EntityDarkLightning(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f, d1, d2 + this.rand.nextGaussian() * (double)f);
		lightning.posX = this.posX + (d0 * 0.115D);
		lightning.posY = this.posY + 3.75D;
		lightning.posZ = this.posZ + (d0 * 0.115D);
		this.worldObj.spawnEntityInWorld(lightning);
	}

	private void useDarkEnergySpray() {
		for (int i = 0; i < rand.nextInt(4) + 3; i++)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.1875F;

			EntityDarkEnergy energy = new EntityDarkEnergy(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f, d1, d2 + this.rand.nextGaussian() * (double)f);
			energy.posX = this.posX + (d0 * 0.115D);
			energy.posY = this.posY + 3.75D;
			energy.posZ = this.posZ + (d0 * 0.115D);
			this.worldObj.spawnEntityInWorld(energy);
		}
	}

	public void tractorBeamEntity()
	{
		if (this.worldObj.isRemote && this.getClientSideTarget() == null || !this.worldObj.isRemote && this.getAttackTarget() == null) return;

		EntityLivingBase entity = this.worldObj.isRemote ? this.getClientSideTarget() : this.getAttackTarget();

		if (entity instanceof EntityPlayer)
		{
			if (!((EntityPlayer)entity).capabilities.isCreativeMode)
			{
				double d1 = entity.posX - this.posX;
				double d2 = entity.posZ - this.posZ;
				double d3 = entity.posY - this.posY;
				float f2 = MathHelper.sqrt_double(d1 * d1 + d2 * d2 + d3 * d3);
				double d4 = 0.25D;

				entity.motionX = -d1 / (double)f2 * d4 * 0.200000011920929D + entity.motionX * 0.30000000298023224D;
				entity.motionZ = -d2 / (double)f2 * d4 * 0.200000011920929D + entity.motionZ * 0.30000000298023224D;
				entity.motionY = -d3 / (double)f2 * d4 * 0.140000011920929D + entity.motionZ * 0.30000000298023224D;
				entity.moveEntity(entity.motionX, entity.motionY, entity.motionZ);
			}
		}
		else
		{
			double d1 = entity.posX - this.posX;
			double d2 = entity.posZ - this.posZ;
			double d3 = entity.posY - this.posY;
			float f2 = MathHelper.sqrt_double(d1 * d1 + d2 * d2 + d3 * d3);
			double d4 = 0.5D;

			entity.motionX = -d1 / (double)f2 * d4 * 0.200000011920929D + entity.motionX * 0.30000000298023224D;
			entity.motionZ = -d2 / (double)f2 * d4 * 0.200000011920929D + entity.motionZ * 0.30000000298023224D;
			entity.motionY = -d3 / (double)f2 * d4 * 0.100000011920929D + entity.motionZ * 0.30000000298023224D;
		}

		if (this.worldObj.isRemote && this.getClientSideTarget() != null)
		{
			double d3 = this.getClientSideTarget().posX - this.posX + 0.5D;
			double d4 = this.getClientSideTarget().posY - this.posY - 1.85D;
			double d5 = this.getClientSideTarget().posZ - this.posZ + 0.5D;

			for (int i = 0; i < 8; i++)
			{
				double d6 = 0.12D * i + (rand.nextDouble() * 0.25D);
				this.worldObj.spawnParticle("witchMagic", this.posX + d3 * d6, this.posY + d4 * d6 + 2.45D, this.posZ + d5 * d6, 0.0, 0.0, 0.0);
			}
		}
	}

	private void useLasers()
	{
		if (this.worldObj.isRemote && this.getClientSideTarget() == null || !this.worldObj.isRemote && this.getAttackTarget() == null) return;

		float f = 32.0F;
		List list = this.worldObj.getEntitiesWithinAABB(EntityDarkCrystal.class, this.boundingBox.expand((double)f, (double)f, (double)f));
		EntityDarkCrystal crystal = null;
		Iterator iterator = list.iterator();

		while (iterator.hasNext())
		{
			crystal = (EntityDarkCrystal)iterator.next();
			if (this.worldObj.isRemote)
			{
				if (this.getClientSideTarget().canEntityBeSeen(crystal)) break;
			}
			else 
			{
				if (this.getAttackTarget().canEntityBeSeen(crystal)) break;
			}
		}

		if (crystal != null && !crystal.isDead)
		{
			if (!this.worldObj.isRemote && crystal.getDistanceToEntity(this.getAttackTarget()) <= 16.0F)
			{
				this.getAttackTarget().attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this), 2.5F * rand.nextFloat() + 1.0F);
				this.getAttackTarget().setFire(4);
			}
			else if (this.worldObj.isRemote)
			{
				double d0 = crystal.posX - this.posX + 0.5D;
				double d1 = crystal.posY - this.posY + 2.45D;
				double d2 = crystal.posZ - this.posZ + 0.5D;

				for (int i = 0; i < 8; i++)
				{
					double d3 = 0.12D * i + (rand.nextDouble() * 0.25D);
					this.worldObj.spawnParticle("flame", this.posX + d0 * d3, this.posY + d1 * d3 + 0.45D, this.posZ + d2 * d3, 0.0, 0.0, 0.0);
				}
			}
		}
	}

	private void createThunderstorm()
	{
		for (int i = 0; i < 3 + rand.nextInt(3); i++)
		{
			double d0 = this.posX + this.getIntegerInRange(4, 16) + rand.nextDouble();
			double d1 = this.posZ + this.getIntegerInRange(4, 16) + rand.nextDouble();
			double d2 = this.worldObj.getTopSolidOrLiquidBlock((int) d0, (int) d1);

			this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, d0, d2, d1));
			this.worldObj.createExplosion(this, d0, d2, d1, rand.nextFloat() * 2.0F + 0.5F, this.getMobGriefing());
		}
	}

	private void useSlam()
	{
		List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(5.0D, 5.0D, 5.0D));
		if (!list.isEmpty())
		{
			this.attackEntitiesInList(list);
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, rand.nextFloat() * 2.0F + 3.5F, false);
		}
	}	

	public boolean canBeCollidedWith()
	{
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		return source.isExplosion() ? false : this.attackEntityFromPart(this.enyvilShell, source, damage);
	}

	@Override
	public World getWorld() {
		return this.worldObj;
	}

	@Override
	public boolean attackEntityFromPart(EntityPart entity, DamageSource source, float damage) {

		if (this.worldObj.isRemote || source.isExplosion() || source == DamageSource.drown) return false;

		if (source == DamageSource.inWall)
		{
			if (this.getMobGriefing()) this.destroyBlocks();
			return false;
		}
		
		if (!this.hasCrystal()) damage /= 2;

		if (entity == this.enyvilEye)
		{			
			TragicMC.logInfo("Enyvil hit in the eye.");
			if (super.attackEntityFrom(source, damage))
			{
				if (rand.nextInt(4) == 0 && !this.hasCrystal())
				{
					this.createNewCrystals();
				}
			}

			if (this.getHurtTime() == 0) this.setHurtTime(10);
			this.playSound("mob.enderdragon.growl", 1.0F, 0.2F);
		}
		else
		{
			super.attackEntityFrom(source, (damage / 6.0F) + 1.0F);
		}

		return true;
	}

	@Override
	public Entity[] getParts()
	{
		return this.enyvilParts;
	}

	@Override
	public void addPotionEffect(PotionEffect effect) {}

	@Override
	public void setFire(int i) {}

	@Override
	public void fall(float f) {}

	@Override
	public void onStruckByLightning(EntityLightningBolt bolt) {}

	private void attackEntitiesInList(List list)
	{
		for (int i = 0; i < list.size(); ++i)
		{
			Entity entity = (Entity)list.get(i);

			if (entity instanceof EntityLivingBase)
			{
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), 12.0F);
				if (TragicNewConfig.allowFear) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(TragicPotions.Fear.id, 60 + rand.nextInt(160), 1)); 

				entity.motionX *= 3.225D;
				entity.motionZ *= 3.225D;
				entity.motionY += 0.225D;
			}
		}
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote || this.getDistanceToEntity(par1Entity) > 6.0F) return false;

		boolean result = super.attackEntityAsMob(par1Entity);

		if (result)
		{
			par1Entity.motionX *= 4.225D;
			par1Entity.motionZ *= 4.225D;
			par1Entity.motionY += 0.625D;
			if (this.getAttackTime() == 0) this.setAttackTime(10);
			
			if (this.getTractorBeamTicks() > 0)
			{
				par1Entity.motionX *= 2;
				par1Entity.motionZ *= 2;
				this.setTractorBeamTicks(0);
			}
		}

		return result;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
		if (tag.hasKey("hurtTime")) this.setHurtTime(tag.getInteger("hurtTime"));
		if (tag.hasKey("lightningTicks")) this.setLightningTicks(tag.getInteger("lightningTicks"));
		if (tag.hasKey("darkEnergyTicks")) this.setDarkEnergyTicks(tag.getInteger("darkEnergyTicks"));
		if (tag.hasKey("tractorBeamTicks")) this.setTractorBeamTicks(tag.getInteger("tractorBeamTicks"));
		if (tag.hasKey("laserTicks")) this.setLaserTicks(tag.getInteger("laserTicks"));
		if (tag.hasKey("thunderstormTicks")) this.setThunderstormTicks(tag.getInteger("thunderstormTicks"));
		if (tag.hasKey("crystalID")) this.setCrystalID(tag.getInteger("crystalID"));
		if (tag.hasKey("slamTicks")) this.setSlamTicks(tag.getInteger("slamTicks"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("attackTime", this.getAttackTime());
		tag.setInteger("hurtTime", this.getHurtTime());
		tag.setInteger("lightningTicks", this.getLightningTicks());
		tag.setInteger("darkEnergyTicks", this.getDarkEnergyTicks());
		tag.setInteger("tractorBeamTicks", this.getTractorBeamTicks());
		tag.setInteger("laserTicks", this.getLaserTicks());
		tag.setInteger("thunderstormTicks", this.getThunderstormTicks());
		tag.setInteger("crystalID", this.getCrystalID());
		tag.setInteger("slamTicks", this.getSlamTicks());
	}
}
