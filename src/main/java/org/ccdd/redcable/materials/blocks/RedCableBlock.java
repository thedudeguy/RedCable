package org.ccdd.redcable.materials.blocks;

import org.bukkit.Material;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.ccdd.redcable.RedCable;
import org.ccdd.redcable.events.RedCablePowerEvent;
import org.ccdd.redcable.materials.items.Items;
import org.ccdd.redcable.util.Debug;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

public abstract class RedCableBlock extends GenericCustomBlock {
	
	public static final int EASTtoWEST = 0;
	public static final int WESTtoEAST = 0;
	public static final int NORTHtoSOUTH = 1;
	public static final int SOUTHtoNORTH = 1;
	
	public static final int NORTHtoEAST = 2;
	public static final int EASTtoNORTH = 2;
	
	public static final int EASTtoSOUTH = 3;
	public static final int SOUTHtoEAST = 3;
	
	public static final int SOUTHtoWEST = 4;
	public static final int WESTtoSOUTH = 4;
	
	public static final int WESTtoNORTH = 5;
	public static final int NORTHtoWEST = 5;
	
	public static final int UPtoDOWN = 6;
	public static final int DOWNtoUP = 6;
	
	public static final int EASTtoUP = 7;
	public static final int UPtoEAST = 7;
	
	public static final int WESTtoUP = 8;
	public static final int UPtoWEST = 8;
	
	public static final int NORTHtoUP = 9;
	public static final int UPtoNORTH = 9;
	
	public static final int SOUTHtoUP = 10;
	public static final int UPtoSOUTH = 10;
	
	public static final int WESTtoDOWN = 11;
	public static final int DOWNtoWEST = 11;
	
	public static final int EASTtoDOWN = 12;
	public static final int DOWNtoEAST = 12;
	
	public static final int NORTHtoDOWN = 13;
	public static final int DOWNtoNORTH = 13;
	
	public static final int SOUTHtoDOWN = 14;
	public static final int DOWNtoSOUTH = 14;
	
	protected int type;
	
	public RedCableBlock(int type) {
		this(type, Material.STEP.getId()); // for material visibility
	}
	
	public RedCableBlock(int type, int baseBlock) {
		super(RedCable.instance, "redcableblock_"+String.valueOf(type), baseBlock);

		setType(type);
		
		this.setName("RedCable Block "+ String.valueOf(type) + " (DO NOT USE)");
		this.setItemDrop(new SpoutItemStack(Items.redCable));
		this.setHardness(0.1F);
	}
	
	private void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	@Override
	public boolean isIndirectlyProvidingPowerTo(org.bukkit.World world, int x, int y, int z, org.bukkit.block.BlockFace face) {
		
		Debug.debug("isInderectlyProvidingPowerTo || face: ", face);
		
		return false;
	}
	
	/**
	 * Face is the the face of another block which is looking toward this block to request if it has power, I beleive
	 * so if this wire is a eastsouth wire, the block the the south will ask on its north face, to see if this block has power.
	 * to know if we have power, we can look toward our east.
	 */
	@Override
	public boolean isProvidingPowerTo(org.bukkit.World world, int x, int y, int z, org.bukkit.block.BlockFace face) {
		
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);
		
		int power = ((RedCableBlock)block.getCustomBlock()).getPower(block);
		
		Debug.debug("isProvidingPowerTo || face: ", face, " || block: ", block.getBlockType().getName(), " || Power: ", power);
		
		List<BlockFace> wireList = ((RedCableBlock)block.getCustomBlock()).getWireEnds();
		
		if (!wireList.contains(face.getOppositeFace())) return false;
		
		return false;
		
	}
	
	@Override
	public void onNeighborBlockChange(org.bukkit.World world, int x, int y, int z, int changedId) {
		
		Debug.debug("onNeighborBlockChange || changeId: ", changedId);
		
		int totalPower = 0;
		SpoutBlock block = (SpoutBlock)world.getBlockAt(x, y, z);
		
		//each wire has 2 faces which can receive power, check those 2 faces for receiving power.
		List<BlockFace> faces = ((RedCableBlock)block.getCustomBlock()).getWireEnds();
		for (BlockFace face : faces ) {
			
			if (
					((SpoutBlock)block.getRelative(face)).isBlockFacePowered(face.getOppositeFace()) ||
					((SpoutBlock)block.getRelative(face)).isBlockFaceIndirectlyPowered(face.getOppositeFace())
					) {
				
				Debug.debug("block at face ", face, " has their face ", face.getOppositeFace(), " powered");
				
				totalPower += ((SpoutBlock)block.getRelative(face)).getBlockPower(face.getOppositeFace());
				
			}
		}
		
		//since we dont need to keep track of how much power is in the block, we will just max it out at 1
		if (totalPower > 1) totalPower = 1;
		
		RedCablePowerEvent event = new RedCablePowerEvent(block, getPower(block), totalPower, null);
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		
	}
	
	/**
	 * gets the block data determining if it is powered.
	 * @param block
	 * @return
	 */
	public Integer getPower(SpoutBlock block) {
		
		if ( (Integer)block.getData("redcable.power") == null) return 0; 
		return (Integer)block.getData("redcable.power");
	}
	
	/**
	 * sets the data for the block if it is powered or not.
	 * @param block
	 * @param power
	 */
	public void setPower(SpoutBlock block, Integer power) {
		block.setData("redcable.power", power);
	}
	
	public boolean isFaceConnected(SpoutBlock block, BlockFace face) {
		
		if (
				((RedCableBlock)((SpoutBlock)block.getRelative(face)).getCustomBlock()) == null
				) {
			return false;
		}
		
		else if (face.equals(BlockFace.NORTH)) {
			if (
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock()).getType() == RedCableBlock.SOUTHtoNORTH ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock()).getType() == RedCableBlock.SOUTHtoEAST ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock()).getType() == RedCableBlock.SOUTHtoWEST ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock()).getType() == RedCableBlock.SOUTHtoDOWN ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.NORTH)).getCustomBlock()).getType() == RedCableBlock.SOUTHtoUP
					) {
				return true;
			}
			
		}
		
		else if (face.equals(BlockFace.EAST)) {
			if (
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock()).getType() == RedCableBlock.WESTtoEAST ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock()).getType() == RedCableBlock.WESTtoNORTH ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock()).getType() == RedCableBlock.WESTtoSOUTH ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock()).getType() == RedCableBlock.WESTtoUP ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.EAST)).getCustomBlock()).getType() == RedCableBlock.WESTtoDOWN
					) {
				return true;
			}
			
		} 
		
		else if (face.equals(BlockFace.SOUTH)) {
			if (
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock()).getType() == RedCableBlock.NORTHtoEAST ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock()).getType() == RedCableBlock.NORTHtoSOUTH ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock()).getType() == RedCableBlock.NORTHtoWEST ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock()).getType() == RedCableBlock.NORTHtoDOWN ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.SOUTH)).getCustomBlock()).getType() == RedCableBlock.NORTHtoUP
					) {
				return true;
			}
			
		} 
		
		else if (face.equals(BlockFace.WEST)) {
			if (
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock()).getType() == RedCableBlock.EASTtoNORTH ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock()).getType() == RedCableBlock.EASTtoSOUTH ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock()).getType() == RedCableBlock.EASTtoWEST ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock()).getType() == RedCableBlock.EASTtoDOWN ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.WEST)).getCustomBlock()).getType() == RedCableBlock.EASTtoUP
					) {
				return true;
			}
			
		}
		
		else if (face.equals(BlockFace.UP)) {
			if (
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock()).getType() == RedCableBlock.DOWNtoEAST ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock()).getType() == RedCableBlock.DOWNtoNORTH ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock()).getType() == RedCableBlock.DOWNtoSOUTH ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock()).getType() == RedCableBlock.DOWNtoUP ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.UP)).getCustomBlock()).getType() == RedCableBlock.DOWNtoWEST
					) {
				return true;
			}
			
		}
		
		else if (face.equals(BlockFace.DOWN)) {
			if (
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock()).getType() == RedCableBlock.UPtoDOWN ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock()).getType() == RedCableBlock.UPtoEAST ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock()).getType() == RedCableBlock.UPtoNORTH ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock()).getType() == RedCableBlock.UPtoSOUTH ||
					((RedCableBlock)((SpoutBlock)block.getRelative(BlockFace.DOWN)).getCustomBlock()).getType() == RedCableBlock.UPtoWEST
					) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public abstract boolean hasOpenEnd(SpoutBlock block);
	
	public abstract List<BlockFace> getWireEnds();
}
