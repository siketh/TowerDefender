package com.group23.towerdefense.tower;

import com.group23.towerdefense.enemy.Debuff;
import com.group23.towerdefense.enemy.DebuffType;
import com.group23.towerdefense.enemy.Enemy;

public class SlowTower extends  SingleTargetCircularRangeTower
{
	private int upgradeType;
	private float slowAmount;
	private float slowDuration;
	private int poisonAmount;
	private int poisonDuration;
	public SlowTower()
	{
		setCooldownTime(400L);
		setRange(200.0f);
		setDamage(7);
		setGoldCost(150);
		setProjectileType("arrow.png");
		setProjectileSpeed(100);
		maxTargets = 1;
		addUpgrades();
		upgradeType = 0;
		slowAmount = .75f;
		slowDuration = 1;
	}
	
	protected void causeEffect(Enemy e)
	{
		e.addDebuff(new Debuff(slowAmount, slowDuration, DebuffType.Slow));
		if(upgradeType == 1)
			e.addDebuff(new Debuff(poisonAmount, poisonDuration, DebuffType.Poison, 1));
	}

	void addUpgrades() 
	{
		Upgrade up = new Upgrade(this);
		up.setName("Frost Tower");
		up.setTexName("damage_button.png");
		up.setCost(100);
		up.setLevels(1);
		up.setId(0);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Poison Tower");
		up.setTexName("tgts_button.png");
		up.setCost(100);
		up.setLevels(1);
		up.setId(1);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Ice Tower");
		up.setTexName("damage_button.png");
		up.setCost(100);
		up.setLevels(1);
		up.setId(2);
		upgrades.add(up);
	}

	void performUpgrades(Upgrade caller) 
	{
		switch(caller.getId())
		{
		case 0:
			makeFrostTower();
			break;
		case 1:
			makePoisonTower();
			break;
		case 2:
			makeIceTower();
			break;
		}
	}
	
	void makeFrostTower()
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		slowAmount -= .15;
		slowDuration += 0.5;
	}
	
	void makePoisonTower()
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		upgradeType = 1;
		slowAmount += .05;
		poisonAmount = 3;
		poisonDuration = 3;
	}
	
	void makeIceTower()
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		slowAmount = 0f;
		slowDuration = .1f;
	}
}
