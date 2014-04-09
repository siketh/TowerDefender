package com.group23.towerdefense.enemy;

import com.group23.towerdefense.Level;

public class Goblin extends Enemy
{
	public Goblin(Level map) 
	{
		super(map, 1.0);
		setTexture("goblin.png");
	}
	
	public Goblin(Level map, double scale) 
	{
		super(map, scale);
		setTexture("goblin.png");
	}

	protected void setBaseStats() 
	{
		hp = maxHP = 100;
		armor = 0;
		moveSpeed = 128;
		healthRegen = 1;
		texWidth = 64;
		texHeight = 64;
		goldValue = 20;
		livesValue = 1;
	}
}
