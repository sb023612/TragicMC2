package tragicneko.tragicmc.entity.mob;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityStarVox extends EntityNorVox {

	public EntityStarVox(World par1World) {
		super(par1World);
		this.setSize(1.135F / 1.5F, 1.875F / 1.5F);
		this.canCorrupt = true;
		this.isCorruptible = true;
		this.isChangeable = true;
		//this.superiorForm = new EntityVoxStellarum(this.worldObj);
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.39);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.5);
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
	
	@Override
	protected void shootProjectiles()
	{
		return;
		/*
		double d0 = this.getAttackTarget().posX - this.posX;
		double d1 = this.getAttackTarget().boundingBox.minY + (double)(this.getAttackTarget().height / 3.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = this.getAttackTarget().posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(this.getDistanceToEntity(this.getAttackTarget())) * 0.95F;
		float f2 = this.rotationYaw;

		for (int i = 0; i < 2; i++)
		{
			EntityWitherSkull fireball = new EntityWitherSkull(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
			fireball.posY = this.posY + (this.height * 2 / 3);
			if (rand.nextBoolean())
			{
				fireball.setInvulnerable(true);
			}
			this.worldObj.spawnEntityInWorld(fireball);
		} */
	}
	
	@Override
	public void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Integer.valueOf((int) 0));
	}
	
	@SideOnly(Side.CLIENT)
	public int getTextureID()
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}
	
	public void setTextureID(int i)
	{
		this.dataWatcher.updateObject(16, i);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (tag.hasKey("texture")) this.setTextureID(tag.getInteger("texture"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("texture", this.dataWatcher.getWatchableObjectInt(16));
	}
	
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		this.setTextureID(rand.nextInt(8));
		return super.onSpawnWithEgg(data);
	}
	
	public int getTotalArmorValue()
	{
		return 12;
	}
}
