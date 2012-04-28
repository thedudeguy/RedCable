package org.ccdd.redcable.materials.blocks;

import org.ccdd.redcable.RedCable;
import org.ccdd.redcable.materials.blocks.wires.RedCableEastDown;
import org.ccdd.redcable.materials.blocks.wires.RedCableEastSouth;
import org.ccdd.redcable.materials.blocks.wires.RedCableEastUp;
import org.ccdd.redcable.materials.blocks.wires.RedCableEastWest;
import org.ccdd.redcable.materials.blocks.wires.RedCableNorthDown;
import org.ccdd.redcable.materials.blocks.wires.RedCableNorthEast;
import org.ccdd.redcable.materials.blocks.wires.RedCableNorthSouth;
import org.ccdd.redcable.materials.blocks.wires.RedCableNorthUp;
import org.ccdd.redcable.materials.blocks.wires.RedCableSouthDown;
import org.ccdd.redcable.materials.blocks.wires.RedCableSouthUp;
import org.ccdd.redcable.materials.blocks.wires.RedCableSouthWest;
import org.ccdd.redcable.materials.blocks.wires.RedCableUpDown;
import org.ccdd.redcable.materials.blocks.wires.RedCableWestDown;
import org.ccdd.redcable.materials.blocks.wires.RedCableWestNorth;
import org.ccdd.redcable.materials.blocks.wires.RedCableWestUp;
import org.getspout.spoutapi.block.design.Texture;

import java.util.HashSet;

public class Blocks {

	public static final Texture speakerwireTexture = new Texture(RedCable.instance, "speakerwireblock.png", 256, 256, 16);
	
	public static RedCableBlock redCableBlockEastWest;
	public static RedCableBlock redCableBlockNorthSouth;
	
	public static RedCableBlock redCableBlockNorthEast;
	public static RedCableBlock redCableBlockEastSouth;
	public static RedCableBlock redCableBlockSouthWest;
	public static RedCableBlock redCableBlockWestNorth;
	
	public static RedCableBlock redCableBlockUpDown;
	
	public static RedCableBlock redCableBlockEastUp;
	public static RedCableBlock redCableBlockWestUp;
	public static RedCableBlock redCableBlockNorthUp;
	public static RedCableBlock redCableBlockSouthUp;
	
	public static RedCableBlock redCableBlockEastDown;
	public static RedCableBlock redCableBlockWestDown;
	public static RedCableBlock redCableBlockNorthDown;
	public static RedCableBlock redCableBlockSouthDown;

    private HashSet<RedCableBlock> blockSet = new HashSet<RedCableBlock>(){{
        add(redCableBlockEastDown);
        add(redCableBlockNorthSouth);

        add(redCableBlockNorthEast);
        add(redCableBlockEastSouth);
        add(redCableBlockSouthWest);
        add(redCableBlockWestNorth);

        add(redCableBlockUpDown);

        add(redCableBlockEastUp);
        add(redCableBlockWestUp);
        add(redCableBlockNorthUp);
        add(redCableBlockSouthUp);

        add(redCableBlockEastDown);
        add(redCableBlockWestDown);
        add(redCableBlockNorthDown);
        add(redCableBlockSouthDown);
    }};
	
	public Blocks() {
		
		redCableBlockEastWest = new RedCableEastWest();
		redCableBlockNorthSouth = new RedCableNorthSouth();
		
		redCableBlockNorthEast = new RedCableNorthEast();
		redCableBlockEastSouth = new RedCableEastSouth();
		redCableBlockSouthWest = new RedCableSouthWest();
		redCableBlockWestNorth = new RedCableWestNorth();
		
		redCableBlockUpDown = new RedCableUpDown();
		
		redCableBlockEastUp = new RedCableEastUp();
		redCableBlockWestUp = new RedCableWestUp();
		redCableBlockNorthUp = new RedCableNorthUp();
		redCableBlockSouthUp = new RedCableSouthUp();
		
		redCableBlockEastDown = new RedCableEastDown();
		redCableBlockWestDown = new RedCableWestDown();
		redCableBlockNorthDown = new RedCableNorthDown();
		redCableBlockSouthDown = new RedCableSouthDown();
		
	}
    public HashSet<RedCableBlock> getBlockSet(){
        return blockSet;
    }
}
