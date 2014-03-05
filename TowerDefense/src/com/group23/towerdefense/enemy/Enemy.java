package com.group23.towerdefense.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.group23.towerdefense.Dir;
import com.group23.towerdefense.level.Level;

public abstract class Enemy 
{
	public static BitmapFont font       = null;

	protected int texWidth, texHeight;
	protected int hp, maxHP;									// Stores current hp of enemy
	protected int moveSpeed;							// Stores movement speed of enemy
	protected Dir direction; 		//Stores direction of enemy
	protected Vector2 pos;								// Store pixel coordinates of enemy
	protected Level path;								// Points to level 
	protected int curTile;							// Stores current tile index of enemy
	protected float distTraveled;						// Stores distance traveled since last new tile
	
	private Color color;

	// Constructor for enemy
	public Enemy(Level map) 
	{
		setBaseStats();
		distTraveled = 0;
		path = map;
		direction = path.getStartDir();	// Pulls starting direction from map
		
		// Converts from tile coordinates to pixel coordinates and centers 
		// enemy in tile and offsets for image height and width
		pos = new Vector2();
		pos.x = path.getStartX() * 128 + 64;
		pos.y = path.getStartY() * 128 + 64;
		
		color = new Color(Color.WHITE);
		
		// Calculates tiles index based on width of tile map
		curTile = path.getStartY() * path.getWidth() + path.getStartX();
	}
	
	//Put base stats of the monster here
	abstract protected void setBaseStats();

	// Returns true if reached the end
	public boolean update(float dt) 
	{
		// Updates units position based on change in time and unit's movement speed
		// 0.71 is for diagonal cases to match movement speed to horizontal and vertical cases
		switch (direction) {
		case N:
			pos.y -= moveSpeed * dt;
			break;
		case NE:
			pos.x += moveSpeed * 0.71 * dt;
			pos.y -= moveSpeed * 0.71 * dt;
			break;
		case E:
			pos.x += moveSpeed * dt;
			break;
		case SE:
			pos.x += moveSpeed * 0.71 * dt;
			pos.y += moveSpeed * 0.71 * dt;
			break;
		case S:
			pos.y += moveSpeed * dt;
			break;
		case SW:
			pos.x -= moveSpeed * 0.71 * dt;
			pos.y += moveSpeed * 0.71 * dt;
			break;
		case W:
			pos.x -= moveSpeed * dt;
			break;
		case NW:
			pos.x -= moveSpeed * 0.71 * dt;
			pos.y -= moveSpeed * 0.71 * dt;
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
				curTile = curTile + path.getWidth() + 1;
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
				curTile = curTile - path.getWidth() - 1;
				break;
			default:
				break;
			}
			
			// Centers enemy at a new tile
			pos.x = (curTile % path.getWidth()) * 128 + 64;
			pos.y = (curTile / path.getWidth()) * 128 + 64;
			distTraveled = 0;
		}
		
		// Gets enemy a new direction
		direction = path.getDirection(curTile);
		
		return false;
	}
	
	public void draw(SpriteBatch batch)
	{
		// draw health
		float percent = (float) Math.floor((float) hp / maxHP * 100.0f);
		font.setScale(2.0f);
		font.setColor(transitionColor(Color.GREEN, Color.RED, (float) hp / maxHP, color));
		font.draw(batch, String.format("%d%%", (int) percent), pos.x - texWidth / 2, pos.y - texHeight / 2);
	}

	public Dir getDir() 
	{
		return direction;
	}

	/**
	 * Damages the enemy
	 * @param damage
	 * @return The new enemy's health
	 */
	public int dealDamage(int damage) 
	{
		return hp -= damage;
	}
	
	public Vector2 getPosition()
	{
		return pos;
	}
	
	/**
	 * Transitions a color from one to another
	 * @param from Color to transition from
	 * @param to Color to transition to
	 * @param mult Modifier from color from to color to
	 * @param color Color to modify
	 * @return color
	 */
	private Color transitionColor(Color from, Color to, float mult, Color color)
	{
		float inv = 1.0f - mult;
		
		color.r = (from.r * mult) + (to.r * inv);
		color.g = (from.g * mult) + (to.g * inv);
		color.b = (from.b * mult) + (to.b * inv);
		
		return color;
	}
}
