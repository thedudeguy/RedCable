package org.ccdd.redcable.materials.blocks.wires;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.materials.blocks.designs.RedCableStraightDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableUpDown extends RedCableBlock {

	private int rotationX = 0;
	private int rotationY = 0;
	private int rotationZ = 90;
	
	private float moveX = -0.46875F;
	private float moveY = -0.46875F;
	private float moveZ = 0;
	
	private List<BlockFace> wireEnds = Arrays.asList(BlockFace.UP, BlockFace.DOWN);
	
	public RedCableUpDown() {
		super(RedCableBlock.UPtoDOWN, 85);
		
		this.setBlockDesign(new RedCableStraightDesign(rotationX, rotationY, rotationZ, moveX, moveY, moveZ));
		
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.UP)) return true;
		if (!this.isFaceConnected(block, BlockFace.DOWN)) return true;
		return false;
	}

	@Override
	public List<BlockFace> getWireEnds() {
		return wireEnds;
	}

}
