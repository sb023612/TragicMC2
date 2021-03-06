package tragicneko.tragicmc.entity.mob;

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
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.EntityJarra;
import tragicneko.tragicmc.entity.boss.EntityKragul;
import tragicneko.tragicmc.entity.boss.TragicMiniBoss;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityGragul extends TragicMob {

	public EntityGragul(World par1World) {
		super(par1World);
		this.setSize(0.225F, 0.495F);
		this.stepHeight = 1.0F;
		this.experienceValue = 8;
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setCanSwim(false);
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.isImmuneToFire = true;
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

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.35);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return;

		if (this.superiorForm == null && !(this instanceof TragicMiniBoss)) this.superiorForm = new EntityKragul(this.worldObj);

		if (this.getAttackTarget() != null && this.ticksExisted % 120 == 0)
		{
			if (this.getAttackTarget() instanceof EntityPlayer && TragicNewConfig.allowInhibit && this.canEntityBeSeen(this.getAttackTarget()))
			{
				((EntityPlayer) this.getAttackTarget()).addPotionEffect(new PotionEffect(TragicPotions.Inhibit.id, 200));
			}
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (this.worldObj.isRemote ||par1DamageSource.isProjectile() || par1DamageSource.isFireDamage()) return false;

		int dif = this.worldObj.difficultySetting.getDifficultyId();

		if (dif == 3)
		{
			par2 = MathHelper.clamp_float(par2, 0.0F, 1.0F);
		}
		else if (dif == 2)
		{
			par2 = MathHelper.clamp_float(par2, 0.0F, 1.5F);
		}
		else
		{
			par2 = MathHelper.clamp_float(par2, 0.0F, 2.5F);
		}

		boolean result = super.attackEntityFrom(par1DamageSource, par2);

		if (result)
		{
			this.hurtResistantTime = 40;
			if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityLivingBase && rand.nextInt(6) == 0)
			{
				if (par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.getEntity()).capabilities.isCreativeMode) return result; 
				((EntityLivingBase) par1DamageSource.getEntity()).addPotionEffect(new PotionEffect(Potion.blindness.id, 60, 1));
			}
		}

		return result;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote) return false;

		if (par1Entity instanceof EntityLivingBase || par1Entity instanceof EntityPlayer)
		{
			boolean result = par1Entity.attackEntityFrom(DamageHelper.causeSuffocationDamageFromMob(this), ((EntityLivingBase) par1Entity).getMaxHealth() / 10);

			if (result)
			{
				if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
				{
					if (rand.nextInt(4) == 0)
					{
						((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 120));
					}
					else if (rand.nextInt(8) == 0)
					{
						((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 120));
					}
				}
			}

			return result;
		}
		else
		{
			return false;
		}
	}

	@Override
	protected boolean isChangeAllowed() {
		return TragicNewConfig.allowKragul;
	}

	@Override
	public void heal(float f) {}

	@Override
	public void fall(float f) {}
}
