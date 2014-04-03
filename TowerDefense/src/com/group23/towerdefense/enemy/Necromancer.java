package com.group23.towerdefense.enemy;

import com.group23.towerdefense.Dir;
import com.group23.towerdefense.Level;

public class Necromancer extends Enemy 
{
	private float timeTillSummon;	//Time between summonings
	private float timeSummoning;	//Time during the current summoning
	private int summon;				//Keeps track of whats been summoned
	

	public Necromancer(Level map) 
	{
		super(map);
		setTexture("enemy03.png");
	}

	protected void setBaseStats() 
	{
		hp = maxHP = 200;
		armor = 0;
		moveSpeed = 64;
		texWidth = 64;
		texHeight = 64;
		goldValue = 25;
		livesValue = 2;
		timeTillSummon = 3;
	}
	
	private void summon()
	{
		switch(direction)
		{
			case N:
				path.getEnemies().add(new SmallSlime(path, curTile - path.getWidth()));
			case NE:
				path.getEnemies().add(new SmallSlime(path, curTile - path.getWidth() + 1));
				break;
			case E:
				path.getEnemies().add(new SmallSlime(path, curTile + 1));
				break;
			case SE:
				path.getEnemies().add(new SmallSlime(path, curTile + path.getWidth() + 1));
				break;
			case S:
				path.getEnemies().add(new SmallSlime(path, curTile + path.getWidth()));
				break;
			case SW:
				path.getEnemies().add(new SmallSlime(path, curTile + path.getWidth() - 1));
				break;
			case W:
				path.getEnemies().add(new SmallSlime(path, curTile - 1));
				break;
			case NW:
				path.getEnemies().add(new SmallSlime(path, curTile - path.getWidth() - 1));
				break;
			default:
				break;
		}
	}
	
	// Returns true if reached the end
	public boolean act(float dt)
	{
		//Takes care of the summoning
		if(timeTillSummon == 0)
		{
			timeSummoning += dt;
			if(timeSummoning >= 1 && summon == 0)
			{
				summon();
				summon = 1;
			}
			else if(timeSummoning >= 2)
			{
				summon();
				timeSummoning = 0;
				timeTillSummon = 3;
				
			}
			return false;
		}
		else if(timeTillSummon <= dt)
		{
			timeTillSummon = 0;
			summon = 0;
			return false;
		}
		else
		{
			timeTillSummon -= dt;
		}
		System.out.println(timeTillSummon);
		
		// Updates units position based on change in time and unit's movement
		// speed
		// 0.71 is for diagonal cases to match movement speed to horizontal and
		// vertical cases
		switch (direction)
		{
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
		if (direction == Dir.NE || direction == Dir.SE || direction == Dir.SW
				|| direction == Dir.NW)
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
