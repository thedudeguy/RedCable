package org.ccdd.redcable.materials.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.ccdd.redcable.RedCable;
import org.ccdd.redcable.events.RedCablePlaceEvent;
import org.ccdd.redcable.util.Debug;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

public class RedCableItem extends GenericCustomItem {
	
	public RedCableItem() {
		super(org.ccdd.redcable.RedCable.instance, "Speaker Wire");
		this.setTexture("speakerwire.png");
	}
	
	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, org.bukkit.block.BlockFace face) {
		
		if (block != null && !block.getType().equals(Material.AIR) && face.equals(BlockFace.UP)) {
			
			SpoutBlock placeBlock = block.getRelative(face);
			if (placeBlock == null || placeBlock.getType().equals(Material.AIR)) {
				
				RedCablePlaceEvent event = new RedCablePlaceEvent(player, placeBlock);
				Bukkit.getServer().getPluginManager().callEvent(event);
				
				//remove 1 from hand.
				//if (!player.getGameMode().equals(GameMode.CREATIVE)) {
					Debug.debug(player, "Removing item from hand");
					ItemStack inHand = player.getItemInHand();
					if (inHand.getAmount()<2) {
						player.setItemInHand(new ItemStack(Material.AIR));
					} else {
						player.getItemInHand().setAmount(inHand.getAmount()-1);
					}
				//}
				
			}
			
		}
		return false;
	}
	
}
