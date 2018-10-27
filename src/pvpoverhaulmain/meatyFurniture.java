package pvpoverhaulmain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Stairs;

public class meatyFurniture {
	
	public static Random rander = new Random();
	
	int[][] chairWithBanner = {{0,0,0},{1,2,1},{0,3,0}};
	int[][] chairWithBanner2 = {{0,0,0},{0,0,0},{0,3,0}};
	List<int[][]> chairBanner = new ArrayList<int[][]>();
	
	public static void meatFurn(Player p, int i) {
		//long chair
		if(i==1) {
			makeLongChair(p, i);
			p.damage(9999);
			return;
		}
		else if(i==2) {
			makeCornerChair(p, i);
			p.damage(9999);
			return;
		}
		else if(i==3) {
			makeCarpet(p, i);
			p.damage(9999);
			return;
		}
		else if(i==4) {
			makeBannerTest(p, i);
			p.damage(9999);
			return;
		}
		else if(i==5) {
			makeBed(p, i);
			p.damage(9999);
			return;
		}
		else if(i==6) {
			makeTable(p, i);
			p.damage(9999);
			return;
		}
	}
	
	public static void meatFurnL(Location l, int i) {
		//long chair
		if(i==1) {
			makeLongChairL(l, i);
			return;
		}
		else if(i==2) {
			makeCornerChairL(l, i);
			return;
		}
		else if(i==3) {
			makeCarpetL(l, i);
			return;
		}
		else if(i==4) {
			makeBannerTestL(l, i);
			return;
		}
		else if(i==5) {
			makeBedL(l, i);
			return;
		}
		else if(i==6) {
			makeTableL(l, i);
			return;
		}
	}
	
	public static void createFurn(Player p, int i) {
		int r = rander.nextInt(4);
		if(i==5) {
			
		}
	}
	
	public void makeC(Player p, int r) {
		try {
		List<int[][]> chairBanner2 = new ArrayList<int[][]>();
		int height = 0;
		for(int[][] arry : chairBanner2) {
			for(int row = 0; arry.length > row; row++) {
				for(int col = 0; arry[0].length > col; col++) {
					Location newLoc = p.getLocation();
					newLoc.add(row,height,col);
					makeB(newLoc, arry[row][col]);
				}
			}
			height=height+1;
		}}
		catch(Exception e) {
			
		}
	}
	
	public void makeB(Location l, int block){
		try {
		Block b = l.getBlock();
		if(b==null) {
			return;
		}
		if(b.getType()!=Material.AIR) {
			return;
		}
		if(block == 0) {
			return;
		}
		else if(block == 1) {
			b.setType(Material.WALL_BANNER);
		}
		else if(block == 2) {
			b.setType(Material.QUARTZ_STAIRS);
		}
		else if(block == 3) {
			b.setType(Material.QUARTZ_BLOCK);
		}}
	catch(Exception e) {
		
	}
	}
	
	public static void chooseBoneBlock(Location loc) {
		try {
		int choose = rander.nextInt(2);
		if(loc.getBlock().getType()!=Material.AIR) {
			return;
		}
		else {
		if(choose==0) {
			loc.getBlock().setType(Material.BONE_BLOCK);
		}
		if(choose==1) {
			loc.getBlock().setType(Material.QUARTZ_BLOCK);
		}
		}
		}
		catch(Exception e) {
			
		}
	}
	
	public static void makeLongChair(Player p, int i) {
try {
		int choose = rander.nextInt(2);
		Location start = p.getLocation();
		start.getBlock().setType(Material.QUARTZ_STAIRS);
		if(choose==0) {
			Location start2 = p.getLocation();
			start2.add(1, 0, 0);
			Location start3 = p.getLocation();
			start3.subtract(1, 0, 0);
			if(start2.getBlock().getType()==Material.AIR) {
			if(rander.nextInt(2)==1) {
			start2.getBlock().setType(Material.QUARTZ_BLOCK);
			}
			else {
				start2.getBlock().setType(Material.BONE_BLOCK);
			}
			}
			if(start3.getBlock().getType()==Material.AIR) {
			if(rander.nextInt(2)==1) {
				start3.getBlock().setType(Material.QUARTZ_BLOCK);
				}
				else {
					start3.getBlock().setType(Material.BONE_BLOCK);
				}
			}
			   Stairs stairs = new Stairs(Material.QUARTZ);
			   if(rander.nextInt(2)==1) {
               stairs.setFacingDirection(BlockFace.NORTH);
			   }
			   else {
				   stairs.setFacingDirection(BlockFace.SOUTH);   
			   }
               start.getBlock().setData(stairs.getData());
		}
		else {
			Location start2 = p.getLocation();
			start2.add(0, 0, 1);
			Location start3 = p.getLocation();
			start3.subtract(0, 0, 1);
			if(start2.getBlock().getType()==Material.AIR) {
				if(rander.nextInt(2)==1) {
			start2.getBlock().setType(Material.QUARTZ_BLOCK);
			}
			else {
				start2.getBlock().setType(Material.BONE_BLOCK);
			}
			}
			if(start3.getBlock().getType()==Material.AIR) {
				if(rander.nextInt(2)==1) {
				start3.getBlock().setType(Material.QUARTZ_BLOCK);
				}
				else {
					start3.getBlock().setType(Material.BONE_BLOCK);
				}
			}
			 Stairs stairs = new Stairs(Material.QUARTZ);
			   if(rander.nextInt(2)==1) {
             stairs.setFacingDirection(BlockFace.EAST);
			   }
			   else {
				   stairs.setFacingDirection(BlockFace.WEST);   
			   }
             start.getBlock().setData(stairs.getData());
		}}
catch(Exception e) {
	
}
	}

	public static void makeCornerChair(Player p, int i) {
		try {
		int choose = rander.nextInt(4);
		Location start = p.getLocation();
		start.getBlock().setType(Material.STEP);
		start.getBlock().setData((byte) 7);
		if(choose==0) {
			Location start2 = p.getLocation();
			start2.add(1, 0, 0);
			chooseBoneBlock(start2);
			Location start3 = p.getLocation();
			start3.add(1, 0, 1);
			chooseBoneBlock(start3);
			Location start4 = p.getLocation();
			start4.add(0, 0, 1);
			chooseBoneBlock(start4);
		}
		else if(choose==1) {
			Location start2 = p.getLocation();
			start2.subtract(1, 0, 0);
			chooseBoneBlock(start2);
			Location start3 = p.getLocation();
			start3.subtract(1, 0, 1);
			chooseBoneBlock(start3);
			Location start4 = p.getLocation();
			start4.subtract(0, 0, 1);
			chooseBoneBlock(start4);
		}
		else if(choose==2) {
			Location start2 = p.getLocation();
			start2.add(1, 0, 0);
			chooseBoneBlock(start2);
			Location start3 = p.getLocation();
			start3.add(1, 0, 0);
			start3.subtract(0, 0, 1);
			chooseBoneBlock(start3);
			Location start4 = p.getLocation();
			start4.subtract(0, 0, 1);
			chooseBoneBlock(start4);
		}
		else if(choose==3) {
			Location start2 = p.getLocation();
			start2.subtract(1, 0, 0);
			chooseBoneBlock(start2);
			Location start3 = p.getLocation();
			start3.add(0, 0, 1);
			start3.subtract(1, 0, 0);
			chooseBoneBlock(start3);
			Location start4 = p.getLocation();
			start4.add(0, 0, 1);
			chooseBoneBlock(start4);
		}}
		catch(Exception e) {
			
		}
	}
	
	public static void makeCarpet(Player p, int i) {
		try {
		int choose = rander.nextInt(2);
		Location start = p.getLocation();
		if(choose==0) {
			start.getBlock().setType(Material.CARPET);
			start.getBlock().setData(((byte) 14));
		}
		else if(choose==1) {
			start.getBlock().setType(Material.CARPET);
		}
		}
		catch(Exception e) {
			
		}
	}
	
	public static void makeBannerTest(Player p, int i) {
		try {
		int choose = rander.nextInt(4);
		Location start = p.getLocation();
		if(choose==0) {
			start.getBlock().setType(Material.QUARTZ_STAIRS);
			start.add(1, 0, 0);
			if(start.getBlock().getType()==Material.AIR) {
			start.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState = start.getBlock().getState();
			blockState.setType(start.getBlock().getType());
			MaterialData blockData = blockState.getData();
			blockData.setData((byte) 5);
			blockState.update(true);
			start.getBlock().getState().setData(blockData);
			Banner banner = (Banner)start.getBlock().getState();
			banner.setBaseColor(DyeColor.WHITE);
			banner.update();
			}
			Location start2 = p.getLocation();
			start2.subtract(1,0,0);
			if(start2.getBlock().getType()==Material.AIR) {
			start2.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState2 = start2.getBlock().getState();
			blockState2.setType(start2.getBlock().getType());
			MaterialData blockData2 = blockState2.getData();
			blockData2.setData((byte) 4);
			blockState2.update(true);
			start2.getBlock().getState().setData(blockData2);
			Banner banner2 = (Banner)start2.getBlock().getState();
			banner2.setBaseColor(DyeColor.WHITE);
			banner2.update();
			}
			//start.getBlock().getState().setRawData((byte) 5);
			//Banner b = (Banner) start.getBlock();
		}
		else if(choose==1) {
			start.getBlock().setType(Material.QUARTZ_STAIRS);
			Stairs stairs = new Stairs(Material.QUARTZ);
            stairs.setFacingDirection(BlockFace.EAST);
            start.getBlock().setData(stairs.getData());
			start.add(0, 0, 1);
			if(start.getBlock().getType()==Material.AIR) {
			start.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState = start.getBlock().getState();
			blockState.setType(start.getBlock().getType());
			MaterialData blockData = blockState.getData();
			blockData.setData((byte) 3);
			blockState.update(true);
			start.getBlock().getState().setData(blockData);
			Banner banner = (Banner)start.getBlock().getState();
			banner.setBaseColor(DyeColor.WHITE);
			banner.update();
			}
			
			Location start2 = p.getLocation();
			start2.subtract(0,0,1);
			if(start2.getBlock().getType()==Material.AIR) {
			start2.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState2 = start2.getBlock().getState();
			blockState2.setType(start2.getBlock().getType());
			MaterialData blockData2 = blockState2.getData();
			blockData2.setData((byte) 2);
			blockState2.update(true);
			start2.getBlock().getState().setData(blockData2);
			Banner banner2 = (Banner)start2.getBlock().getState();
			banner2.setBaseColor(DyeColor.WHITE);
			banner2.update();
			}
			//start.getBlock().getState().setRawData((byte) 5);
			//Banner b = (Banner) start.getBlock();
		}
		else if(choose==2) {
			start.getBlock().setType(Material.QUARTZ_STAIRS);
			Stairs stairs = new Stairs(Material.QUARTZ);
            stairs.setFacingDirection(BlockFace.NORTH);
            start.getBlock().setData(stairs.getData());
			start.add(1, 0, 0);
			if(start.getBlock().getType()==Material.AIR) {
			start.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState = start.getBlock().getState();
			blockState.setType(start.getBlock().getType());
			MaterialData blockData = blockState.getData();
			blockData.setData((byte) 5);
			blockState.update(true);
			start.getBlock().getState().setData(blockData);
			Banner banner = (Banner)start.getBlock().getState();
			banner.setBaseColor(DyeColor.WHITE);
			banner.update();
			}
			Location start2 = p.getLocation();
			start2.subtract(1,0,0);
			if(start2.getBlock().getType()==Material.AIR) {
			start2.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState2 = start2.getBlock().getState();
			blockState2.setType(start2.getBlock().getType());
			MaterialData blockData2 = blockState2.getData();
			blockData2.setData((byte) 4);
			blockState2.update(true);
			start2.getBlock().getState().setData(blockData2);
			Banner banner2 = (Banner)start2.getBlock().getState();
			banner2.setBaseColor(DyeColor.WHITE);
			banner2.update();
			}
			//start.getBlock().getState().setRawData((byte) 5);
			//Banner b = (Banner) start.getBlock();
		}
		else if(choose==3) {
			start.getBlock().setType(Material.QUARTZ_STAIRS);
			Stairs stairs = new Stairs(Material.QUARTZ);
            stairs.setFacingDirection(BlockFace.WEST);
            start.getBlock().setData(stairs.getData());
			start.add(0, 0, 1);
			if(start.getBlock().getType()==Material.AIR) {
			start.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState = start.getBlock().getState();
			blockState.setType(start.getBlock().getType());
			MaterialData blockData = blockState.getData();
			blockData.setData((byte) 3);
			blockState.update(true);
			start.getBlock().getState().setData(blockData);
			Banner banner = (Banner)start.getBlock().getState();
			banner.setBaseColor(DyeColor.WHITE);
			banner.update();
			}
			Location start2 = p.getLocation();
			start2.subtract(0,0,1);
			if(start2.getBlock().getType()==Material.AIR) {
			start2.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState2 = start2.getBlock().getState();
			blockState2.setType(start2.getBlock().getType());
			MaterialData blockData2 = blockState2.getData();
			blockData2.setData((byte) 2);
			blockState2.update(true);
			start2.getBlock().getState().setData(blockData2);
			Banner banner2 = (Banner)start2.getBlock().getState();
			banner2.setBaseColor(DyeColor.WHITE);
			banner2.update();
			}
			//start.getBlock().getState().setRawData((byte) 5);
			//Banner b = (Banner) start.getBlock();
		}}
		catch(Exception e) {
			
		}
		
	}
	
	public static void setFlesh(Location l) {
		try {
		int i = rander.nextInt(2);
		if(i==0) {
			l.getBlock().setTypeIdAndData(35, DyeColor.RED.getWoolData(), true);
		}
		else {
			l.getBlock().setTypeIdAndData(251, (byte) 14, true);
		}
		}
		catch(Exception e) {
			
		}
	}

	public static void makeBed(Player p, int i) {
		try {
		int choose = rander.nextInt(4);
		if(choose==0) {
			Location up1 = p.getLocation();
			setFlesh(up1);
			up1.add(1, 0, 0);
			setFlesh(up1);
			up1.add(1, 0, 0);
			setFlesh(up1);
			up1.add(0, 1, 0);
			up1.getBlock().setType(Material.SNOW);
			up1.getBlock().setData((byte) 1);
			Location up2 = p.getLocation();
			up2.add(0, 0, 1);
			setFlesh(up2);
			up2.add(1, 0, 0);
			setFlesh(up2);
			up2.add(1, 0, 0);
			setFlesh(up2);
			up2.add(0, 1, 0);
			up2.getBlock().setType(Material.SNOW);
			up2.getBlock().setData((byte) 2);
		}
		else if(choose==1) {
			Location up1 = p.getLocation();
			setFlesh(up1);
			up1.subtract(1, 0, 0);
			setFlesh(up1);
			up1.subtract(1, 0, 0);
			setFlesh(up1);
			up1.add(0, 1, 0);
			up1.getBlock().setType(Material.SNOW);
			up1.getBlock().setData((byte) 1);
			Location up2 = p.getLocation();
			up2.add(0, 0, 1);
			setFlesh(up2);
			up2.subtract(1, 0, 0);
			setFlesh(up2);
			up2.subtract(1, 0, 0);
			setFlesh(up2);
			up2.add(0, 1, 0);
			up2.getBlock().setType(Material.SNOW);
			up2.getBlock().setData((byte) 2);
		}
		else if(choose==2) {
			Location up1 = p.getLocation();
			setFlesh(up1);
			up1.subtract(0, 0, 1);
			setFlesh(up1);
			up1.subtract(0, 0, 1);
			setFlesh(up1);
			up1.add(0, 1, 0);
			up1.getBlock().setType(Material.SNOW);
			up1.getBlock().setData((byte) 1);
			Location up2 = p.getLocation();
			up2.add(1, 0, 0);
			setFlesh(up2);
			up2.subtract(0, 0, 1);
			setFlesh(up2);
			up2.subtract(0, 0, 1);
			setFlesh(up2);
			up2.add(0, 1, 0);
			up2.getBlock().setType(Material.SNOW);
			up2.getBlock().setData((byte) 2);
		}
		else if(choose==3) {
			Location up1 = p.getLocation();
			setFlesh(up1);
			up1.add(0, 0, 1);
			setFlesh(up1);
			up1.add(0, 0, 1);
			setFlesh(up1);
			up1.add(0, 1, 0);
			up1.getBlock().setType(Material.SNOW);
			up1.getBlock().setData((byte) 1);
			Location up2 = p.getLocation();
			up2.subtract(1, 0, 0);
			setFlesh(up2);
			up2.add(0, 0, 1);
			setFlesh(up2);
			up2.add(0, 0, 1);
			setFlesh(up2);
			up2.add(0, 1, 0);
			up2.getBlock().setType(Material.SNOW);
			up2.getBlock().setData((byte) 2);
		}
		//domeatyfurniture 5
		}
		catch(Exception e) {
			
		}
	}

	public static void makeTable(Player p, int i) {
		try {
		Location n1 = p.getLocation();
		n1.getBlock().setType(Material.RED_SHULKER_BOX);
		n1.add(0, 1, 0);
		if(rander.nextInt(2)==1) {
		n1.getBlock().setTypeIdAndData(171, (byte) 14, true);
		}
		else {
			n1.getBlock().setType(Material.REDSTONE_TORCH_ON);	
		}}
		catch(Exception e) {
			
		}
	}


	public static void makeLongChairL(Location l, int i) {
		try {
		int choose = rander.nextInt(2);
		Location start = l;
		start.getBlock().setType(Material.QUARTZ_STAIRS);
		if(choose==0) {
			Location start2 = l;
			start2.add(1, 0, 0);
			Location start3 = l;
			start3.subtract(1, 0, 0);
			if(start2.getBlock().getType()==Material.AIR) {
			if(rander.nextInt(2)==1) {
			start2.getBlock().setType(Material.QUARTZ_BLOCK);
			}
			else {
				start2.getBlock().setType(Material.BONE_BLOCK);
			}
			}
			if(start3.getBlock().getType()==Material.AIR) {
			if(rander.nextInt(2)==1) {
				start3.getBlock().setType(Material.QUARTZ_BLOCK);
				}
				else {
					start3.getBlock().setType(Material.BONE_BLOCK);
				}
			}
			   Stairs stairs = new Stairs(Material.QUARTZ);
			   if(rander.nextInt(2)==1) {
               stairs.setFacingDirection(BlockFace.NORTH);
			   }
			   else {
				   stairs.setFacingDirection(BlockFace.SOUTH);   
			   }
               start.getBlock().setData(stairs.getData());
		}
		else {
			Location start2 = l;
			start2.add(0, 0, 1);
			Location start3 = l;
			start3.subtract(0, 0, 1);
			if(start2.getBlock().getType()==Material.AIR) {
				if(rander.nextInt(2)==1) {
			start2.getBlock().setType(Material.QUARTZ_BLOCK);
			}
			else {
				start2.getBlock().setType(Material.BONE_BLOCK);
			}
			}
			if(start3.getBlock().getType()==Material.AIR) {
				if(rander.nextInt(2)==1) {
				start3.getBlock().setType(Material.QUARTZ_BLOCK);
				}
				else {
					start3.getBlock().setType(Material.BONE_BLOCK);
				}
			}
			 Stairs stairs = new Stairs(Material.QUARTZ);
			   if(rander.nextInt(2)==1) {
             stairs.setFacingDirection(BlockFace.EAST);
			   }
			   else {
				   stairs.setFacingDirection(BlockFace.WEST);   
			   }
             start.getBlock().setData(stairs.getData());
		}}
		catch(Exception e) {
			
		}
	}

	public static void makeCornerChairL(Location l, int i) {
		try {
		int choose = rander.nextInt(4);
		Location start = l;
		start.getBlock().setType(Material.STEP);
		start.getBlock().setData((byte) 7);
		if(choose==0) {
			Location start2 = l;
			start2.add(1, 0, 0);
			chooseBoneBlock(start2);
			Location start3 = l;
			start3.add(1, 0, 1);
			chooseBoneBlock(start3);
			Location start4 = l;
			start4.add(0, 0, 1);
			chooseBoneBlock(start4);
		}
		else if(choose==1) {
			Location start2 = l;
			start2.subtract(1, 0, 0);
			chooseBoneBlock(start2);
			Location start3 = l;
			start3.subtract(1, 0, 1);
			chooseBoneBlock(start3);
			Location start4 = l;
			start4.subtract(0, 0, 1);
			chooseBoneBlock(start4);
		}
		else if(choose==2) {
			Location start2 = l;
			start2.add(1, 0, 0);
			chooseBoneBlock(start2);
			Location start3 = l;
			start3.add(1, 0, 0);
			start3.subtract(0, 0, 1);
			chooseBoneBlock(start3);
			Location start4 = l;
			start4.subtract(0, 0, 1);
			chooseBoneBlock(start4);
		}
		else if(choose==3) {
			Location start2 = l;
			start2.subtract(1, 0, 0);
			chooseBoneBlock(start2);
			Location start3 = l;
			start3.add(0, 0, 1);
			start3.subtract(1, 0, 0);
			chooseBoneBlock(start3);
			Location start4 = l;
			start4.add(0, 0, 1);
			chooseBoneBlock(start4);
		}}
		catch(Exception e) {
			
		}
	}
	
	public static void makeCarpetL(Location l, int i) {
		try {
		int choose = rander.nextInt(2);
		Location start = l;
		if(choose==0) {
			start.getBlock().setType(Material.CARPET);
			start.getBlock().setData(((byte) 14));
		}
		else if(choose==1) {
			start.getBlock().setType(Material.CARPET);
		}}
		catch(Exception e) {
			
		}
	}
	
	public static void makeBannerTestL(Location l, int i) {
		try {
		int choose = rander.nextInt(4);
		Location start = l;
		if(choose==0) {
			start.getBlock().setType(Material.QUARTZ_STAIRS);
			start.add(1, 0, 0);
			if(start.getBlock().getType()==Material.AIR) {
			start.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState = start.getBlock().getState();
			blockState.setType(start.getBlock().getType());
			MaterialData blockData = blockState.getData();
			blockData.setData((byte) 5);
			blockState.update(true);
			start.getBlock().getState().setData(blockData);
			Banner banner = (Banner)start.getBlock().getState();
			banner.setBaseColor(DyeColor.WHITE);
			banner.update();
			}
			Location start2 = l;
			start2.subtract(1,0,0);
			if(start2.getBlock().getType()==Material.AIR) {
			start2.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState2 = start2.getBlock().getState();
			blockState2.setType(start2.getBlock().getType());
			MaterialData blockData2 = blockState2.getData();
			blockData2.setData((byte) 4);
			blockState2.update(true);
			start2.getBlock().getState().setData(blockData2);
			Banner banner2 = (Banner)start2.getBlock().getState();
			banner2.setBaseColor(DyeColor.WHITE);
			banner2.update();
			}
			//start.getBlock().getState().setRawData((byte) 5);
			//Banner b = (Banner) start.getBlock();
		}
		else if(choose==1) {
			start.getBlock().setType(Material.QUARTZ_STAIRS);
			Stairs stairs = new Stairs(Material.QUARTZ);
            stairs.setFacingDirection(BlockFace.EAST);
            start.getBlock().setData(stairs.getData());
			start.add(0, 0, 1);
			if(start.getBlock().getType()==Material.AIR) {
			start.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState = start.getBlock().getState();
			blockState.setType(start.getBlock().getType());
			MaterialData blockData = blockState.getData();
			blockData.setData((byte) 3);
			blockState.update(true);
			start.getBlock().getState().setData(blockData);
			Banner banner = (Banner)start.getBlock().getState();
			banner.setBaseColor(DyeColor.WHITE);
			banner.update();
			}
			
			Location start2 = l;
			start2.subtract(0,0,1);
			if(start2.getBlock().getType()==Material.AIR) {
			start2.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState2 = start2.getBlock().getState();
			blockState2.setType(start2.getBlock().getType());
			MaterialData blockData2 = blockState2.getData();
			blockData2.setData((byte) 2);
			blockState2.update(true);
			start2.getBlock().getState().setData(blockData2);
			Banner banner2 = (Banner)start2.getBlock().getState();
			banner2.setBaseColor(DyeColor.WHITE);
			banner2.update();
			}
			//start.getBlock().getState().setRawData((byte) 5);
			//Banner b = (Banner) start.getBlock();
		}
		else if(choose==2) {
			start.getBlock().setType(Material.QUARTZ_STAIRS);
			Stairs stairs = new Stairs(Material.QUARTZ);
            stairs.setFacingDirection(BlockFace.NORTH);
            start.getBlock().setData(stairs.getData());
			start.add(1, 0, 0);
			if(start.getBlock().getType()==Material.AIR) {
			start.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState = start.getBlock().getState();
			blockState.setType(start.getBlock().getType());
			MaterialData blockData = blockState.getData();
			blockData.setData((byte) 5);
			blockState.update(true);
			start.getBlock().getState().setData(blockData);
			Banner banner = (Banner)start.getBlock().getState();
			banner.setBaseColor(DyeColor.WHITE);
			banner.update();
			}
			Location start2 = l;
			start2.subtract(1,0,0);
			if(start2.getBlock().getType()==Material.AIR) {
			start2.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState2 = start2.getBlock().getState();
			blockState2.setType(start2.getBlock().getType());
			MaterialData blockData2 = blockState2.getData();
			blockData2.setData((byte) 4);
			blockState2.update(true);
			start2.getBlock().getState().setData(blockData2);
			Banner banner2 = (Banner)start2.getBlock().getState();
			banner2.setBaseColor(DyeColor.WHITE);
			banner2.update();
			}
			//start.getBlock().getState().setRawData((byte) 5);
			//Banner b = (Banner) start.getBlock();
		}
		else if(choose==3) {
			start.getBlock().setType(Material.QUARTZ_STAIRS);
			Stairs stairs = new Stairs(Material.QUARTZ);
            stairs.setFacingDirection(BlockFace.WEST);
            start.getBlock().setData(stairs.getData());
			start.add(0, 0, 1);
			if(start.getBlock().getType()==Material.AIR) {
			start.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState = start.getBlock().getState();
			blockState.setType(start.getBlock().getType());
			MaterialData blockData = blockState.getData();
			blockData.setData((byte) 3);
			blockState.update(true);
			start.getBlock().getState().setData(blockData);
			Banner banner = (Banner)start.getBlock().getState();
			banner.setBaseColor(DyeColor.WHITE);
			banner.update();
			}
			Location start2 = l;
			start2.subtract(0,0,1);
			if(start2.getBlock().getType()==Material.AIR) {
			start2.getBlock().setType(Material.WALL_BANNER);
			BlockState blockState2 = start2.getBlock().getState();
			blockState2.setType(start2.getBlock().getType());
			MaterialData blockData2 = blockState2.getData();
			blockData2.setData((byte) 2);
			blockState2.update(true);
			start2.getBlock().getState().setData(blockData2);
			Banner banner2 = (Banner)start2.getBlock().getState();
			banner2.setBaseColor(DyeColor.WHITE);
			banner2.update();
			}
			//start.getBlock().getState().setRawData((byte) 5);
			//Banner b = (Banner) start.getBlock();
		}}
		catch(Exception e) {
			
		}
		
	}
	
	public static void makeBedL(Location l, int i) {
		try {
		int choose = rander.nextInt(4);
		if(choose==0) {
			Location up1 = l;
			setFlesh(up1);
			up1.add(1, 0, 0);
			setFlesh(up1);
			up1.add(1, 0, 0);
			setFlesh(up1);
			up1.add(0, 1, 0);
			up1.getBlock().setType(Material.SNOW);
			up1.getBlock().setData((byte) 1);
			Location up2 = l;
			up2.add(0, 0, 1);
			setFlesh(up2);
			up2.add(1, 0, 0);
			setFlesh(up2);
			up2.add(1, 0, 0);
			setFlesh(up2);
			up2.add(0, 1, 0);
			up2.getBlock().setType(Material.SNOW);
			up2.getBlock().setData((byte) 2);
		}
		else if(choose==1) {
			Location up1 = l;
			setFlesh(up1);
			up1.subtract(1, 0, 0);
			setFlesh(up1);
			up1.subtract(1, 0, 0);
			setFlesh(up1);
			up1.add(0, 1, 0);
			up1.getBlock().setType(Material.SNOW);
			up1.getBlock().setData((byte) 1);
			Location up2 = l;
			up2.add(0, 0, 1);
			setFlesh(up2);
			up2.subtract(1, 0, 0);
			setFlesh(up2);
			up2.subtract(1, 0, 0);
			setFlesh(up2);
			up2.add(0, 1, 0);
			up2.getBlock().setType(Material.SNOW);
			up2.getBlock().setData((byte) 2);
		}
		else if(choose==2) {
			Location up1 = l;
			setFlesh(up1);
			up1.subtract(0, 0, 1);
			setFlesh(up1);
			up1.subtract(0, 0, 1);
			setFlesh(up1);
			up1.add(0, 1, 0);
			up1.getBlock().setType(Material.SNOW);
			up1.getBlock().setData((byte) 1);
			Location up2 = l;
			up2.add(1, 0, 0);
			setFlesh(up2);
			up2.subtract(0, 0, 1);
			setFlesh(up2);
			up2.subtract(0, 0, 1);
			setFlesh(up2);
			up2.add(0, 1, 0);
			up2.getBlock().setType(Material.SNOW);
			up2.getBlock().setData((byte) 2);
		}
		else if(choose==3) {
			Location up1 = l;
			setFlesh(up1);
			up1.add(0, 0, 1);
			setFlesh(up1);
			up1.add(0, 0, 1);
			setFlesh(up1);
			up1.add(0, 1, 0);
			up1.getBlock().setType(Material.SNOW);
			up1.getBlock().setData((byte) 1);
			Location up2 = l;
			up2.subtract(1, 0, 0);
			setFlesh(up2);
			up2.add(0, 0, 1);
			setFlesh(up2);
			up2.add(0, 0, 1);
			setFlesh(up2);
			up2.add(0, 1, 0);
			up2.getBlock().setType(Material.SNOW);
			up2.getBlock().setData((byte) 2);
		}
		//domeatyfurniture 5
		}
		catch(Exception e) {
			
		}
	}

	public static void makeTableL(Location l, int i) {
		try {
		Location n1 = l;
		n1.getBlock().setType(Material.RED_SHULKER_BOX);
		n1.add(0, 1, 0);
		if(rander.nextInt(2)==1) {
		n1.getBlock().setTypeIdAndData(171, (byte) 14, true);
		}
		else {
			n1.getBlock().setType(Material.REDSTONE_TORCH_ON);	
		}}
		catch(Exception e) {
			
		}
	}

}
