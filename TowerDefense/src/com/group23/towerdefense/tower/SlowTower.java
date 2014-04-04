package com.group23.towerdefense.tower;

import com.group23.towerdefense.enemy.Debuff;
import com.group23.towerdefense.enemy.DebuffType;
import com.group23.towerdefense.enemy.Enemy;

public class SlowTower extends  SingleTargetCircularRangeTower
{
	public SlowTower()
	{
		setCooldownTime(100L);
		setRange(250.0f);
		setDamage(3);
		setGoldCost(150);
		setTexture("tower00.png");
	}
	
	protected void causeEffect(Enemy e)
	{
		e.addDebuff(new Debuff(0.8f, 1, DebuffType.Slow));
	}

}
