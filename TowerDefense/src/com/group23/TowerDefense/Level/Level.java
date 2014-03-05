package com.group23.TowerDefense.Level;

import java.util.Arrays;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.group23.TowerDefense.Dir;
import com.group23.TowerDefense.Enemy.Enemy;
import com.group23.TowerDefense.Spawn.LevelWave;
import com.group23.TowerDefense.Tower.Tower;
import com.group23.TowerDefense.Tower.Tower1;

public abstract class Level 
{	
	// Tile textures array
	public static Texture[] textures;
	
	// Level constants
	private final static int NUM_TILES_WIDTH  = 15;
	private final static int NUM_TILES_HEIGHT = 8;
	private final static int MAX_TOWERS       = 10;
	
	// Tile data
	private int[] tiles;				
	private Dir[] directions;
	
	private Array<Tower> towers;
	private Array<Enemy> enemies;
	private LevelWave wave;
	
	/**
	 * Initializes the level class
	 * For now, the level uses a pre-defined tile array and direction array
	 */
	public Level()
	{
		enemies = new Array<Enemy>();
		towers  = new Array<Tower>();
		wave    = new LevelWave();
		
		// initialize tile array
		tiles = loadTiles();
		
		// initialize direction array
		directions = new Dir[tiles.length];
		
		// Create a direction map based off of the tile map
		createDirMap();
		
		// load the waves
		loadWaves(wave);
		
		// DEBUG start the first wave
		wave.next(enemies);
	}
	
	// Creates a direction map for enemies to follow, based off of the tile map
	private void createDirMap()
	{
		Arrays.fill(directions, Dir.I);			// Initialize all cells to invalid
		directions[15] = getStartDir();			// Set the starting cell
		
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
		wave.update(this);
		
		// DEBUG when one wave is finished, start the next one
		if (!wave.isPlaying() && !wave.isFinished())
			wave.next(enemies);
		
		// Update enemies
		for (Enemy e : enemies)
			e.update(dt);
		
		// Update towers
		for (Tower t : towers)
			t.update();
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
			towers.add(new Tower1(this, x, y));
	}
	
	/**
	 * Removes a tower on a tile-coordinate position
	 * @param x Tile x-coordinate to remove tower
	 * @param y Tile y-coordinate to remove tower
	 * @return true if tower removed
	 */
	public boolean removeTower(int x, int y)
	{
		final int index = convertTileToIndex(x, y);
		boolean removed = false;
		
		Iterator<Tower> iter = towers.iterator();
		while (iter.hasNext())
		{
			Tower t = iter.next();
			removed = t.getTile() == index;
			
			if (removed)
			{
				iter.remove();
				break;
			}
		}
		
		return removed;
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
		
		return towers.size < MAX_TOWERS;
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
	
	public Array<Enemy> getEnemies()
	{
		return enemies;
	}
	
	public void removeEnemy(Enemy enemy)
	{
		enemies.removeValue(enemy, false);
	}
	
	/**
	 * Converts a tile coordinate to index coordinate
	 * @param x Tile x-coordinate
	 * @param y Tile y-coordinate
	 * @return index position of the tile (x,y) coordinate
	 */
	private int convertTileToIndex(int x, int y)
	{
		return y * getWidth() + x;
	}
	
	/**
	 * Generates the map tiles.
	 * @return Tile array
	 */
	protected abstract int[] loadTiles();
	
	/**
	 * Generates the enemy spawn waves
	 * @param waves The LevelWave to modify
	 */
	protected abstract void loadWaves(LevelWave waves);
	
	public abstract int getStartX();
	public abstract int getStartY();
	public abstract Dir getStartDir();
}
