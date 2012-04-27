package org.ccdd.redcable.materials.blocks.wires;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.SpeakerWireBlock;
import org.ccdd.redcable.materials.blocks.designs.SpeakerWireTurnDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class SpeakerWireWestDown extends SpeakerWireBlock {

	private int rotationX = 90;
	private int rotationY = 90;
	private int rotationZ = 0;
	
	private float moveX = 0.46875F;
	private float moveY = -0.46875F;
	private float moveZ = 0;
	
	public SpeakerWireWestDown() {
		super(SpeakerWireBlock.WESTtoDOWN);
		this.setBlockDesign(new SpeakerWireTurnDesign(rotationX, rotationY, rotationZ, moveX, moveY, moveZ));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.WEST)) return true;
		if (!this.isFaceConnected(block, BlockFace.DOWN)) return true;
		return false;
	}
}
