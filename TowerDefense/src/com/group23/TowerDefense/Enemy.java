package com.group23.TowerDefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
	private Texture tex;							// Stores enemy texture file
	private int texHeight, texWidth;				// Stores height and width of texture file
	private int hp;									// Stores current hp of enemy
	private int moveSpeed;							// Stores movement speed of enemy
	private int direction; // TODO: convert to Direction enum		//Stores direction of enemy
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
		
		// Loads enemy texture
		tex = new Texture(Gdx.files.internal("enemy00.png"));
	}

	// Returns true if reached the end
	public boolean update() 
	{
		// 1 is N
		// 2 is NE
		// 3 is E
		// 4 is SE
		// 5 is S
		// 6 is SW
		// 7 is W
		// 8 is NW
		// TODO: convert to Direction enum
		// Updates units position based on change in time and unit's movement speed
		// 0.71 is for diagonal cases to match movement speed to horizontal and vertical cases
		switch (direction) {
		case 1:
			y += moveSpeed * Gdx.graphics.getDeltaTime();
			break;
		case 2:
			x += moveSpeed * 0.71 * Gdx.graphics.getDeltaTime();
			y -= moveSpeed * 0.71 * Gdx.graphics.getDeltaTime();
			break;
		case 3:
			x += moveSpeed * Gdx.graphics.getDeltaTime();
			break;
		case 4:
			x += moveSpeed * 0.71 * Gdx.graphics.getDeltaTime();
			y += moveSpeed * 0.71 * Gdx.graphics.getDeltaTime();
			break;
		case 5:
			y += moveSpeed * Gdx.graphics.getDeltaTime();
			break;
		case 6:
			x -= moveSpeed * 0.71 * Gdx.graphics.getDeltaTime();
			y += moveSpeed * 0.71 * Gdx.graphics.getDeltaTime();
			break;
		case 7:
			x -= moveSpeed * Gdx.graphics.getDeltaTime();
			break;
		case 8:
			x -= moveSpeed * 0.71 * Gdx.graphics.getDeltaTime();
			y -= moveSpeed * 0.71 * Gdx.graphics.getDeltaTime();
			break;
		case 9:
			return true;
		}
		
		// Even cases are diagonal cases
		if (direction % 2 == 0)	
			distTraveled += moveSpeed * 0.71 * Gdx.graphics.getDeltaTime();
		// Odd cases are horizontal and vertical cases
		else
			distTraveled += moveSpeed * Gdx.graphics.getDeltaTime();

		// If the enemy has moved across one tile, center it in the next tile 
		// and update it's current tile
		// Add or subtract path.getWidth() for moving up or down a row
		// Add or subtract 1 for moving right or left
		if (distTraveled >= 128) 
		{
			switch (direction) {
			case 1:
				curTile -= path.getWidth();
				break;
			case 2:
				curTile = curTile - path.getWidth() + 1;
				break;
			case 3:
				curTile += 1;
				break;
			case 4:
				curTile = curTile + 1 + path.getWidth();
				break;
			case 5:
				curTile += path.getWidth();
				break;
			case 6:
				curTile = curTile + path.getWidth() - 1;
				break;
			case 7:
				curTile -= 1;
				break;
			case 8:
				curTile = curTile -1 + path.getWidth();
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

	public int getDir() 
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
		batch.draw(tex, x, y);
	}
}
