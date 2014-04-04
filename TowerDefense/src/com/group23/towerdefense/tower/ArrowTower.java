package com.group23.towerdefense.tower;

public class ArrowTower extends SingleTargetCircularRangeTower
{
	public ArrowTower()
	{
		setCooldownTime(100L);
		setRange(250.0f);
		setDamage(4);
		setGoldCost(100);
		setTexture("tower00.png");
	}
}
