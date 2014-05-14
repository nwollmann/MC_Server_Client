package com.rpgcraft.tiles;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SkillBlock extends BlockContainer {

	public SkillBlock(){
		super(Material.circuits);
		
		setHardness(4.0F);
		setResistance(10);
	}
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntitySkillBlock();
	}
}