package com.rpgcraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import sun.net.www.content.text.Generic;

import com.rpgcraft.crafting.RecipeRegistry;
import com.rpgcraft.gui.TutorialGui;
import com.rpgcraft.item.GenericItem;
import com.rpgcraft.tiles.FreezerBlock;
import com.rpgcraft.tiles.GenericOre;
import com.rpgcraft.tiles.SkillBlock;
import com.rpgcraft.tiles.TileEntityFreezer;
import com.rpgcraft.tiles.TileEntitySkillBlock;
import com.rpgcraft.tiles.TutorialBlock;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod(modid = RPGCraft.MODID, name = RPGCraft.NAME, version = RPGCraft.VERSION)
public class RPGCraft {

	//mod data
	public static final String MODID = "rpgcraft";
	public static final String NAME = "RPG Craft";
	public static final String VERSION = "0.0.0";
	
	public static final int GUI_FREEZER = 0;
	
	public static Item genericItem;
	public static Item genericIngot;
	
	public static Block tutorialBlock;
	public static Item tutorialItem;
	
	public final static Block genericOre = new GenericOre(Material.rock);
	
	public static Block freezerBlock;
	
	public static Block skillBlock;
	
	public static CreativeTabs mainTab = new CreativeTabs("mainTab"){
		@Override
		public Item getTabIconItem(){
			return tutorialItem;
		}
	};
	
	//Singleton instance used by Forge
	@Instance(MODID)
	public static RPGCraft instance;
	
	//Says where the client and server 'proxy' code is located
	@SidedProxy(clientSide = "com.rpgcraft.client.ClientProxy", serverSide = "com.rpgcraft.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		genericItem = new GenericItem();
		GameRegistry.registerItem(genericItem, genericItem.getUnlocalizedName());
		
		tutorialBlock = new TutorialBlock(Material.rock).setBlockName("tutorialBlock").setBlockTextureName("rpgcraft:tutorialBlock").setCreativeTab(mainTab);
		GameRegistry.registerBlock(tutorialBlock, tutorialBlock.getUnlocalizedName());
		tutorialItem = new Item().setCreativeTab(mainTab).setUnlocalizedName("tutorialItem").setTextureName("rpgcraft:tutorialItem");
		GameRegistry.registerItem(tutorialItem, tutorialItem.getUnlocalizedName());
	
		freezerBlock = new FreezerBlock().setBlockName("freezer").setCreativeTab(mainTab);
		GameRegistry.registerBlock(freezerBlock, freezerBlock.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileEntityFreezer.class, freezerBlock.getUnlocalizedName());
		
		genericIngot = new GenericItem().setMaxStackSize(16).setUnlocalizedName("genericIngot");
		
		GameRegistry.registerBlock(genericOre, "genericOre");
		
		skillBlock = new SkillBlock().setBlockName("skillBlock").setCreativeTab(mainTab);
		
		GameRegistry.registerBlock(skillBlock, skillBlock.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileEntitySkillBlock.class, skillBlock.getUnlocalizedName());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.registerRenderers();
		RecipeRegistry.registerRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(MODID, new TutorialGui());
	}
	
	@EventHandler
	public void postInitialization(FMLPostInitializationEvent event){
		
	}
	
	
}