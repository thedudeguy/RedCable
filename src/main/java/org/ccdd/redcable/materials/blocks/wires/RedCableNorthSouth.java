package org.ccdd.redcable.materials.blocks.wires;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.materials.blocks.designs.RedCableStraightDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableNorthSouth extends RedCableBlock {

	private int rotationY = 0;
	
	private List<BlockFace> wireEnds = Arrays.asList(BlockFace.NORTH, BlockFace.SOUTH);
	
	public RedCableNorthSouth() {
		super(RedCableBlock.NORTHtoSOUTH);
		
		this.setBlockDesign(new RedCableStraightDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.NORTH)) return true;
		if (!this.isFaceConnected(block, BlockFace.SOUTH)) return true;
		return false;
	}

	@Override
	public List<BlockFace> getWireEnds() {
		return wireEnds;
	}
	
}
