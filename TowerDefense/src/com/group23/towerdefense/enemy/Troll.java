package com.group23.towerdefense.enemy;

import com.group23.towerdefense.Dir;
import com.group23.towerdefense.Level;

public class Troll extends Enemy
{
	private float cooldown;
	private float speedBoostTime;
	
	public Troll(Level map) 
	{
		super(map, 1.0);
		setTexture("enemy01.png");
	}
	
	public Troll(Level map, double scale) 
	{
		super(map, scale);
		setTexture("enemy01.png");
	}

	protected void setBaseStats() 
	{
		hp = maxHP = 80;
		armor = 0;
		moveSpeed = 180;
		texWidth = 64;
		texHeight = 64;
		goldValue = 20;
		livesValue = 1;
		healthRegen = 4;
		cooldown = 2;
		speedBoostTime = 0;
	}
	
	public int dealDamage(int damage)
	{
		if(cooldown <= 0)
		{
			cooldown = 2;
			speedBoostTime = 0.5f;
		}
		return hp -= (damage - armor);
	}
	
	// Returns true if reached the end
	public boolean act(float dt)
	{
		calcMoveSpeed(dt);
		double speedModifier = 1;
		//Sets up movespeedBoost
		if(cooldown > 0)
			cooldown -= dt;
		if(speedBoostTime > 0)
		{
			speedBoostTime -= dt;
			speedModifier = 1.5;
		}
		
		
		timeToRegen -= dt;
		//Handles Health Regeneration
		if(timeToRegen <= 0)
		{
			hp+= healthRegen * scaling * healReduction;
			if(hp > maxHP)
				hp = maxHP;
			timeToRegen = 1;
		}
		// Updates units position based on change in time and unit's movement
		// speed
		// 0.71 is for diagonal cases to match movement speed to horizontal and
		// vertical cases
		switch (direction)
		{
		case N:
			pos.y -= moveSpeed * dt * speedModifier;
			break;
		case NE:
			pos.x += moveSpeed * 0.71 * dt * speedModifier;
			pos.y -= moveSpeed * 0.71 * dt * speedModifier;
			break;
		case E:
			pos.x += moveSpeed * dt * speedModifier;
			break;
		case SE:
			pos.x += moveSpeed * 0.71 * dt * speedModifier;
			pos.y += moveSpeed * 0.71 * dt * speedModifier;
			break;
		case S:
			pos.y += moveSpeed * dt * speedModifier;
			break;
		case SW:
			pos.x -= moveSpeed * 0.71 * dt * speedModifier;
			pos.y += moveSpeed * 0.71 * dt * speedModifier;
			break;
		case W:
			pos.x -= moveSpeed * dt * speedModifier;
			break;
		case NW:
			pos.x -= moveSpeed * 0.71 * dt * speedModifier;
			pos.y -= moveSpeed * 0.71 * dt * speedModifier;
			break;
		case End:
			return true;
		default:
			break;
		}

		// Even cases are diagonal cases
		if (direction == Dir.NE || direction == Dir.SE || direction == Dir.SW
				|| direction == Dir.NW)
			distTraveled += moveSpeed * 0.71 * dt * speedModifier;
		// Odd cases are horizontal and vertical cases
		else
			distTraveled += moveSpeed * dt * speedModifier;

		// If the enemy has moved across one tile, center it in the next tile
		// and update it's current tile
		// Add or subtract path.getWidth() for moving up or down a row
		// Add or subtract 1 for moving right or left
		if (distTraveled >= 128)
		{
			switch (direction)
			{
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

		if (path.getEnd() == curTile)
			path.enemyReachedEnd(this);

		updateTexturePosition(pos);

		return false;
	}
}
