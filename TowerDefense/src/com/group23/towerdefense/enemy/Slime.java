package com.group23.towerdefense.enemy;

import com.group23.towerdefense.Level;

public class Slime extends Enemy
{
	public Slime(Level map) 
	{
		super(map);
		setTexture("enemy03.png");
	}

	protected void setBaseStats() 
	{
		hp = maxHP = 150;
		armor = 0;
		moveSpeed = 90;
		texWidth = 64;
		texHeight = 64;
		goldValue = 10;
		livesValue = 2;
	}
	
	public void rewardGold()
	{
		if(goldValue == 0)
			return;
		path.giveGold(goldValue);
		path.getEnemies().add(new SmallSlime(path, curTile));
		switch(direction)
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
		path.getEnemies().add(new SmallSlime(path, curTile));
		goldValue = 0; // So its not accidentally given twice.
	}
}
