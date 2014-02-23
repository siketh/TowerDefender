package com.group23.TowerDefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tower
{
	public static Texture texture;					// Stores tower texture file
	private int texHeight, texWidth;				// Stores height and width of texture file
	private int tile;								// Stores current tile index of tower
	private int range;
	private int damage;
	private float x, y;								// Store pixel coordinates of enemy
	private Level map;
	
	public Tower(Level map, int x, int y) 
	{
		//TODO: Change to get it 
		texHeight = 64;
		texWidth = 64;
		tile = x + y * map.getWidth();
		
		this.map = map;
		// Converts from tile coordinates to pixel coordinates and centers 
		// enemy in tile and offsets for image height and width
		this.x = (tile % map.getWidth()) * 128 + 64 ;
		this.y = (tile / map.getWidth()) * 128 + 64 ;
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture, (x - texWidth / 2), (y- texHeight / 2));	
	}

	public int getTile()
	{
		return tile;
	}
	
	public boolean cmpTile(int tile)
	{
		return tile == this.tile;
	}
	
	public boolean cmpTile(int x, int y)
	{
		int temp = x + y * map.getWidth();
		return temp == tile;
	}

	public int getRange()
	{
		return range;
	}

	public int getDamage()
	{
		return damage;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public Level getMap()
	{
		return map;
	}
	
	
}

