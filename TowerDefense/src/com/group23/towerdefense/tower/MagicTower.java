package com.group23.towerdefense.tower;

public class MagicTower extends CircularRangeTower
{
	public MagicTower()
	{
		setCooldownTime(750L);
		setDamage(15);
		setRange(300.0f);
		setMaxTargets(1);
		setGoldCost(150);
		setProjectileType("magic.png");
		setProjectileSpeed(500);
		setArmorPen(5);
		addUpgrades();
	}

	
	void addUpgrades() 
	{
		
	}

	void performUpgrades(Upgrade caller) 
	{
		
		
	}
}
