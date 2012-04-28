package org.ccdd.redcable.listeners;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.ccdd.redcable.RedCable;

import org.bukkit.event.block.BlockRedstoneEvent;
import org.ccdd.redcable.events.RedCablePlaceEvent;
import org.ccdd.redcable.events.RedCablePowerEvent;
import org.ccdd.redcable.materials.blocks.Blocks;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.util.Debug;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCableListener implements Listener {
	
	public HashMap<BlockFace, SpoutBlock> getAvailableWires(SpoutBlock block) {
		List<BlockFace> faceList = Arrays.asList(BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST,  BlockFace.UP, BlockFace.DOWN);
		
		HashMap<BlockFace, SpoutBlock> wires = new HashMap<BlockFace, SpoutBlock>();
		
		//collect a list of surrounding blocks who are also speaker wire, and have atleast one open side.
		for (BlockFace face : faceList) {
			
			SpoutBlock relBlock = (SpoutBlock)block.getRelative(face);
			
			if (relBlock.getCustomBlock() != null && relBlock.getCustomBlock() instanceof RedCableBlock) {
				Debug.debug("Block at ", face, " is redcableblock");
				
				if ( ((RedCableBlock)relBlock.getCustomBlock()).hasOpenEnd(relBlock) )
				{
					//if there is a maximum of 2, we dont need anymore, so go ahead and skip
					if (wires.size() < 2) {
						wires.put(face, relBlock);
					}
				}
			}
			
		}
		
		return wires;
		
	}
	
	public BlockFace getConnectedFace(SpoutBlock block, BlockFace ignoreFace) {
		
		List<BlockFace> faceList = Arrays.asList(BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST, BlockFace.UP, BlockFace.DOWN);
		
		//collect a list of surrounding blocks who are also speaker wire, and have atleast one open side.
		for (BlockFace face : faceList) {
			
			if (!face.equals(ignoreFace)) {
				
				if ( ((RedCableBlock)block.getCustomBlock()).isFaceConnected(block, face) ) return face;
				
			}
		}
		
		return null;
		
	}
	
	public void setBlockType(SpoutBlock wireBlock, HashMap<BlockFace, SpoutBlock> availableWires) {
		
		switch(availableWires.size()) {
		case 0:
			//do nothing;
			break;
		case 1:
			//only connect to one;
			if (
					availableWires.containsKey(BlockFace.EAST) ||
					availableWires.containsKey(BlockFace.WEST)
					) {
				wireBlock.setCustomBlock(Blocks.redCableBlockEastWest);
			} else if (
					availableWires.containsKey(BlockFace.NORTH) ||
					availableWires.containsKey(BlockFace.SOUTH)
					) {
				wireBlock.setCustomBlock(Blocks.redCableBlockNorthSouth);
			}
			else if (
					availableWires.containsKey(BlockFace.UP) ||
					availableWires.containsKey(BlockFace.DOWN)
					) {
				wireBlock.setCustomBlock(Blocks.redCableBlockUpDown);
			}
			break;
		case 2:
			//connecting to 2 wires
			if (availableWires.containsKey(BlockFace.EAST) && availableWires.containsKey(BlockFace.WEST)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockEastWest);
				
			} else if (availableWires.containsKey(BlockFace.NORTH) && availableWires.containsKey(BlockFace.SOUTH)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockNorthSouth);
				
			} else if (availableWires.containsKey(BlockFace.NORTH) && availableWires.containsKey(BlockFace.EAST)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockNorthEast);
				
			} else if (availableWires.containsKey(BlockFace.EAST) && availableWires.containsKey(BlockFace.SOUTH)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockEastSouth);
				
			} else if (availableWires.containsKey(BlockFace.SOUTH) && availableWires.containsKey(BlockFace.WEST)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockSouthWest);
				
			} else if (availableWires.containsKey(BlockFace.WEST) && availableWires.containsKey(BlockFace.NORTH)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockWestNorth);
				
			} else if (availableWires.containsKey(BlockFace.UP) && availableWires.containsKey(BlockFace.DOWN)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockUpDown);
				
			} else if (availableWires.containsKey(BlockFace.EAST) && availableWires.containsKey(BlockFace.UP)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockEastUp);
				
			} else if (availableWires.containsKey(BlockFace.WEST) && availableWires.containsKey(BlockFace.UP)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockWestUp);
				
			} else if (availableWires.containsKey(BlockFace.NORTH) && availableWires.containsKey(BlockFace.UP)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockNorthUp);
				
			} else if (availableWires.containsKey(BlockFace.SOUTH) && availableWires.containsKey(BlockFace.UP)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockSouthUp);
				
			} else if (availableWires.containsKey(BlockFace.EAST) && availableWires.containsKey(BlockFace.DOWN)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockEastDown);
				
			} else if (availableWires.containsKey(BlockFace.WEST) && availableWires.containsKey(BlockFace.DOWN)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockWestDown);
				
			} else if (availableWires.containsKey(BlockFace.NORTH) && availableWires.containsKey(BlockFace.DOWN)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockNorthDown);
				
			} else if (availableWires.containsKey(BlockFace.SOUTH) && availableWires.containsKey(BlockFace.DOWN)) {
				wireBlock.setCustomBlock(Blocks.redCableBlockSouthDown);
				
			}
			break;
		}
		
	}
	
	@EventHandler 
	public void onRedstone(BlockRedstoneEvent event) {
		
		SpoutBlock block = (SpoutBlock)event.getBlock();
		
		Debug.debug("BlockRedstoneEvent || Name: ", block.getBlockType().getName() , "|| Old current: ", event.getOldCurrent(), " || New Current: ", event.getNewCurrent());
		
	}
	
	@EventHandler
	public void onRedCablePower(RedCablePowerEvent event) {
		
		RedCableBlock cableBlock = (RedCableBlock)event.getBlock().getCustomBlock();
		
		Debug.debug("RedCablePowerEvent || oldCurrent: ", event.getOldCurrent(), " || newCurrent: ", event.getNewCurrent(), " || faceComingFrom: ", event.getFaceComingFrom());
		
		//set this blocks power to 1.
		cableBlock.setPower(event.getBlock(), event.getNewCurrent());
	
	}
	
	@EventHandler
	public void onPlace(RedCablePlaceEvent event) {
		Debug.debug(event.getPlayer(), "Placing Speaker Wire");
		
		HashMap<BlockFace, SpoutBlock> wires = getAvailableWires(event.getBlock());
		
		Debug.debug(event.getPlayer(), "Surrounding Wires with open ends: ", wires.size());
		Debug.debug(event.getPlayer(), "Placing Wire");
		
		
		event.getBlock().setCustomBlock(Blocks.redCableBlockEastWest);
		
		setBlockType((SpoutBlock)event.getBlock(), wires);
		
		//do the same for the wipres we connected to.
		//we know that every wire in this list has ATLEAST one available connection
		for ( Entry<BlockFace, SpoutBlock> item : wires.entrySet() ) {
			
			HashMap<BlockFace, SpoutBlock> connectTo = new HashMap<BlockFace, SpoutBlock>();
			//add our block.
			connectTo.put(item.getKey().getOppositeFace(), event.getBlock());
			
			BlockFace existingFace = getConnectedFace(item.getValue(), item.getKey().getOppositeFace());
			
			if (existingFace != null) {
				connectTo.put(existingFace, item.getValue());
			}
			
			setBlockType(item.getValue(), connectTo);
			
		}
	}

    @EventHandler(priority= EventPriority.HIGH)
    public void playerStepsOnWire (PlayerInteractEvent event) {
        if (event.getAction().equals(Action.PHYSICAL)){ // Steps on it
            SpoutBlock block = (SpoutBlock) event.getClickedBlock();
            if (block.getCustomBlock() != null){
                if (RedCable.instance.getBlockSet().contains(block.getCustomBlock())){
                    event.setCancelled(true); // We don't want the "step on" event to fire at all for our blocks
                }
            }
        }
    }
}
