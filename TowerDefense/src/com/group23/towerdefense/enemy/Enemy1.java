package com.group23.towerdefense.enemy;

import com.group23.towerdefense.Level;

public class Enemy1 extends Enemy
{
	public Enemy1(Level map) 
	{
		super(map);
		setTexture("enemy01.png");
	}

	protected void setBaseStats() 
	{
		hp = maxHP = 100;
		armor = 0;
		moveSpeed = 128;
		texWidth = 64;
		texHeight = 64;
		goldValue = 20;
		livesValue = 1;
	}
}
