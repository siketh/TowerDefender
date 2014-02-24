package com.group23.TowerDefense;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Level 
{	
	// Tile textures array
	public static Texture[] textures;
	
	// Level constants
	private final static int NUM_TILES_WIDTH  = 15;
	private final static int NUM_TILES_HEIGHT = 8;
	private final static long RESPAWN_TIME    = 2000; // time in MS
	
	// Tile data
	private int[] tiles;				
	private Dir[] directions;
	
	// Starting information
	private int startX, startY;
	private Dir startDir;
	
	private Array<Tower> towers;
	private Array<Enemy> enemies;
	private Button menu;
	
	// DEBUG time from last enemy spawned
	private long lastSpawnTime;
	
	/**
	 * Initializes the level class
	 * For now, the level uses a pre-defined tile array and direction array
	 */
	public Level()
	{
		enemies = new Array<Enemy>();
		towers  = new Array<Tower>();
		menu = new Button();
		
		lastSpawnTime = TimeUtils.millis();
		
		// initialize starting position
		startX = 0;
		startY = 1;
		startDir = Dir.E;
		
		// initialize tile array
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
		
		// initialize direction array
		directions = new Dir[tiles.length];
				
		startX = 0;
		startY = 1;
		
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
	
	public void update(float dt)
	{
		// Add enemy if passed RESPAWN_TIME
		if (TimeUtils.millis() - lastSpawnTime > RESPAWN_TIME)
		{
			enemies.add(new Enemy(this));
			lastSpawnTime = TimeUtils.millis();
		}
		
		// Update enemies
		for (Enemy e : enemies)
			e.update(dt);
		
		// Update towers
		for (Tower t : towers)
			t.update(dt);
	}
	
	/**
	 * Draws the level, including the enemies and towers
	 * @param batch
	 */
	public void draw(SpriteBatch batch)
	{
		menu.draw(batch);
		// Draws level tiles
		for (int y = 0; y < NUM_TILES_HEIGHT; y++)		
			for (int x = 0; x < NUM_TILES_WIDTH; x++)
				batch.draw(textures[getTile(x, y)], x * 128, y * 128);
		
		// Draw enemies
		for (Enemy e : enemies)
			e.draw(batch);
		
		// Draw towers
		for (Tower t : towers)
			t.draw(batch);		
	}
	
	/**
	 * Places a tower on a tile-coordinate position if:
	 * <li>The tile is empty</li>
	 * <li>No other tower is already placed</li>
	 * <br/>
	 * WARNING: No check is done to ensure boundary
	 * @param x Tile x-coordinate to place the new tower
	 * @param y Tile y-coordinate to place the new tower
	 */
	public void placeTower(int x, int y)
	{
		if (canPlaceTower(x, y))
			towers.add(new Tower(this, x, y));
	}
	
	/**
	 * Checks if a tower can be placed on a tile coordinate position
	 * 
	 * @param x Tile x-coordinate to check
	 * @param y Tile y-coordinate to check
	 * @return True if a tower can be positioned
	 */
	private boolean canPlaceTower(int x, int y)
	{
		// Check if tile is an empty tile
		if (getTile(x,y) != 0)
			return false;
		
		// Check that no towers conflict with position
		final int pos = y * getWidth() + x;
		for (Tower t : towers)
			if (t.getTile() == pos)
				return false;
		
		return true;
	}
	
	public int getWidth()
	{
		return NUM_TILES_WIDTH;
	}
	
	public int getHeight()
	{
		return NUM_TILES_HEIGHT;
	}
	
	// Get tile in tile index 
	public int getTile(int tile)
	{
		return tiles[tile];
	}
	
	// Get tile in tile x and y coordinates
	public int getTile(int x, int y)
	{
		return getTile(y * getWidth() + x);
	}
	
	// Get direction in tile index 
	public Dir getDirection(int tile)
	{
		return directions[tile];
	}
	
	// Get direction in tile x and y coordinates
	public Dir getDirection(int x, int y)
	{
		return getDirection(y * getWidth() + x);
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
	
	public Array<Enemy> getEnemies()
	{
		return enemies;
	}
}
