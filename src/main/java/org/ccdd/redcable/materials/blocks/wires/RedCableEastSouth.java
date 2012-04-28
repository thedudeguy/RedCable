package org.ccdd.redcable.materials.blocks.wires;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.materials.blocks.designs.RedCableTurnDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableEastSouth extends RedCableBlock {

	private int rotationY = 0;
	
	private List<BlockFace> wireEnds = Arrays.asList(BlockFace.SOUTH, BlockFace.EAST);
	
	public RedCableEastSouth() {
		super(RedCableBlock.EASTtoSOUTH);
		
		this.setBlockDesign(new RedCableTurnDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.SOUTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.EAST)) return true;
		return false;
	}

	@Override
	public List<BlockFace> getWireEnds() {
		return wireEnds;
	}

}
