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
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import tragicneko.tragicmc.entity.boss.EntityMegaCryse;
import tragicneko.tragicmc.entity.boss.TragicMiniBoss;
import tragicneko.tragicmc.main.TragicEntities;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.util.DamageHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityCryse extends TragicMob {

	public EntityCryse(World par1World) {
		super(par1World);
		this.setSize(0.4F, 1.45F);
		this.stepHeight = 1.0F;
		this.experienceValue = 12;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLivingBase.class, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.canCorrupt = true;
		this.isCorruptible = true;
		this.isChangeable = true;
		this.superiorForm = new EntityMegaCryse(par1World);
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return TragicEntities.Natural;
	}
	
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return 15728880;
	}

	public float getBrightness(float par1)
	{
		return 1.0F;
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(35.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.28);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{ 
		if (par1DamageSource.isFireDamage())
		{
			par2 *= 4;
		}

		if (par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1DamageSource.getEntity();

			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemPickaxe)
			{
				par2 *= 1.5;
			}
		}	
		
		if (super.attackEntityFrom(par1DamageSource, par2) && par1DamageSource.getEntity() != null && this.rand.nextInt(4) == 0)
		{
			par1DamageSource.getEntity().attackEntityFrom(DamageHelper.causeModMagicDamageToEntity(this), par2 / 4);
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean result = super.attackEntityAsMob(par1Entity);

		EnumDifficulty dif = this.worldObj.difficultySetting;
		int x = 1;

		if (dif == EnumDifficulty.EASY)
		{
			x = 2;
		}

		if (dif == EnumDifficulty.NORMAL)
		{
			x = 3;
		}

		if (dif == EnumDifficulty.HARD)
		{
			x = 4;
		}

		if (result && par1Entity instanceof EntityLivingBase && rand.nextInt(16 / x) == 0)
		{
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200 + rand.nextInt(320), 1 + rand.nextInt(4)));
		}

		if (result && par1Entity instanceof EntityLivingBase && rand.nextInt(32 / x) == 0)
		{
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 200 + rand.nextInt(320), 1 + rand.nextInt(4)));
		}

		return result;
	}

	public int getTotalArmorValue()
	{
		return 4;
	}

	public void onChange(World world, TragicMob entity, TragicMiniBoss boss, double par1, double par2, double par3) {

		if (!TragicNewConfig.allowMegaCryse)
		{
			return;
		}
		
		boss.copyLocationAndAnglesFrom(this);
		boss.onSpawnWithEgg((IEntityLivingData)null);
		world.removeEntity(this);
		world.spawnEntityInWorld(boss);
		boss.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 2));
		boss.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 2));

	}

}
