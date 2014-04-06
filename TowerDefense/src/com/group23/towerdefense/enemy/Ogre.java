package com.group23.towerdefense.enemy;

import com.group23.towerdefense.Level;

public class Ogre extends Enemy
{
	public Ogre(Level map)
	{
		super(map, 1.0);
		setTexture("ogre.png");
		System.out.println("1");
	}
	
	public Ogre(Level map, double scale)
	{
		super(map, scale);
		System.out.println(scale);
		setTexture("ogre.png");
	}

	protected void setBaseStats()
	{
		hp = maxHP = 200;
		armor = 2;
		moveSpeed = 64;
		texWidth = 64;
		texHeight = 64;
		goldValue = 30;
		livesValue = 2;
		healthRegen = 2;
	}
}
