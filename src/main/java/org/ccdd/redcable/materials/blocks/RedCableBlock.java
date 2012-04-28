package org.ccdd.redcable.materials.blocks;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.ccdd.redcable.RedCable;
import org.ccdd.redcable.materials.items.Items;
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
	
}
