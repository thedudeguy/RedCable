package org.ccdd.redcable.materials.blocks.wires;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.materials.blocks.designs.RedCableStraightDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableNorthSouth extends RedCableBlock {

	private int rotationY = 0;
	
	public RedCableNorthSouth() {
		super(RedCableBlock.NORTHtoSOUTH);
		
		this.setBlockDesign(new RedCableStraightDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.NORTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.SOUTH)) return true;
		return false;
	}
	
}
