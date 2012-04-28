package org.ccdd.redcable.materials.blocks.wires;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.materials.blocks.designs.RedCableTurnDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableSouthWest extends RedCableBlock {

	private int rotationY = 90;
	
	public RedCableSouthWest() {
		super(RedCableBlock.SOUTHtoWEST);
		
		this.setBlockDesign(new RedCableTurnDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		
		if (!this.isFaceConnected(block, BlockFace.SOUTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.WEST)) return true;
		return false;
		
	}

}
