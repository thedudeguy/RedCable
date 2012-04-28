package org.ccdd.redcable.events;

import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCablePowerEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	private SpoutBlock block;
	private int oldCurrent;
	private int newCurrent;
	private BlockFace face;
	
	public RedCablePowerEvent(SpoutBlock block, int oldCurrent, int newCurrent, BlockFace face) {
		this.oldCurrent = oldCurrent;
		this.newCurrent = newCurrent;
		this.block = block;
		this.face = face;
	}
	
	public int getOldCurrent() {
		return oldCurrent;
	}
	
	public int getNewCurrent() {
		return newCurrent;
	}
	
	public BlockFace getFaceComingFrom() {
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
