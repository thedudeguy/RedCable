package org.ccdd.redcable.materials.blocks;

import org.ccdd.redcable.RedCable;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireEastDown;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireEastSouth;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireEastUp;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireEastWest;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireNorthDown;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireNorthEast;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireNorthSouth;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireNorthUp;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireSouthDown;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireSouthUp;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireSouthWest;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireUpDown;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireWestDown;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireWestNorth;
import org.ccdd.redcable.materials.blocks.wires.SpeakerWireWestUp;
import org.getspout.spoutapi.block.design.Texture;

public class Blocks {

	public static final Texture speakerwireTexture = new Texture(RedCable.instance, "speakerwireblock.png", 256, 256, 16);
	
	public static SpeakerWireBlock speakerWireBlockEastWest;
	public static SpeakerWireBlock speakerWireBlockNorthSouth;
	
	public static SpeakerWireBlock speakerWireBlockNorthEast;
	public static SpeakerWireBlock speakerWireBlockEastSouth;
	public static SpeakerWireBlock speakerWireBlockSouthWest;
	public static SpeakerWireBlock speakerWireBlockWestNorth;
	
	public static SpeakerWireBlock speakerWireBlockUpDown;
	
	public static SpeakerWireBlock speakerWireBlockEastUp;
	public static SpeakerWireBlock speakerWireBlockWestUp;
	public static SpeakerWireBlock speakerWireBlockNorthUp;
	public static SpeakerWireBlock speakerWireBlockSouthUp;
	
	public static SpeakerWireBlock speakerWireBlockEastDown;
	public static SpeakerWireBlock speakerWireBlockWestDown;
	public static SpeakerWireBlock speakerWireBlockNorthDown;
	public static SpeakerWireBlock speakerWireBlockSouthDown;
	
	public Blocks() {
		
		speakerWireBlockEastWest = new SpeakerWireEastWest();
		speakerWireBlockNorthSouth = new SpeakerWireNorthSouth();
		
		speakerWireBlockNorthEast = new SpeakerWireNorthEast();
		speakerWireBlockEastSouth = new SpeakerWireEastSouth();
		speakerWireBlockSouthWest = new SpeakerWireSouthWest();
		speakerWireBlockWestNorth = new SpeakerWireWestNorth();
		
		speakerWireBlockUpDown = new SpeakerWireUpDown();
		
		speakerWireBlockEastUp = new SpeakerWireEastUp();
		speakerWireBlockWestUp = new SpeakerWireWestUp();
		speakerWireBlockNorthUp = new SpeakerWireNorthUp();
		speakerWireBlockSouthUp = new SpeakerWireSouthUp();
		
		speakerWireBlockEastDown = new SpeakerWireEastDown();
		speakerWireBlockWestDown = new SpeakerWireWestDown();
		speakerWireBlockNorthDown = new SpeakerWireNorthDown();
		speakerWireBlockSouthDown = new SpeakerWireSouthDown();
		
	}
}
