package org.ccdd.redcable.materials.blocks.wires;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.BlockFace;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.materials.blocks.designs.RedCableStraightDesign;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableEastWest extends RedCableBlock {

	private int rotationY = 90;
	
	private List<BlockFace> wireEnds = Arrays.asList(BlockFace.EAST, BlockFace.WEST);
	
	public RedCableEastWest() {
		super(RedCableBlock.EASTtoWEST);
		
		this.setBlockDesign(new RedCableStraightDesign(rotationY));
	}

	@Override
	public boolean hasOpenEnd(SpoutBlock block) {
		if (!this.isFaceConnected(block, BlockFace.EAST)) return true;
		if (!this.isFaceConnected(block, BlockFace.WEST)) return true;
		return false;
		
	}

	@Override
	public List<BlockFace> getWireEnds() {
		return wireEnds;
	}
	
	
}
