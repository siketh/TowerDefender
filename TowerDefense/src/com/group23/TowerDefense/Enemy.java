package com.group23.TowerDefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
	public static Texture texture;					// Stores enemy texture file
	private int texHeight, texWidth;				// Stores height and width of texture file
	private int hp;									// Stores current hp of enemy
	private int moveSpeed;							// Stores movement speed of enemy
	private Dir direction; 		//Stores direction of enemy
	private float x, y;								// Store pixel coordinates of enemy
	private Level path;								// Points to level 
	private int curTile;							// Stores current tile index of enemy
	private float distTraveled;						// Stores distance traveled since last new tile

	// Constructor for enemy
	public Enemy(Level map) 
	{
		hp = 100;
		//TODO: Change to get it 
		texHeight = 64;
		texWidth = 64;
		moveSpeed = 128;
		distTraveled = 0;
		path = map;
		direction = path.getStartingDirection();	// Pulls starting direction from map
		
		// Converts from tile coordinates to pixel coordinates and centers 
		// enemy in tile and offsets for image height and width
		x = path.getStartX() * 128 + 64 - (texWidth / 2);
		y = path.getStartY() * 128 + 64 - (texHeight / 2);
		
		// Calculates tiles index based on width of tile map
		curTile = path.getStartY() * path.getWidth() + path.getStartX();
	}

	// Returns true if reached the end
	public boolean update(float dt) 
	{
		// Updates units position based on change in time and unit's movement speed
		// 0.71 is for diagonal cases to match movement speed to horizontal and vertical cases
		switch (direction) {
		case N:
			y += moveSpeed * dt;
			break;
		case NE:
			x += moveSpeed * 0.71 * dt;
			y -= moveSpeed * 0.71 * dt;
			break;
		case E:
			x += moveSpeed * dt;
			break;
		case SE:
			x += moveSpeed * 0.71 * dt;
			y += moveSpeed * 0.71 * dt;
			break;
		case S:
			y += moveSpeed * dt;
			break;
		case SW:
			x -= moveSpeed * 0.71 * dt;
			y += moveSpeed * 0.71 * dt;
			break;
		case W:
			x -= moveSpeed * dt;
			break;
		case NW:
			x -= moveSpeed * 0.71 * dt;
			y -= moveSpeed * 0.71 * dt;
			break;
		case End:
			return true;
		default:
			break;
		}
		
		// Even cases are diagonal cases
		if (direction == Dir.NE || direction == Dir.SE || direction == Dir.SW || direction == Dir.NW)	
			distTraveled += moveSpeed * 0.71 * dt;
		// Odd cases are horizontal and vertical cases
		else
			distTraveled += moveSpeed * dt;

		// If the enemy has moved across one tile, center it in the next tile 
		// and update it's current tile
		// Add or subtract path.getWidth() for moving up or down a row
		// Add or subtract 1 for moving right or left
		if (distTraveled >= 128) 
		{
			switch (direction) {
			case N:
				curTile -= path.getWidth();
				break;
			case NE:
				curTile = curTile - path.getWidth() + 1;
				break;
			case E:
				curTile += 1;
				break;
			case SE:
				curTile = curTile + 1 + path.getWidth();
				break;
			case S:
				curTile += path.getWidth();
				break;
			case SW:
				curTile = curTile + path.getWidth() - 1;
				break;
			case W:
				curTile -= 1;
				break;
			case NW:
				curTile = curTile -1 + path.getWidth();
				break;
			default:
				break;
			}
			
			// Centers enemy at a new tile
			x = (curTile % path.getWidth()) * 128 + 64 - (texWidth / 2);
			y = (curTile / path.getWidth()) * 128 + 64 - (texHeight / 2);
			distTraveled = 0;
		}
		
		// Gets enemy a new direction
		direction = path.getDirection(curTile);
		
		return false;
	}

	public Dir getDir() 
	{
		return direction;
	}

	// Returns true if still alive
	// Returns false if dead
	public boolean dealDamage(int damage) 
	{
		hp -= damage;
		if (damage <= 0)
			return false;
		return true;

	}
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture, x, y);
	}
}
