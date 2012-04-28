package org.ccdd.redcable.util;

import org.ccdd.redcable.materials.items.Items;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;

public class Recipies {
	
	public static void load() {
		
		//////////////////
		// Speaker Wire //
		//////////////////
		SpoutManager.getMaterialManager().registerSpoutRecipe(
		new SpoutShapedRecipe( new SpoutItemStack(Items.redCable, 3) )
			.shape("sss", "rrr", "sss")
			.setIngredient('s', MaterialData.whiteWool)
			.setIngredient('r', MaterialData.redstone)
			);
		
	}
}
