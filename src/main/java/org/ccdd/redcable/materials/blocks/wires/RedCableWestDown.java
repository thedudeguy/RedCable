package org.ccdd.redcable.materials.blocks.wires;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.materials.blocks.designs.RedCableTurnDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableWestDown extends RedCableBlock {

	private int rotationX = 90;
	private int rotationY = 90;
	private int rotationZ = 0;
	
	private float moveX = 0.46875F;
	private float moveY = -0.46875F;
	private float moveZ = 0;
	
	private List<BlockFace> wireEnds = Arrays.asList(BlockFace.WEST, BlockFace.DOWN);
	
	public RedCableWestDown() {
		super(RedCableBlock.WESTtoDOWN);
		this.setBlockDesign(new RedCableTurnDesign(rotationX, rotationY, rotationZ, moveX, moveY, moveZ));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.WEST)) return true;
		if (!this.isFaceConnected(block, BlockFace.DOWN)) return true;
		return false;
	}

	@Override
	public List<BlockFace> getWireEnds() {
		return wireEnds;
	}
}
