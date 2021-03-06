package tragicneko.tragicmc.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.client.gui.GuiAmuletStatus;
import tragicneko.tragicmc.client.gui.GuiDoom;
import tragicneko.tragicmc.client.model.ModelAbomination;
import tragicneko.tragicmc.client.model.ModelGragul;
import tragicneko.tragicmc.client.model.ModelGreaterStin;
import tragicneko.tragicmc.client.model.ModelInkling;
import tragicneko.tragicmc.client.model.ModelJarra;
import tragicneko.tragicmc.client.model.ModelKragul;
import tragicneko.tragicmc.client.model.ModelMinotaur;
import tragicneko.tragicmc.client.model.ModelPlague;
import tragicneko.tragicmc.client.model.ModelPumpkinhead;
import tragicneko.tragicmc.client.model.ModelRagr;
import tragicneko.tragicmc.client.model.ModelSirv;
import tragicneko.tragicmc.client.model.ModelStinKing;
import tragicneko.tragicmc.client.model.ModelStinQueen;
import tragicneko.tragicmc.client.model.ModelTox;
import tragicneko.tragicmc.client.model.ModelTragicNeko;
import tragicneko.tragicmc.client.render.RenderDarkCrystal;
import tragicneko.tragicmc.client.render.RenderEpicWeapon;
import tragicneko.tragicmc.client.render.RenderLargeRock;
import tragicneko.tragicmc.client.render.RenderProjectile;
import tragicneko.tragicmc.client.render.RenderStatue;
import tragicneko.tragicmc.client.render.RenderTimeDisruption;
import tragicneko.tragicmc.client.render.boss.RenderApis;
import tragicneko.tragicmc.client.render.boss.RenderClaymation;
import tragicneko.tragicmc.client.render.boss.RenderDeathReaper;
import tragicneko.tragicmc.client.render.boss.RenderEnyvil;
import tragicneko.tragicmc.client.render.boss.RenderKitsune;
import tragicneko.tragicmc.client.render.boss.RenderMegaCryse;
import tragicneko.tragicmc.client.render.boss.RenderPolaris;
import tragicneko.tragicmc.client.render.boss.RenderTimeController;
import tragicneko.tragicmc.client.render.boss.RenderVoxStellarum;
import tragicneko.tragicmc.client.render.boss.RenderYeti;
import tragicneko.tragicmc.client.render.mob.RenderCryse;
import tragicneko.tragicmc.client.render.mob.RenderErkel;
import tragicneko.tragicmc.client.render.mob.RenderJabba;
import tragicneko.tragicmc.client.render.mob.RenderMob;
import tragicneko.tragicmc.client.render.mob.RenderNorVox;
import tragicneko.tragicmc.client.render.mob.RenderPirah;
import tragicneko.tragicmc.client.render.mob.RenderStin;
import tragicneko.tragicmc.client.render.mob.RenderTox;
import tragicneko.tragicmc.client.render.mob.RenderWisp;
import tragicneko.tragicmc.entity.EntityDarkCrystal;
import tragicneko.tragicmc.entity.EntityStatue;
import tragicneko.tragicmc.entity.EntityTimeDisruption;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityClaymation;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityEnyvil;
import tragicneko.tragicmc.entity.boss.EntityGreaterStin;
import tragicneko.tragicmc.entity.boss.EntityJarra;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityKragul;
import tragicneko.tragicmc.entity.boss.EntityMagmox;
import tragicneko.tragicmc.entity.boss.EntityMegaCryse;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityStinKing;
import tragicneko.tragicmc.entity.boss.EntityStinQueen;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityVoxStellarum;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityErkel;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityMinotaur;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.entity.mob.EntitySirv;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.entity.projectile.EntityBanana;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.entity.projectile.EntityDarkLightning;
import tragicneko.tragicmc.entity.projectile.EntityDarkMortor;
import tragicneko.tragicmc.entity.projectile.EntityIcicle;
import tragicneko.tragicmc.entity.projectile.EntityLargePumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntityLargeRock;
import tragicneko.tragicmc.entity.projectile.EntityNekoClusterBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoMiniBomb;
import tragicneko.tragicmc.entity.projectile.EntityNekoRocket;
import tragicneko.tragicmc.entity.projectile.EntityNekoStickyBomb;
import tragicneko.tragicmc.entity.projectile.EntityPitchBlack;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.entity.projectile.EntityPumpkinbomb;
import tragicneko.tragicmc.entity.projectile.EntitySolarBomb;
import tragicneko.tragicmc.entity.projectile.EntitySpiritCast;
import tragicneko.tragicmc.entity.projectile.EntitySpore;
import tragicneko.tragicmc.entity.projectile.EntityStarShard;
import tragicneko.tragicmc.entity.projectile.EntityThrowingRock;
import tragicneko.tragicmc.entity.projectile.EntityTimeBomb;
import tragicneko.tragicmc.entity.projectile.EntityWebBomb;
import tragicneko.tragicmc.events.KeyInputEvents;
import tragicneko.tragicmc.events.MouseEvents;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {

	public static KeyBinding useSpecial;
	public static KeyBinding openAmuletGui;	

	@Override
	public void registerRenders()
	{
		Minecraft mc = Minecraft.getMinecraft();

		//Gui event registration
		if (TragicNewConfig.showDoomGui) MinecraftForge.EVENT_BUS.register(new GuiDoom(mc));
		if (TragicNewConfig.showAmuletStatus) MinecraftForge.EVENT_BUS.register(new GuiAmuletStatus(mc));

		//Keybinding registrations
		useSpecial = new KeyBinding("Special Use", Keyboard.KEY_R, TragicMC.MODNAME);
		ClientRegistry.registerKeyBinding(useSpecial);

		openAmuletGui = new KeyBinding("Open Amulet Gui", Keyboard.KEY_P, TragicMC.MODNAME);
		ClientRegistry.registerKeyBinding(openAmuletGui);

		FMLCommonHandler.instance().bus().register(new KeyInputEvents());
		MinecraftForge.EVENT_BUS.register(new KeyInputEvents());
		MinecraftForge.EVENT_BUS.register(new MouseEvents(mc));
		
		//Weapon models
		MinecraftForgeClient.registerItemRenderer(TragicItems.ReaperScythe, new RenderEpicWeapon(0, mc));
		MinecraftForgeClient.registerItemRenderer(TragicItems.Butcher, new RenderEpicWeapon(1, mc));
		MinecraftForgeClient.registerItemRenderer(TragicItems.DragonFang, new RenderEpicWeapon(2, mc));
		MinecraftForgeClient.registerItemRenderer(TragicItems.Thardus, new RenderEpicWeapon(3, mc));
		MinecraftForgeClient.registerItemRenderer(TragicItems.Splinter, new RenderEpicWeapon(4, mc));
		MinecraftForgeClient.registerItemRenderer(TragicItems.Paranoia, new RenderEpicWeapon(5, mc));
		MinecraftForgeClient.registerItemRenderer(TragicItems.CelestialAegis, new RenderEpicWeapon(6, mc));
		MinecraftForgeClient.registerItemRenderer(TragicItems.Titan, new RenderEpicWeapon(7, mc));
		

		//Projectile and non-mob entity renders
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowingRock.class, new RenderProjectile(TragicItems.Rock));
		RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinbomb.class, new RenderProjectile(TragicItems.Pumpkinbomb));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargePumpkinbomb.class, new RenderProjectile(TragicItems.Pumpkinbomb));
		RenderingRegistry.registerEntityRenderingHandler(EntityPoisonBarb.class, new RenderProjectile(TragicItems.PoisonBarb));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoRocket.class, new RenderProjectile(TragicItems.NekoRocket));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoStickyBomb.class, new RenderProjectile(TragicItems.NekoStickyBomb));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoClusterBomb.class, new RenderProjectile(TragicItems.NekoClusterBomb));
		RenderingRegistry.registerEntityRenderingHandler(EntityNekoMiniBomb.class, new RenderProjectile(TragicItems.NekoClusterBomb));
		RenderingRegistry.registerEntityRenderingHandler(EntitySolarBomb.class, new RenderProjectile(TragicItems.SolarBomb));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiritCast.class, new RenderProjectile(TragicItems.SpiritCast));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpore.class, new RenderProjectile(TragicItems.Spore));
		RenderingRegistry.registerEntityRenderingHandler(EntityBanana.class, new RenderProjectile(TragicItems.Banana));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargeRock.class, new RenderLargeRock());
		RenderingRegistry.registerEntityRenderingHandler(EntityIcicle.class, new RenderProjectile(TragicItems.CrushedIce));
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeDisruption.class, new RenderTimeDisruption());
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeBomb.class, new RenderProjectile(Items.clock));
		RenderingRegistry.registerEntityRenderingHandler(EntityStatue.class, new RenderStatue());
		RenderingRegistry.registerEntityRenderingHandler(EntityStarShard.class, new RenderProjectile(TragicItems.StarPieces));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkLightning.class, new RenderProjectile(TragicItems.PureDarkness));
		RenderingRegistry.registerEntityRenderingHandler(EntityPitchBlack.class, new RenderProjectile(TragicItems.PitchBlack));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkEnergy.class, new RenderProjectile(TragicItems.DarkParticles));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkCrystal.class, new RenderDarkCrystal());
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkMortor.class, new RenderProjectile(TragicItems.ObsidianOrb));
		RenderingRegistry.registerEntityRenderingHandler(EntityWebBomb.class, new RenderProjectile(Item.getItemFromBlock(Blocks.web)));

		//Mob renders
		RenderingRegistry.registerEntityRenderingHandler(EntityJabba.class, new RenderJabba());
		RenderingRegistry.registerEntityRenderingHandler(EntityJarra.class, new RenderMob(new ModelJarra(), 0.655F, "Jarra_lowRes", 1.585F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlague.class, new RenderMob(new ModelPlague(), 0.115F, "Plague_lowRes"));
		RenderingRegistry.registerEntityRenderingHandler(EntityGragul.class, new RenderMob(new ModelGragul(), 0.115F, "Gragul_lowRes"));
		RenderingRegistry.registerEntityRenderingHandler(EntityKragul.class, new RenderMob(new ModelKragul(), 0.115F, "Kragul_lowRes", 2.115F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMinotaur.class, new RenderMob(new ModelMinotaur(), 0.337F, "Minotaur_lowRes"));
		RenderingRegistry.registerEntityRenderingHandler(EntityRagr.class, new RenderMob(new ModelRagr(), 0.435F, "Ragr_lowRes"));
		RenderingRegistry.registerEntityRenderingHandler(EntityInkling.class, new RenderMob(new ModelInkling(), 0.175F, "Inkling_lowRes"));
		RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinhead.class, new RenderMob(new ModelPumpkinhead(), 0.375F, "Pumpkinhead_lowRes"));
		RenderingRegistry.registerEntityRenderingHandler(EntityTragicNeko.class, new RenderMob(new ModelTragicNeko(), 0.295F, "TragicNeko_lowRes"));
		RenderingRegistry.registerEntityRenderingHandler(EntityTox.class, new RenderTox());
		RenderingRegistry.registerEntityRenderingHandler(EntityMagmox.class, new RenderMob(new ModelTox(), 0.565F, "Magmox2_lowRes", 1.625F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCryse.class, new RenderCryse());
		RenderingRegistry.registerEntityRenderingHandler(EntityMegaCryse.class, new RenderMegaCryse());
		RenderingRegistry.registerEntityRenderingHandler(EntityNorVox.class, new RenderNorVox());
		RenderingRegistry.registerEntityRenderingHandler(EntityVoxStellarum.class, new RenderVoxStellarum());
		RenderingRegistry.registerEntityRenderingHandler(EntityPirah.class, new RenderPirah());
		RenderingRegistry.registerEntityRenderingHandler(EntityStin.class, new RenderStin());
		RenderingRegistry.registerEntityRenderingHandler(EntityGreaterStin.class, new RenderMob(new ModelGreaterStin(), 0.675F, "GreaterStin_lowRes"));
		RenderingRegistry.registerEntityRenderingHandler(EntityStinKing.class, new RenderMob(new ModelStinKing(), 0.675F, "StinKing_lowRes", 1.625F));
		RenderingRegistry.registerEntityRenderingHandler(EntityStinQueen.class, new RenderMob(new ModelStinQueen(), 0.675F, "StinQueen_lowRes", 1.225F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWisp.class, new RenderWisp());
		RenderingRegistry.registerEntityRenderingHandler(EntityAbomination.class, new RenderMob(new ModelAbomination(), 0.475F, "Abomination_lowRes"));
		RenderingRegistry.registerEntityRenderingHandler(EntityErkel.class, new RenderErkel());
		RenderingRegistry.registerEntityRenderingHandler(EntitySirv.class, new RenderMob(new ModelSirv(), 0.245F, "Sirv_lowRes"));

		//Boss renders
		RenderingRegistry.registerEntityRenderingHandler(EntityApis.class, new RenderApis());
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathReaper.class, new RenderDeathReaper());
		RenderingRegistry.registerEntityRenderingHandler(EntityKitsune.class, new RenderKitsune());
		RenderingRegistry.registerEntityRenderingHandler(EntityPolaris.class, new RenderPolaris());
		RenderingRegistry.registerEntityRenderingHandler(EntityYeti.class, new RenderYeti());
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeController.class, new RenderTimeController());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnyvil.class, new RenderEnyvil());
		RenderingRegistry.registerEntityRenderingHandler(EntityClaymation.class, new RenderClaymation());
	}

	@Override
	public EntityPlayer getPlayerFromMessageCtx(MessageContext ctx)
	{
		return Minecraft.getMinecraft().thePlayer;
	}

}
