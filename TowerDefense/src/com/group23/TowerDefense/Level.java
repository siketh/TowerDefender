package com.group23.TowerDefense;

public class Level 
{	
	private int[] tiles;				// Stores index of image of tile				
	private int[] directions;			// Stores which way enemies should move
	private int startX, startY;			// Stores starting tile of enemies
	private int startDir;				// Stores initial direction of enemies
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
		l.startDir = 3;
			
		//0 is invalid
		//1 is N
		//2 is NE
		//3 is E
		//4 is SE
		//5 is S
		//6 is SW
		//7 is W
		//8 is NW
		//9 is done
		l.directions = new int[]
		{
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				3, 3, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 4, 0, 0, 2, 0, 5, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 3, 2, 0, 0, 4, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 0, 0	
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
	public int getDirection(int tile)
	{
		return directions[tile];
	}
	
	// Get direction in tile x and y coordinates
	public int getDirection(int x, int y)
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
	
	public int getStartingDirection()
	{
		return startDir;
	}
}
