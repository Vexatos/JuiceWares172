package com.juicegrape.juicewares.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.juicegrape.juicewares.blocks.ModBlocks;
import com.juicegrape.juicewares.config.Enabling;
import com.juicegrape.juicewares.items.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class CustomRecipes {
	
	public static boolean checkRegOre(String name) {
		for (String entry : OreDictionary.getOreNames()) {
			if (entry == name) {
				return true;
			}
		}
		return false;
	}

	public static void regCustomModRecipes() {
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string),
				new Object[] { new ItemStack(ModItems.stringreed) }); 
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.drawer, new Object[] { 	
						"WWW",
						" W ",
						"WWW",

		Character.valueOf('W'), "plankWood"}));

		GameRegistry.addRecipe( new ItemStack(ModItems.divinghelmet), new Object[] {
				" H ",
				"IGI",
				" B ",
				Character.valueOf('H'), Items.golden_helmet,
				Character.valueOf('B'), Blocks.iron_bars,
				Character.valueOf('I'), Items.iron_ingot,
				Character.valueOf('G'), ModItems.lens
		});
		
		//Easy night vision lens recipe
		if (Enabling.enableEasyNightVisionLensRecipe) {
			if (checkRegOre("gemEmerald")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lens, 1, 1),
						new Object[] {	"E",
										"L",
					Character.valueOf('L'), new ItemStack(ModItems.lens, 1, 0),
					Character.valueOf('E'), "gemEmerald"
					}));
			}
		}
		if (Enabling.enableModerateNightVisionLensRecipe) {
			if (checkRegOre("gemEmerald")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lens, 1, 1),
						new Object[] {	" E ",
										"ELE",
										" E ",
					Character.valueOf('L'), new ItemStack(ModItems.lens, 1, 0),
					Character.valueOf('E'), "gemEmerald"
					}));
			}
		}
		if (Enabling.enableHardNightVisionLensRecipe) {
			if (checkRegOre("gemEmerald")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lens, 1, 1),
						new Object[] {	"PEP",
										"ELE",
										"PEP",
					Character.valueOf('L'), new ItemStack(ModItems.lens, 1, 0),
					Character.valueOf('E'), "gemEmerald",
					Character.valueOf('P'), new ItemStack(Items.potionitem,1, 8230),
					}));
			}
		}
		if (Enabling.enableSuperHardNightVisionLensRecipe) {
			if (checkRegOre("gemEmerald")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lens, 1, 1),
						new Object[] {	"PEP",
										"ELE",
										"PEP",
					Character.valueOf('L'), new ItemStack(ModItems.lens, 1, 0),
					Character.valueOf('E'), "gemEmerald",
					Character.valueOf('P'), new ItemStack(Items.potionitem,1, 8262),
					}));
			}
		}
		
		GameRegistry.addRecipe(new ItemStack(ModItems.nightvisiongoggles), new Object[] {
			"S S",
			"OLO",
			"LIL",
			Character.valueOf('S'), Items.string,
			Character.valueOf('L'), new ItemStack(ModItems.lens, 1, 1),
			Character.valueOf('I'), Items.iron_ingot,
			Character.valueOf('O'), Blocks.obsidian
		});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(ModItems.enchantmentItem, 1, 0), new Object[] {
			" C ",
			"CIC",
			" C ",
			Character.valueOf('C'), Blocks.clay,
			Character.valueOf('I'), Items.iron_ingot
		});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.enchantmentItem, 1, 1), new Object[] {
			" L ",
			"LGL",
			" L ",
			Character.valueOf('L'), new ItemStack(Items.dye, 1, 4),
			Character.valueOf('G'), Items.gold_ingot
		});
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.altar), new Object[] {
			"I I",
			"I I",
			"OOO",
			Character.valueOf('I'), Items.iron_ingot,
			Character.valueOf('O'), Blocks.obsidian
		}); 
		
		if (Enabling.enableTimeSpring) {	
			if (checkRegOre("gemEmerald")) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTimeSpring), new Object[] {
				"E ",
				"CI",
				"O ",
				Character.valueOf('E'), "gemEmerald",
				Character.valueOf('C'), Items.clock,
				Character.valueOf('I'), Items.iron_ingot,
				Character.valueOf('O'), Items.ender_eye
				}));
			}
		}
		
		

		
	}
}
