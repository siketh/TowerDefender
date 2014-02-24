package com.group23.TowerDefense;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Level 
{	
	private static Texture[] textures;	// Stores the tile textures
	private int[] tiles;				// Stores index of image of tile				
	private Dir[] directions;			// Stores which way enemies should move
	private int startX, startY;			// Stores starting tile of enemies
	private Dir startDir;				// Stores initial direction of enemies
	private int height, width;			// Stores height and width of grid
	private Array<Enemy> enemies;		//Stores the array of enemies
	
	public Level(Array<Enemy> enemyArray)
	{
		enemies = enemyArray;
		width = 15;
		height = 8;
		
		tiles = new int[]
		{
			0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0,		//Bottom Right
			1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 2,
			1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 2,
			1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 2,		
			1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0,
			1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0,
			1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
			0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0			//Top Right
		};
		
		startX = 0;
		startY = 1;
		select = -1;
		
		// Create a direction map based off of the tile map
		createDirMap();
	}
	
	// Creates a direction map for enemies to follow, based off of the tile map
	private void createDirMap()
	{
		directions = new Dir[120];
		Arrays.fill(directions, Dir.I);			// Initialize all cells to invalid
		startDir = Dir.S;						// Set the starting direction
		directions[15] = startDir;				// Set the starting cell
		
		boolean leftEdge; 				// True if current index is on the left edge of the map
		boolean rightEdge;				// True if current index is on the right edge of the map
		boolean topEdge;				// True if current index is on the top edge of the map
		boolean bottomEdge;				// True if current index is on the bottom edge of the map
		
		// DFS loop, start at whichever index the starting cell is
		// Sets the direction FROM the current cell TO the next cell
		// Runs as long as we aren't on a base cell
		int i = 15;	
		while(tiles[i] != 2)
		{
			// Initialize edge cases to false
			leftEdge = false; 
			rightEdge = false;
			topEdge = false;
			bottomEdge = false;
			
			// Determine if we are CURRENTLY on an edge
			if(i % getWidth() == 0) 
				leftEdge = true;
			if(i % getWidth() == getWidth() - 1)
				rightEdge = true;
			if(i / getWidth() == 0) 
				topEdge = true;
			if(i / getWidth() == getHeight() - 1) 
				bottomEdge = true;
			
			// If we are not on the top edge, the northern tile is not 0, and we have not 
			// already set the direction of the northern tile, set current tile's direction 
			// to north and make it our new pathfinding index.
			// Repeat similar process for all directions.
			if(topEdge == false && tiles[i-15] != 0 && directions[i-15] == Dir.I)
			{
				directions[i] = Dir.N;
				i = i-15;
			}
			// East is 1 tile forward
			else if(rightEdge == false && tiles[i+1] != 0 && directions[i+1] == Dir.I)
			{	
				directions[i] = Dir.E;
				i = i+1;
			}
			// South is 15 tiles forward
			else if(bottomEdge == false && tiles[i+15] != 0 && directions[i+15] == Dir.I)
			{
				directions[i] = Dir.S;
				i = i+15;
			}
			// West is 1 tile backwards
			else if(leftEdge == false && tiles[i-1] != 0 && directions[i-1] == Dir.I)
			{
				directions[i] = Dir.W;
				i = i-1;
			}
			// NE is 14 tiles backwards
			else if(topEdge == false && rightEdge == false && tiles[i-14] != 0 && directions[i-14] == Dir.I)
			{
				directions[i] = Dir.NE;
				i = i-14;
			}
			// SE is 16 tiles forwards
			else if(bottomEdge == false && rightEdge == false && tiles[i+16] != 0 && directions[i+16] == Dir.I)
			{
				directions[i] = Dir.SE;
				i = i+16;
			}
			// SW is 14 tiles forwards
			else if(bottomEdge == false && leftEdge == false && tiles[i+14] != 0 && directions[i+14] == Dir.I)
			{
				directions[i] = Dir.SW;
				i = i+14;
			}
			// NW is 16 tiles backwards
			else if(topEdge == false && leftEdge == false && tiles[i-16] != 0 && directions[i-16] == Dir.I)
			{
				directions[i] = Dir.NW;
				i = i-16;
			}
		}	
		// Loop exited since we are on a base tile, so make this the end tile
		directions[i] = Dir.End;
	}
	
	static void initialize()
	{
		textures = new Texture[4];
		textures[0] = new Texture(Gdx.files.internal("tile00.png"));
		textures[1] = new Texture(Gdx.files.internal("tile01.png"));
		textures[2] = new Texture(Gdx.files.internal("tile02.png"));
		textures[3] = new Texture(Gdx.files.internal("tile03.png"));
	}
	
	static void dispose()
	{
		for (Texture t : textures)
			t.dispose();
		textures = null;
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
	
	public int select;
	
	public void draw(SpriteBatch batch)
	{
		// Grid is 8x15
		// Draws map ***BASED ON SCREEN WIDTH***
		for (int y = 0; y < TowerDefense.SCREEN_HEIGHT / 128; y++)		
			for (int x = 0; x < TowerDefense.SCREEN_WIDTH / 128; x++)
			{
				final int w = TowerDefense.SCREEN_WIDTH / 128;
				if( select ==  y * w + x)
					batch.draw(textures[3], x * 128, y * 128);
				else
					batch.draw(textures[getTile(x, y)], x * 128, y * 128);
			}
	}
}
