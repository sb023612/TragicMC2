package tragicneko.tragicmc.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.TragicMob;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.util.DamageHelper;

public class EntityKragul extends EntityGragul implements TragicMiniBoss {

	private int timeSinceFiring;

	public EntityKragul(World par1World) {
		super(par1World);
		this.setSize(0.225F * 2.115F, 0.515F * 2.115F);
		this.stepHeight = 1.0F;
		this.experienceValue = 16;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.38);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote) return; 

		++this.timeSinceFiring;

		if (this.getAttackTarget() != null && this.getDistanceToEntity(this.getAttackTarget()) > 4.0F && this.getDistanceToEntity(this.getAttackTarget()) <= 16.0F && rand.nextInt(16) == 0
				&& this.canEntityBeSeen(this.getAttackTarget()) && this.timeSinceFiring >= 60)
		{
			double d0 = this.getAttackTarget().posX - this.posX;
			double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
			double d2 = this.getAttackTarget().posZ - this.posZ;

			float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.75F;

			for (int i = 0; i < 3; i++)
			{
				EntitySpiritCast fireball = new EntitySpiritCast(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
				fireball.posX = this.posX + d0 * 0.115D;
				fireball.posY = this.posY + this.height;
				fireball.posZ = this.posZ + d0 * 0.115D;
				this.worldObj.spawnEntityInWorld(fireball);
				this.timeSinceFiring = 0;
			}
		}
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (this.worldObj.isRemote || !(par1Entity instanceof EntityLivingBase)) return false;

		boolean result = par1Entity.attackEntityFrom(DamageHelper.causeSuffocationDamageFromMob(this), ((EntityLivingBase) par1Entity).getMaxHealth() / 5);

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
				else if (rand.nextInt(16) == 0)
				{
					if (TragicNewConfig.allowStun) ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(TragicPotions.Stun.id, 120));
				}
			}
		}

		return result;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("waitTime")) this.timeSinceFiring = tag.getInteger("waitTime");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("waitTime", this.timeSinceFiring);
	}

	@Override
	protected boolean isChangeAllowed() {
		return false;
	}

	@Override
	public Class getLesserForm() {
		return EntityGragul.class;
	}

}
