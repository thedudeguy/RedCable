package org.ccdd.redcable.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.block.SpoutBlock;

public class RedCablePowerEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	private SpoutBlock block;
	private int oldCurrent;
	private int newCurrent;
	
	public RedCablePowerEvent(SpoutBlock block, int oldCurrent, int newCurrent) {
		this.oldCurrent = oldCurrent;
		this.newCurrent = newCurrent;
		this.block = block;
	}
	
	public int getOldCurrent() {
		return oldCurrent;
	}
	
	public int getNewCurrent() {
		return newCurrent;
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
