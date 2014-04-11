package com.group23.towerdefense.tower;

public class MeleeTower extends CircularRangeTower
{
	public MeleeTower()
	{
		setCooldownTime(300L);
		setDamage(12);
		setRange(160.0f);
		setMaxTargets(1);
		setGoldCost(100);
		setProjectileType("arrow.png");
		setProjectileSpeed(1000);
		setArmorPen(1);
		addUpgrades();
	}
	
	void addUpgrades() 
	{
		
		
	}

	
	void performUpgrades(Upgrade caller) 
	{
		
	}

}
