package com.group23.towerdefense.tower;

public class MultiArrowTower extends CircularRangeTower
{
	public MultiArrowTower()
	{
		setCooldownTime(150L);
		setDamage(2);
		setRange(250.0f);
		setMaxTargets(2);
		setGoldCost(100);
		setTexture("tower01.png");
	}
}
