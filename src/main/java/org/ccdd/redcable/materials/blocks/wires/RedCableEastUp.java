package org.ccdd.redcable.materials.blocks.wires;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.materials.blocks.designs.RedCableTurnDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableEastUp extends RedCableBlock {
	
	private int rotationX = 270;
	private int rotationY = 270;
	private int rotationZ = 0;
	
	private float moveX = 0.46875F;
	private float moveY = -0.46875F;
	private float moveZ = 0;
	
	private List<BlockFace> wireEnds = Arrays.asList(BlockFace.EAST, BlockFace.UP);
	
	public RedCableEastUp() {
		super(RedCableBlock.EASTtoUP, 85);
		this.setBlockDesign(new RedCableTurnDesign(rotationX, rotationY, rotationZ, moveX, moveY, moveZ));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.EAST)) return true;
		if (!this.isFaceConnected(block, BlockFace.UP)) return true;
		return false;
	}

	@Override
	public List<BlockFace> getWireEnds() {
		return wireEnds;
	}
}
