package tragicneko.tragicmc.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;

public class EntityDarkCrystal extends Entity {

	public EntityEnyvil owner;

	public EntityDarkCrystal(World world)
	{
		this(world, null);
	}

	public EntityDarkCrystal(World p_i1582_1_, EntityEnyvil owner) {
		super(p_i1582_1_);
		this.owner = owner;
		this.setSize(0.445F, 0.665F);
		this.preventEntitySpawning = true;
		this.yOffset = this.height / 2.0F;
		this.isImmuneToFire = true;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (this.worldObj.isRemote)
		{
			for (int i = 0; i < 2; i++)
			{
				this.worldObj.spawnParticle("witchMagic", this.posX + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), this.posY + 0.115D + rand.nextDouble(),
						this.posZ + ((rand.nextDouble() - rand.nextDouble()) * 0.355D), 0.0F, 0.155F * this.rand.nextFloat(), 0.0F);
			}
		}
		else
		{
			if (this.owner == null) this.setDead();

			this.incrementRotation();
			if (this.ticksExisted % 20 == 0 && rand.nextBoolean()) this.motionY = rand.nextDouble() * 0.375D - 0.165D;

			if (Math.abs(this.motionY) >= 0.725D || this.posY >= this.worldObj.getTopSolidOrLiquidBlock((int) this.posX, (int) this.posZ) + 10.0D) this.motionY *= 0.5D;

			if (Math.abs(this.motionX) >= 0.225D && Math.abs(this.motionX) <= 0.5D) this.motionX *= 1.125D;
			if (Math.abs(this.motionZ) >= 0.225D && Math.abs(this.motionZ) <= 0.5D) this.motionZ *= 1.125D;

			this.moveEntity(this.motionX, this.motionY, this.motionZ);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float par2)
	{
		if (this.worldObj.isRemote || this.isEntityInvulnerable()) return false;

		if (source.getEntity() != null)
		{
			this.setDead();
			this.setBeenAttacked();

			if (!this.worldObj.isRemote)
			{
				boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
				this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, 4.0F, flag);
			}
			return true;
		}
		return false;
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(4, Integer.valueOf(rand.nextInt(10000)));
	}

	private void incrementRotation()
	{
		int pow = this.dataWatcher.getWatchableObjectInt(4);
		this.dataWatcher.updateObject(4, ++pow);
	}

	public int getRotationTicks()
	{
		return this.dataWatcher.getWatchableObjectInt(4);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		if (tag.hasKey("position")) this.setPosition(tag.getIntArray("position")[0], tag.getIntArray("position")[1], tag.getIntArray("position")[2]);
		if (tag.hasKey("ownerID")) this.owner = (EntityEnyvil) this.worldObj.getEntityByID(tag.getInteger("ownerID"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		tag.setIntArray("position", new int[] {(int) this.posX, (int) this.posY, (int) this.posZ});
		if (this.owner != null) tag.setInteger("ownerID", this.owner.getEntityId());
	}

	@Override
	public void setFire(int i) {}

	@Override
	public void fall(float f) {}

	@Override
	public void onStruckByLightning(EntityLightningBolt bolt) {}
}
