package com.juicegrape.juicewares;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import com.juicegrape.juicewares.blocks.ModBlocks;
import com.juicegrape.juicewares.compat.ThaumcraftHandler;
import com.juicegrape.juicewares.config.ConfigHandler;
import com.juicegrape.juicewares.entities.Entity;
import com.juicegrape.juicewares.generation.GenerationHandler;
import com.juicegrape.juicewares.items.ModItems;
import com.juicegrape.juicewares.misc.CustomDamageSource;
import com.juicegrape.juicewares.network.EventHooks;
import com.juicegrape.juicewares.potionEffects.PotionPreInit;
import com.juicegrape.juicewares.potionEffects.Potions;
import com.juicegrape.juicewares.proxies.CommonProxy;
import com.juicegrape.juicewares.recipes.CustomRecipes;
import com.juicegrape.juicewares.recipes.FuelHandler;
import com.juicegrape.juicewares.recipes.MortarPestleRecipes;
import com.juicegrape.juicewares.recipes.VanillaItemRecipes;
import com.juicegrape.juicewares.recipes.primalEnchanting.PrimalEnchantingMain;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, dependencies = "after:MoreEnchants;after:Thaumcraft;after:Forestry;after:MineFactoryReloaded")
public class juicewares {

	@Instance(ModInformation.ID)
	public static juicewares instance;

	@SidedProxy(clientSide = "com.juicegrape.juicewares.proxies.ClientProxy", serverSide = "com.juicegrape.juicewares.proxies.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs juiceTab = new CreativeTabs("juicewares_juicetab") {

		@Override
		public Item getTabIconItem() {
			return ModItems.mortarPestle;
		}
		
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		ModBlocks.init();
		ModItems.init();
		Entity.init();
		proxy.initSounds();
		proxy.initRenderers();
		proxy.registerTileEntities();
		ModItems.dungeonLoot();
		MinecraftForge.EVENT_BUS.register(new EventHooks());
		PotionPreInit.preInit();
		CustomDamageSource.init();
		System.out.println("JuiceWares succesfully pre initialized (probably)"); 
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		if (Loader.isModLoaded("Thaumcraft")) {
			ThaumcraftHandler.init();
		}

		Potions.init();
		ModItems.miscInit();
		ModItems.addOreDictionary();
		ModBlocks.addOreDict();
		CustomRecipes.regCustomModRecipes();
		VanillaItemRecipes.regCustomVanillaRecipes();
		new GenerationHandler();
		new FuelHandler();
		Entity.initEggs();
		Entity.initSpawns();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
		System.out.println("JuiceWares succesfully initialized (probably)"); 
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		if (Loader.isModLoaded("Thaumcraft")) {
			ThaumcraftHandler.Postinit();
		}
		
		
		PrimalEnchantingMain.init();
		
		MortarPestleRecipes.init();

		System.out.println("JuiceWares succesfully post initialized (probably)");

	}
}
