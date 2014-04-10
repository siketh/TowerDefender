package com.group23.towerdefense.tower;

public class ArrowTower extends CircularRangeTower
{
	public ArrowTower()
	{
		setCooldownTime(400L);
		setDamage(10);
		setRange(250.0f);
		setMaxTargets(1);
		setGoldCost(100);
		setProjectileType("arrow.png");
		setProjectileSpeed(500);
		setArmorPen(0);
		addUpgrades();
	}

	
	void addUpgrades() 
	{
		Upgrade up = new Upgrade(this);
		up.setName("Damage");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(5);
		up.setId(0);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Max Targets");
		up.setTexName("tgts_button.png");
		up.setCost(150);
		up.setLevels(3);
		up.setId(1);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Range");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(5);
		up.setId(2);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Armor Penetration");
		up.setTexName("ap_button.png");
		up.setCost(50);
		up.setLevels(3);
		up.setId(3);
		upgrades.add(up);
	}

	void performUpgrades(Upgrade caller) 
	{
		switch(caller.getId())
		{
		case 0:
			setDamage(getDamage() + 2);
			caller.setCost(caller.getCost() + 25);
			break;
		case 1:
			setMaxTargets(getMaxTargets() + 1);
			caller.setCost(caller.getCost() + 100);
			break;
		case 2: 
			setRange(getRange() + 25);
		case 3: 
			setArmorPen(getArmorPen() + 3);
			caller.setCost(caller.getCost() + 50);
			
			
		}
		
	}
}
