package com.rpgcraft;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import advancedhud.KeyRegister;
import advancedhud.TickHandler;
import advancedhud.api.HUDRegistry;

import com.rpgcraft.crafting.RecipeRegistry;
import com.rpgcraft.events.RPGEventHandler;
import com.rpgcraft.gui.GuiBuffBar;
import com.rpgcraft.gui.TutorialGui;
import com.rpgcraft.gui.hud.items.HudItemAir;
import com.rpgcraft.gui.hud.items.HudItemArmor;
import com.rpgcraft.gui.hud.items.HudItemBossBar;
import com.rpgcraft.gui.hud.items.HudItemCrosshairs;
import com.rpgcraft.gui.hud.items.HudItemExperienceBar;
import com.rpgcraft.gui.hud.items.HudItemFood;
import com.rpgcraft.gui.hud.items.HudItemHealth;
import com.rpgcraft.gui.hud.items.HudItemHealthMount;
import com.rpgcraft.gui.hud.items.HudItemHotbar;
import com.rpgcraft.gui.hud.items.HudItemJumpBar;
import com.rpgcraft.gui.hud.items.HudItemRecordDisplay;
import com.rpgcraft.gui.hud.items.HudItemTooltips;
import com.rpgcraft.item.GenericItem;
import com.rpgcraft.item.ItemStore;
import com.rpgcraft.packets.PacketPipeline;
import com.rpgcraft.tiles.FreezerBlock;
import com.rpgcraft.tiles.GenericOre;
import com.rpgcraft.tiles.SkillBlock;
import com.rpgcraft.tiles.TileEntityFreezer;
import com.rpgcraft.tiles.TileEntitySkillBlock;
import com.rpgcraft.tiles.TutorialBlock;

import cpw.mods.fml.common.FMLCommonHandler;
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
	public static Logger log;
	
	public static final int GUI_FREEZER = 0;
	public static final int GUI_SKILL = 1;
	public static final int GUI_ITEM_INV = 2;
	
	public static final PacketPipeline packetPipeline = new PacketPipeline();
	
	public static Item genericItem;
	public static Item genericIngot;
	public static Item itemstore;
	
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
		log = event.getModLog();
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
		
		itemstore = new ItemStore().setUnlocalizedName("item_store").setCreativeTab(mainTab);	
		GameRegistry.registerItem(itemstore, itemstore.getUnlocalizedName());
		
		FMLCommonHandler.instance().bus().register(new RPGEventHandler());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.registerRenderers();
		RecipeRegistry.registerRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(MODID, new TutorialGui());
		packetPipeline.initialise();
		
		FMLCommonHandler.instance().bus().register(new TickHandler());
        FMLCommonHandler.instance().bus().register(new KeyRegister());

        registerHUDItems();
	}
	
	@EventHandler
	public void postInitialization(FMLPostInitializationEvent event){
		packetPipeline.postInitialise();
		//MinecraftForge.EVENT_BUS.register(new GuiBuffBar(Minecraft.getMinecraft()));
		System.out.println("Well?");
	}
	
	private void registerHUDItems(){
		HUDRegistry.registerHudItem(new HudItemHotbar());
        HUDRegistry.registerHudItem(new HudItemHealth());
        HUDRegistry.registerHudItem(new HudItemAir());
        HUDRegistry.registerHudItem(new HudItemFood());
        HUDRegistry.registerHudItem(new HudItemArmor());
        HUDRegistry.registerHudItem(new HudItemBossBar());
        HUDRegistry.registerHudItem(new HudItemJumpBar());
        HUDRegistry.registerHudItem(new HudItemHealthMount());
        HUDRegistry.registerHudItem(new HudItemExperienceBar());
        HUDRegistry.registerHudItem(new HudItemCrosshairs());
        HUDRegistry.registerHudItem(new HudItemTooltips());
        HUDRegistry.registerHudItem(new HudItemRecordDisplay());
        HUDRegistry.setInitialLoadComplete(true);
	}
	
}
