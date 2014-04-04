package com.group23.towerdefense.tower;

public class SlowTower extends  SingleTargetCircularRangeTower
{
	public SlowTower()
	{
		setCooldownTime(100L);
		setRange(250.0f);
		setDamage(3);
		setGoldCost(150);
		setTexture("tower00.png");
	}

}
