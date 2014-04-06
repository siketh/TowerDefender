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
		}
		
	}
}
