package com.group23.towerdefense.tower;

import com.group23.towerdefense.enemy.Debuff;
import com.group23.towerdefense.enemy.DebuffType;
import com.group23.towerdefense.enemy.Enemy;

public class SlowTower extends  SingleTargetCircularRangeTower
{
	public SlowTower()
	{
		setCooldownTime(350L);
		setRange(200.0f);
		setDamage(6);
		setGoldCost(150);
		setProjectileType("arrow.png");
		setProjectileSpeed(100);
		maxTargets = 1;
		addUpgrades();
	}
	
	protected void causeEffect(Enemy e)
	{
		e.addDebuff(new Debuff(0.65f, 1, DebuffType.Slow));
	}

	void addUpgrades() 
	{
		
	}

	void performUpgrades(Upgrade caller) 
	{
		
	}
}
