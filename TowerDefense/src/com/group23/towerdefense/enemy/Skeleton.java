package com.group23.towerdefense.enemy;

import com.group23.towerdefense.Level;

public class Skeleton extends Enemy
{
	public Skeleton(Level map, int tile) 
	{
		super(map);
		setTexture("enemy02.png");
		
		curTile = tile;
		pos.x = (curTile % path.getWidth()) * 128 + 64;
		pos.y = (curTile / path.getWidth()) * 128 + 64;
	}

	protected void setBaseStats() 
	{
		hp = maxHP = 60;
		armor = 0;
		moveSpeed = 150;
		texWidth = 64;
		texHeight = 64;
		goldValue = 0;
		livesValue = 1;
	}
}
