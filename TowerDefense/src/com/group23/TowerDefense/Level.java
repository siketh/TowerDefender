package com.group23.TowerDefense;

public class Level 
{	
	private int[] tiles;				// Stores index of image of tile				
	private Dir[] directions;			// Stores which way enemies should move
	private int startX, startY;			// Stores starting tile of enemies
	private Dir startDir;				// Stores initial direction of enemies
	private int height, width;			// Stores height and width of grid
	
	// Initializes instance of a level
	static Level debug()
	{
		// Creates instance of level
		Level l = new Level();
		
		l.width = 15;
		l.height = 8;
		
		//0 is red
		//1 is yellow
		//2 is teal
		l.tiles = new int[]
		{
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,		//Bottom Right
			1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0,		
			0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0			//Top Right
		};
		
		l.startX = 0;
		l.startY = 1;
		l.startDir = Dir.E;
		
		//TODO: Generate direction map automatically
		l.directions = new Dir[]
		{
				Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I,
				Dir.E, Dir.E, Dir.SE, Dir.I, Dir.I, Dir.I, Dir.I, Dir.SE, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I,
				Dir.I, Dir.I, Dir.I, Dir.SE, Dir.I, Dir.I, Dir.NE, Dir.I, Dir.S, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I,
				Dir.I, Dir.I, Dir.I, Dir.I, Dir.E, Dir.NE, Dir.I, Dir.I, Dir.SE, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I,
				Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.SE, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I,
				Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.SE, Dir.I, Dir.I, Dir.I, Dir.I,
				Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.S, Dir.I, Dir.I, Dir.I,
				Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.I, Dir.End, Dir.End, Dir.End, Dir.I, Dir.I	
		};
		return l;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	// Get tile in tile index 
	public int getTile(int tile)
	{
		return tiles[tile];
	}
	
	// Get tile in tile x and y coordinates
	public int getTile(int x, int y)
	{
		return getTile(y * width + x);
	}
	
	// Get direction in tile index 
	public Dir getDirection(int tile)
	{
		return directions[tile];
	}
	
	// Get direction in tile x and y coordinates
	public Dir getDirection(int x, int y)
	{
		return getDirection(y * width + x);
	}
	
	public int getStartX()
	{
		return startX;
	}
	
	public int getStartY()
	{
		return startY;
	}
	
	public Dir getStartingDirection()
	{
		return startDir;
	}
}
