package com.group23.towerdefense.enemy;

import com.group23.towerdefense.Level;

public class SmallSlime extends Enemy
{
	public SmallSlime(Level map, int tile) 
	{
		super(map, 1.0);
		setTexture("enemy04.png");
		
		curTile = tile;
		pos.x = (curTile % path.getWidth()) * 128 + 64;
		pos.y = (curTile / path.getWidth()) * 128 + 64;
	}
	
	public SmallSlime(Level map, int tile, double scale) 
	{
		super(map, scale);
		setTexture("enemy04.png");
		
		curTile = tile;
		pos.x = (curTile % path.getWidth()) * 128 + 64;
		pos.y = (curTile / path.getWidth()) * 128 + 64;
	}

	protected void setBaseStats() 
	{
		hp = maxHP = 75;
		armor = 0;
		moveSpeed = 120;
		texWidth = 64;
		texHeight = 64;
		goldValue = 10;
		livesValue = 1;
	}
}
