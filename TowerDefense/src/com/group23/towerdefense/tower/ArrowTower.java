package com.group23.towerdefense.tower;

public class ArrowTower extends CircularRangeTower
{
	public ArrowTower()
	{
		setCooldownTime(300L);
		setDamage(10);
		setRange(250.0f);
		setMaxTargets(1);
		setGoldCost(100);
		setProjectileType("arrow.png");
		setProjectileSpeed(500);
	}
}
