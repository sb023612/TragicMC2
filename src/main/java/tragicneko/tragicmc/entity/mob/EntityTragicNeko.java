package tragicneko.tragicmc.entity.mob;

import java.util.Calendar;
import java.util.UUID;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.main.TragicItems;

public class EntityTragicNeko extends TragicMob {
	
	private AttributeModifier mod = new AttributeModifier(UUID.fromString("ef7bc471-3df8-4d0d-8aa6-8f52ae0a6045"), "tragicNekoSpeedDebuff", -0.50, 0);

	public EntityTragicNeko(World par1World) {
		super(par1World);
		this.setSize(0.4F, 1.955F);
		this.experienceValue = 16;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.65D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.85D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	public boolean canCorrupt()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf(0));
		this.dataWatcher.addObject(17, Integer.valueOf(0));
		this.dataWatcher.addObject(18, Integer.valueOf(0));
		this.dataWatcher.addObject(19, Integer.valueOf(0));
	}

	public int getFiringTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

	private void setFiringTicks(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}

	private void incrementFiringTicks()
	{
		int pow = this.getFiringTicks();
		this.setFiringTicks(++pow);
	}

	public boolean isAboutToFire()
	{
		return this.getFiringTicks() > 0 && this.getFiringTicks() <= 60;
	}

	public boolean hasFired()
	{
		return this.getFiringTicks() > 60;
	}

	public boolean canFire()
	{
		return this.getFiringTicks() >= 120;
	}

	public int getThrowingTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	private void setThrowingTicks(int i)
	{
		this.dataWatcher.updateObject(17, i);
	}

	private void decrementThrowingTicks()
	{
		int pow = this.getThrowingTicks();
		this.setThrowingTicks(--pow);
	}

	public int getAttackTime()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	private void setAttackTime(int i)
	{
		this.dataWatcher.updateObject(18, i);
	}

	private void decrementAttackTime()
	{
		int pow = this.getAttackTime();
		this.setAttackTime(--pow);
	}

	public int getFlickTime()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	private void setFlickTime(int i)
	{
		this.dataWatcher.updateObject(19, i);
	}

	private void decrementFlickTime()
	{
		int pow = this.getFlickTime();
		this.setFlickTime(--pow);
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.335);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
	}

	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	public void setInWeb() {}

	public int getTotalArmorValue()
	{
		return 0;
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.getAttackTarget() != null) this.incrementFiringTicks();
		if (this.getThrowingTicks() > 0) this.decrementThrowingTicks();
		if (this.getAttackTime() > 0) this.decrementAttackTime();
		if (this.getFlickTime() > 0) this.decrementFlickTime();

		int i = this.getHealth() <= this.getMaxHealth() / 2 ? 4 : 16;

		if (this.getAttackTarget() != null && rand.nextInt(i) == 0 && this.canFire() && this.ticksExisted % 20 == 0 && this.getThrowingTicks() == 0 && this.getAttackTime() == 0
				&& this.getDistanceToEntity(this.getAttackTarget()) >= 2.0F)
		{
			this.setFiringTicks(0);
		}

		if (this.getAttackTarget() != null)
		{
			if (this.getFlickTime() > 0) this.setFlickTime(0);
			
			if (this.getFiringTicks() == 60)
			{
				this.doMissleAttack();
			}
			else if (this.hasFired() && rand.nextInt(8) == 0 && this.getFiringTicks() % 20 == 0 && this.getAttackTime() == 0)
			{
				this.throwRandomProjectile();
				this.setThrowingTicks(20);
			}
		}
		else
		{
			if (this.ticksExisted % 20 == 0 && rand.nextInt(4) == 0) this.setFlickTime(10);
			this.setFiringTicks(65);
		}
		
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(mod);
		if (this.isAboutToFire() || this.getThrowingTicks() > 0) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(mod);
		
	}
	
	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		if (!this.worldObj.isRemote) this.setThrowingTicks(20);
	}

	public void onDeathUpdate()
	{
		super.onDeathUpdate();

		if (this.worldObj.isRemote) return;

		if (this.deathTime % 10 == 0)
		{
			EntityNekoStickyBomb bomb = new EntityNekoStickyBomb(this.worldObj, this);

			bomb.posY = this.posY + 0.1;
			bomb.posX = this.posX + rand.nextDouble() * 1.000035;
			bomb.posZ = this.posZ + rand.nextDouble() * 1.000035;
			bomb.motionY = rand.nextDouble() * 0.35;
			bomb.motionZ = (rand.nextDouble() - 0.55) * 0.55;
			bomb.motionX = (rand.nextDouble() - 0.55) * 0.55;

			this.worldObj.spawnEntityInWorld(bomb);
		}
		else if (this.deathTime == 20)
		{
			boolean flag = false;
			int x = 1;

			if (this.rand.nextInt(10) == 0)
			{
				flag = true;
			}

			if (!flag)
			{
				x = rand.nextInt(3) + 2;
			}

			for (int i = 0; i < x; i++)
			{
				EntityNekoClusterBomb bomb = new EntityNekoClusterBomb(this.worldObj, this);
				bomb.posY = this.posY + 0.2;
				bomb.posX = this.posX + rand.nextDouble() * 1.000035;
				bomb.posZ = this.posZ + rand.nextDouble() * 1.000035;
				bomb.motionY = 0.6;
				bomb.motionZ = (rand.nextDouble() - 0.55) * 0.15;
				bomb.motionX = (rand.nextDouble() - 0.55) * 0.15;

				this.worldObj.spawnEntityInWorld(bomb);
			}
		}
	}

	private void doMissleAttack()
	{
		EntityLivingBase entity = this.getAttackTarget();
		double d0 = entity.posX - this.posX;
		double d1 = entity.boundingBox.minY + (double)(this.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = entity.posZ - this.posZ;
		EntityNekoRocket rocket = new EntityNekoRocket(this.worldObj, this, d0, d1, d2);
		rocket.shootingEntity = this;
		rocket.posY = this.posY + (0.5D);
		rocket.posX = this.posX - (Math.sin(this.rotationYaw * (float)Math.PI / 180.0F) * 0.025D);
		rocket.posZ = this.posZ - (Math.cos(this.rotationYaw * (float)Math.PI / 180.0F) * 0.025D);
		this.worldObj.spawnEntityInWorld(rocket);
	}

	private void throwRandomProjectile() 
	{
		EntityThrowable theProjectile = null;

		switch (rand.nextInt(5))
		{
		case 1:
			theProjectile = new EntityNekoStickyBomb(this.worldObj, this);
			break;
		case 2:
			theProjectile = new EntityNekoClusterBomb(this.worldObj, this);
			break;
		case 4:
			theProjectile = new EntityNekoStickyBomb(this.worldObj, this);
			break;
		default:
			theProjectile = new EntityNekoMiniBomb(this.worldObj, this);
			break;
		}
		
		theProjectile.motionX = (this.getAttackTarget().posX - this.posX) * 0.335D;
		theProjectile.motionZ = (this.getAttackTarget().posZ - this.posZ) * 0.335D;
		this.worldObj.spawnEntityInWorld(theProjectile);
	}

	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.boundingBox.minY);

		if (i <= 63)
		{
			return false;
		}
		else
		{
			int j = MathHelper.floor_double(this.posX);
			int k = MathHelper.floor_double(this.posZ);
			int l = this.worldObj.getBlockLightValue(j, i, k);
			byte b0 = 4;
			Calendar calendar = this.worldObj.getCurrentDate();

			if ((calendar.get(2) + 1 != 10 || calendar.get(5) < 20) && (calendar.get(2) + 1 != 11 || calendar.get(5) > 3))
			{
				if (this.rand.nextBoolean())
				{
					return false;
				}
			}
			else
			{
				b0 = 7;
			}

			return l > this.rand.nextInt(b0) ? false : super.getCanSpawnHere();
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (par1DamageSource.isExplosion() || this.worldObj.isRemote) return false;
		
		if (this.getFiringTicks() < 60) this.setFiringTicks(61);
		boolean result = super.attackEntityFrom(par1DamageSource, par2);
		if (result) this.setAttackTime(10);

		return result;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.getThrowingTicks() > 0) return false;
		if (this.getFiringTicks() < 60) this.setFiringTicks(61);
		boolean result = super.attackEntityAsMob(par1Entity);
		if (result) this.setAttackTime(10);
		return result;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("attackTime")) this.setAttackTime(tag.getInteger("attackTime"));
		if (tag.hasKey("firingTicks")) this.setFiringTicks(tag.getInteger("firingTicks"));
		if (tag.hasKey("throwingTicks")) this.setThrowingTicks(tag.getInteger("throwingTicks"));
		if (tag.hasKey("flickTime")) this.setFlickTime(tag.getInteger("flickTime"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("attackTime", this.getAttackTime());
		tag.setInteger("firingTicks", this.getFiringTicks());
		tag.setInteger("throwingTicks", this.getThrowingTicks());
		tag.setInteger("flickTime", this.getFlickTime());
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

}
