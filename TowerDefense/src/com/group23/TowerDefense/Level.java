package com.group23.TowerDefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Level 
{	
	// Tile textures array
	public static Texture[] textures;
	
	// Level constants
	private final static int NUM_TILES_WIDTH = 15;
	private final static int NUM_TILES_HEIGHT = 8;
	private final static long RESPAWN_TIME = 2000;
	
	// Tile data
	private int[] tiles;				
	private Dir[] directions;
	
	// Starting information
	private int startX, startY;
	private Dir startDir;
	
	private Array<Tower> towers;
	private Array<Enemy> enemies;
	
	// DEBUG time from last enemy spawned
	private long lastSpawnTime;
	
	/**
	 * Initializes the level class
	 * For now, the level uses a pre-defined tile array and direction array
	 */
	public Level()
	{
		enemies = new Array<Enemy>();
		towers = new Array<Tower>();
		
		lastSpawnTime = TimeUtils.millis();
		
		// initialize starting position
		startX = 0;
		startY = 1;
		startDir = Dir.E;
		
		// initialize tile array
		tiles = new int[]
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
		
		// initialize direction array
		directions = new Dir[]
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
		//for (Tower t : towers)
		//	t.update();
	}
	
	/**
	 * Draws the level, including the enemies and towers
	 * @param batch
	 */
	public void draw(SpriteBatch batch)
	{
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
		for (Tower t : towers)
			if (t.cmpTile(x, y))
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
}
