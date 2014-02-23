package com.group23.TowerDefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Level 
{	
	private static Texture[] textures;						// Stores the tile textures
	
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
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,		//Bottom Right
			1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0,		
			0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0			//Top Right
		};
		startX = 0;
		startY = 1;
		startDir = Dir.E;
		select = -1;
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
		// Draws map ***BASED ONcd Documents/ SCREEN WIDTH***
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
