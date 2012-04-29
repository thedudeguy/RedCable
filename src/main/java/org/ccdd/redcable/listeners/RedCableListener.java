package org.ccdd.redcable.listeners;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.ccdd.redcable.RedCable;
import org.ccdd.redcable.events.RedCablePlaceEvent;
import org.ccdd.redcable.events.RedCablePowerEvent;
import org.ccdd.redcable.materials.blocks.Blocks;
import org.ccdd.redcable.materials.blocks.RedCableBlock;
import org.ccdd.redcable.util.Debug;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.particle.Particle;
import org.getspout.spoutapi.particle.Particle.ParticleType;

public class RedCableListener implements Listener {
	
	private List<BlockFace> faceList = Arrays.asList(BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST, BlockFace.UP, BlockFace.DOWN);
	
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
		
		//if this block is a cable wire and receives a red stone event, its from stepping on it.
		//if we dont continue on to send the redcablepowerevent, this will have no effect.
		if (block.getCustomBlock() != null && block.getCustomBlock() instanceof RedCableBlock) return;
		
		//see if a redstone cable is attached to this block.
		for (BlockFace face : faceList ) {
			if ( 
					((SpoutBlock)block.getRelative(face)).getCustomBlock() != null &&
					((SpoutBlock)block.getRelative(face)).getCustomBlock() instanceof RedCableBlock &&
					((RedCableBlock)((SpoutBlock)block.getRelative(face)).getCustomBlock()).getWireEnds().contains(face.getOppositeFace())
					) {
				
				Debug.debug("This Block has a cable block attached");
				SpoutBlock relblock = (SpoutBlock)block.getRelative(face);
				int power = 0;
				if (event.getNewCurrent() > 0) power = 1;
				RedCablePowerEvent revent = new RedCablePowerEvent(relblock, face, power);
				Bukkit.getServer().getPluginManager().callEvent(revent);
			}
		}
		
	}
	
	@EventHandler
	public void onRedCablePower(RedCablePowerEvent event) {
		
		SpoutBlock block = (SpoutBlock)event.getBlock();
		RedCableBlock cableBlock = (RedCableBlock)block.getCustomBlock();
		
		Debug.debug("RedCablePowerEvent || newPower: ", event.getNewPower(), " || face: ", event.getFace(), " || faceComingFrom: ", event.getFaceComingFrom());
		
		List<BlockFace> wireFaces = cableBlock.getWireEnds();
		for ( BlockFace wireFace : wireFaces ) {
			//if its the face where the power is coming from, we should NOT return the power.
			//only set power to the face other than the face where the power is coming from.
			if (!wireFace.equals(event.getFaceComingFrom())) {
				cableBlock.setFacePower(block, wireFace, event.getNewPower());
				
				Vector motion = new Vector();
				motion.setX(0);
				motion.setY(0);
				motion.setZ(0);
				Particle electrify = new Particle(ParticleType.SPLASH, event.getBlock().getLocation(), motion);
				electrify.setGravity(0.1F);
				electrify.setAmount(100);
				electrify.spawn();
				
				//notify a wireblock if it is connected to this face.
				if (
						(block.getRelative(wireFace)).getCustomBlock() != null &&
						(block.getRelative(wireFace)).getCustomBlock() instanceof RedCableBlock &&
						((RedCableBlock)(block.getRelative(wireFace)).getCustomBlock()).getWireEnds().contains(wireFace.getOppositeFace())
						) {
					Debug.debug("This Block has a cable block attached");
					SpoutBlock relblock = (SpoutBlock)block.getRelative(wireFace);
					RedCablePowerEvent revent = new RedCablePowerEvent(relblock, wireFace, event.getNewPower());
					Bukkit.getServer().getPluginManager().callEvent(revent);
					
				} else if (block.getRelative(wireFace) != null) {
					
					SpoutBlock ublock = block.getRelative(wireFace);
					if (ublock.getCustomBlock() != null && ublock.getCustomBlock() instanceof RedCableBlock) {
						//er... do nothing
					} else {
						//try to apply physics to the block if its anything other than a wire
						net.minecraft.server.World w = ((net.minecraft.server.World)((CraftWorld)ublock.getWorld()).getHandle());
						w.applyPhysics(ublock.getX(), ublock.getY(), ublock.getZ(), ublock.getTypeId());
					}
					
				}
			} 
		}
		
		//if the power of this block is more than 1, the wire has been overloaded.
		if (cableBlock.getTotalPower(block) > 1) {
			Debug.debug("Wire Overload!");
			block.breakNaturally();
			return;
		}

	}
	
	@EventHandler
	public void onPlace(RedCablePlaceEvent event) {
		Debug.debug(event.getPlayer(), "Placing Speaker Wire");
		
		HashMap<BlockFace, SpoutBlock> wires = getAvailableWires(event.getBlock());
		
		Debug.debug(event.getPlayer(), "Surrounding Wires with open ends: ", wires.size());
		Debug.debug(event.getPlayer(), "Placing Wire");
		
		
		event.getBlock().setCustomBlock(Blocks.redCableBlockEastWest);
		
		setBlockType((SpoutBlock)event.getBlock(), wires);
		
		Vector motion = new Vector();
		motion.setX(0);
		motion.setY(0);
		motion.setZ(0);
		Particle electrify = new Particle(ParticleType.SPLASH, event.getBlock().getLocation(), motion);
		electrify.setGravity(0.1F);
		electrify.setAmount(100);
		electrify.spawn();
		
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
