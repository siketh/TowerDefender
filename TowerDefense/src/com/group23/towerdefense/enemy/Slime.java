package com.group23.towerdefense.enemy;

import com.group23.towerdefense.level.Level;

public class Slime extends Enemy
{
	public Slime(Level map) 
	{
		super(map);
		setTexture("enemy01.png");
	}

	protected void setBaseStats() 
	{
		hp = maxHP = 150;
		armor = 0;
		moveSpeed = 64;
		texWidth = 64;
		texHeight = 64;
		goldValue = 10;
		livesValue = 2;
	}
}
