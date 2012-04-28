package org.ccdd.redcable.materials.blocks.wires;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.materials.blocks.designs.RedCableTurnDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableWestNorth extends RedCableBlock {

	private int rotationY = 180;
	
	public RedCableWestNorth() {
		super(RedCableBlock.WESTtoNORTH);

		this.setBlockDesign(new RedCableTurnDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		
		if (!this.isFaceConnected(block, BlockFace.WEST)) return true;
		if (!this.isFaceConnected(block, BlockFace.NORTH)) return true;
		return false;
	}

}
