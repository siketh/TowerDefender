package com.group23.towerdefense.tower;

import com.group23.towerdefense.enemy.Enemy;

public class ArrowTower extends SingleTargetCircularRangeTower
{
	public ArrowTower()
	{
		setCooldownTime(100L);
		setRange(250.0f);
		setDamage(4);
		setGoldCost(100);
		setTexture("tower00.png");
		setProjectileType("arrow.png");
		setProjectileSpeed(100);
		maxTargets = 1;
	}
	
	protected void causeEffect(Enemy e)
	{
	}
}
