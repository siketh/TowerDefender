package com.group23.towerdefense.tower;

public class ArrowTower extends CircularRangeTower
{
	public ArrowTower()
	{
		setCooldownTime(300L);
		setDamage(10);
		setRange(250.0f);
		setMaxTargets(1);
		setGoldCost(100);
		setProjectileType("arrow.png");
		setProjectileSpeed(500);
		addUpgrades();
	}

	
	void addUpgrades() 
	{
		Upgrade damage = new Upgrade(this);
		damage.setName("Damage");
		damage.setTexName("damage_button.png");
		damage.setCost(50);
		damage.setLevels(5);
		damage.setId(0);
		upgrades.add(damage);
		
		Upgrade numTargets = new Upgrade(this);
		numTargets.setName("Max Targets");
		numTargets.setTexName("tgts_button.png");
		numTargets.setCost(150);
		numTargets.setLevels(3);
		numTargets.setId(1);
		upgrades.add(numTargets);
		
		Upgrade rangeIncrease = new Upgrade(this);
		rangeIncrease.setName("Increased Range");
		rangeIncrease.setTexName("damage_button.png");
		rangeIncrease.setCost(50);
		rangeIncrease.setLevels(5);
		rangeIncrease.setId(2);
		upgrades.add(rangeIncrease);
	}

	void performUpgrades(Upgrade caller) 
	{
		switch(caller.getId())
		{
		case 0:
			setDamage(getDamage() + 3);
			caller.setCost(caller.getCost() + 25);
			caller.incrementLevel();
			break;
		case 1:
			setMaxTargets(getMaxTargets() + 1);
			caller.setCost(caller.getCost() + 100);
			caller.incrementLevel();
			break;
		case 2: 
			setRange(getRange() + 25);
			caller.incrementLevel();
		}
		
	}
}
