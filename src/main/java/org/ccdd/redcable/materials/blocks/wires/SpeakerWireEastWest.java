package org.ccdd.redcable.materials.blocks.wires;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.SpeakerWireBlock;
import org.ccdd.redcable.materials.blocks.designs.SpeakerWireStraightDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class SpeakerWireEastWest extends SpeakerWireBlock {

	private int rotationY = 90;
	
	public SpeakerWireEastWest() {
		super(SpeakerWireBlock.EASTtoWEST);
		
		this.setBlockDesign(new SpeakerWireStraightDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.EAST)) return true;
		if (!this.isFaceConnected(block, BlockFace.WEST)) return true;
		return false;
		
	}
	
	
}
