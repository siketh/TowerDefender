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
		damage.setTexName("sell_button.png");
		damage.setCost(50);
		damage.setLevels(5);
		damage.setId(0);
		upgrades.add(damage);
		
		Upgrade numTargets = new Upgrade(this);
		damage.setName("Max Targets");
		damage.setTexName("sell_button.png");
		damage.setCost(150);
		damage.setLevels(3);
		damage.setId(1);
		upgrades.add(numTargets);
		
		Upgrade rangeIncrease = new Upgrade(this);
		damage.setName("Increased Range");
		damage.setTexName("sell_button.png");
		damage.setCost(50);
		damage.setLevels(5);
		damage.setId(2);
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
