package com.group23.TowerDefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Tower
{
	public static Texture texture       = null;
	private static final int texWidth  = 64;
	private static final int texHeight = 64;
	
	// Tile index position in the map array
	private int tile;
	
	// World coordinate of tower
	private float x, y;
	
	// Range the tower has to acquire an an enemy
	private float range;
	
	private Level map;
	private Enemy target;
	
	public Tower(Level map, int x, int y) 
	{
		this.tile = y * map.getWidth() + x;
		
		this.map    = map;
		this.target = null;
		this.range  = 250.0f;
		
		// Converts from tile coordinates to pixel coordinates and centers 
		// enemy in tile and offsets for image height and width
		this.x = x * 128 + 64;
		this.y = y * 128 + 64;
	}
	
	public void update(float dt)
	{
		// TODO have the tower attack an enemy
	}
	
	public void draw(SpriteBatch batch)
	{
		ShapeRenderer shapeRenderer = TowerDefense.shapeRenderer;
		batch.draw(texture, x - texWidth / 2.0f, y - texHeight / 2.0f);
		
		// draw the radius of the range
		shapeRenderer.setColor(1.0f, 0.0f, 0.0f, 0.5f);
		shapeRenderer.circle(x, y, range);
		
		// draw the line to the target (if applicable)
		if (target != null)
		{
			shapeRenderer.setColor(0.0f, 1.0f, 1.0f, 0.5f);
			shapeRenderer.line(x, y, target.getX(), target.getY());
		}
	}

	public int getTile()
	{
		return tile;
	}
}