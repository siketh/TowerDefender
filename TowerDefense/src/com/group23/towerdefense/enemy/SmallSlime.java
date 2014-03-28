package com.group23.towerdefense.enemy;

import com.group23.towerdefense.level.Level;

public class SmallSlime extends Enemy
{
	public SmallSlime(Level map) 
	{
		super(map);
		setTexture("enemy01.png");
	}

	protected void setBaseStats() 
	{
		hp = maxHP = 75;
		armor = 0;
		moveSpeed = 90;
		texWidth = 64;
		texHeight = 64;
		goldValue = 10;
		livesValue = 1;
	}
}
