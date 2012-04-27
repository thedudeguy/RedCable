package org.ccdd.redcable.materials.blocks.wires;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.SpeakerWireBlock;
import org.ccdd.redcable.materials.blocks.designs.SpeakerWireStraightDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class SpeakerWireNorthSouth extends SpeakerWireBlock {

	private int rotationY = 0;
	
	public SpeakerWireNorthSouth() {
		super(SpeakerWireBlock.NORTHtoSOUTH);
		
		this.setBlockDesign(new SpeakerWireStraightDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.NORTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.SOUTH)) return true;
		return false;
	}
	
}
