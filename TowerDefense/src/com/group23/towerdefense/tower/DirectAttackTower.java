package com.group23.towerdefense.tower;

public class DirectAttackTower extends SingleTargetCircularRangeTower
{
	public DirectAttackTower()
	{
		setCooldownTime(100L);
		setRange(250.0f);
		setDamage(4);
		setGoldCost(100);
	}
}
