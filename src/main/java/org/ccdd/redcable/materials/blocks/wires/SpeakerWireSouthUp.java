package org.ccdd.redcable.materials.blocks.wires;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.SpeakerWireBlock;
import org.ccdd.redcable.materials.blocks.designs.SpeakerWireTurnDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class SpeakerWireSouthUp extends SpeakerWireBlock {

	private int rotationX = 270;
	private int rotationY = 0;
	private int rotationZ = 0;
	
	private float moveX = 0;
	private float moveY = -0.46875F;
	private float moveZ = 0.46875F;
	
	public SpeakerWireSouthUp() {
		super(SpeakerWireBlock.SOUTHtoUP, 85);
		this.setBlockDesign(new SpeakerWireTurnDesign(rotationX, rotationY, rotationZ, moveX, moveY, moveZ));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.SOUTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.UP)) return true;
		return false;
	}


}
