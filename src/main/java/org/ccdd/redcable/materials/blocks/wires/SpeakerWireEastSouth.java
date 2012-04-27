package org.ccdd.redcable.materials.blocks.wires;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.SpeakerWireBlock;
import org.ccdd.redcable.materials.blocks.designs.SpeakerWireTurnDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class SpeakerWireEastSouth extends SpeakerWireBlock {

	private int rotationY = 0;
	
	public SpeakerWireEastSouth() {
		super(SpeakerWireBlock.EASTtoSOUTH);
		
		this.setBlockDesign(new SpeakerWireTurnDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.SOUTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.EAST)) return true;
		return false;
	}

}
