package org.ccdd.redcable.materials.blocks.wires;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.SpeakerWireBlock;
import org.ccdd.redcable.materials.blocks.designs.SpeakerWireStraightDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class SpeakerWireUpDown extends SpeakerWireBlock {

	private int rotationX = 0;
	private int rotationY = 0;
	private int rotationZ = 90;
	
	private float moveX = -0.46875F;
	private float moveY = -0.46875F;
	private float moveZ = 0;
	
	public SpeakerWireUpDown() {
		super(SpeakerWireBlock.UPtoDOWN, 85);
		
		this.setBlockDesign(new SpeakerWireStraightDesign(rotationX, rotationY, rotationZ, moveX, moveY, moveZ));
		
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.UP)) return true;
		if (!this.isFaceConnected(block, BlockFace.DOWN)) return true;
		return false;
	}

}
