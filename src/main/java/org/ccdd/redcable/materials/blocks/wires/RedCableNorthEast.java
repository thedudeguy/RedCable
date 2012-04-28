package org.ccdd.redcable.materials.blocks.wires;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.materials.blocks.designs.RedCableTurnDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableNorthEast extends RedCableBlock {
	
	private int rotationY = 270;
	
	private List<BlockFace> wireEnds = Arrays.asList(BlockFace.NORTH, BlockFace.EAST);
	
	public RedCableNorthEast() {
		super(RedCableBlock.NORTHtoEAST);
		this.setBlockDesign(new RedCableTurnDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.NORTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.EAST)) return true;
		return false;
	}

	@Override
	public List<BlockFace> getWireEnds() {
		return wireEnds;
	}
	
}
