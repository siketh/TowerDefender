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
		Upgrade up = new Upgrade(this);
		up.setName("Fire Tower");
		up.setTexName("damage_button.png");
		up.setCost(100);
		up.setLevels(1);
		up.setId(0);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Ligtning Tower");
		up.setTexName("tgts_button.png");
		up.setCost(100);
		up.setLevels(1);
		up.setId(1);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Arcane Tower");
		up.setTexName("damage_button.png");
		up.setCost(100);
		up.setLevels(1);
		up.setId(2);
		upgrades.add(up);
	}

	void performUpgrades(Upgrade caller) 
	{
		
		
	}
}
