package com.group23.towerdefense.tower;

public class DirectMultiAttackTower extends CircularRangeTower
{
	public DirectMultiAttackTower()
	{
		setCooldownTime(100L);
		setDamage(2);
		setRange(250.0f);
	}
}
