package org.ccdd.redcable.materials.blocks.wires;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.materials.blocks.designs.RedCableTurnDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableNorthEast extends RedCableBlock {
	
	private int rotationY = 270;
	
	public RedCableNorthEast() {
		super(RedCableBlock.NORTHtoEAST);
		this.setBlockDesign(new RedCableTurnDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.NORTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.EAST)) return true;
		return false;
	}
	
}
