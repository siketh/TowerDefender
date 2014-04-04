package com.group23.towerdefense.tower;

import com.group23.towerdefense.enemy.Debuff;
import com.group23.towerdefense.enemy.DebuffType;
import com.group23.towerdefense.enemy.Enemy;

public class ArrowTower extends SingleTargetCircularRangeTower
{
	public ArrowTower()
	{
		setCooldownTime(100L);
		setRange(250.0f);
		setDamage(0);
		setGoldCost(100);
		setTexture("tower00.png");
		maxTargets = 1;
	}
	
	protected void causeEffect(Enemy e)
	{
		e.addDebuff(new Debuff(1, 4, DebuffType.Burn, 0.5f, 100));
	}
}
