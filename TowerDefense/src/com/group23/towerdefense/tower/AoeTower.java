package com.group23.towerdefense.tower;

public class AoeTower extends CircularRangeTower 
{
	public AoeTower()
	{
		setCooldownTime(600L);
		setDamage(5);
		setRange(300.0f);
		setMaxTargets(1);
		setGoldCost(150);
		setProjectileType("arrow.png");
		setProjectileSpeed(400);
		setArmorPen(0);
		addUpgrades();
	}

	void addUpgrades() 
	{
		
	}

	void performUpgrades(Upgrade caller) 
	{
		
	}
}
