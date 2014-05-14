package com.rpgcraft.tiles;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TutorialBlock extends Block {

	public TutorialBlock(Material material) {
		super(material);
	
		setHardness(1.5F);
		setResistance(10);
		setLightLevel(10);
		setLightOpacity(10);
	}
}
