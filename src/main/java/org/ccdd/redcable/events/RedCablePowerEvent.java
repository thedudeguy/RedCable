package org.ccdd.redcable.events;

import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCablePowerEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	private SpoutBlock block;
	private BlockFace face;
	private int newPower;
	
	public RedCablePowerEvent(SpoutBlock block, BlockFace face, int newPower) {
		this.newPower = newPower;
		this.block = block;
		this.face = face;
	}
	
	public int getNewPower() {
		return newPower;
	}
	
	public BlockFace getFaceComingFrom() {
		return face.getOppositeFace();
	}
	
	public BlockFace getFace() {
		return face;
	}
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	
	public SpoutBlock getBlock() {
		return block;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
