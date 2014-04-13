package com.group23.towerdefense.tower;

import com.badlogic.gdx.math.Vector2;
import com.group23.towerdefense.enemy.Debuff;
import com.group23.towerdefense.enemy.DebuffType;
import com.group23.towerdefense.enemy.Enemy;

public class AoeTower extends CircularRangeTower 
{
	private float aoeSize;
	private boolean isFlame;
	private float debuffDuration;
	private float debuffDamage;
	
	public AoeTower()
	{
		setCooldownTime(600L);
		setDamage(5);
		setRange(300.0f);
		setMaxTargets(1);
		setGoldCost(150);
		setProjectileType("arrow.png");
		setProjectileSpeed(400);
		setArmorPen(0);
		addUpgrades();
		aoeSize = 1;
		isFlame = true;
		debuffDuration = 4;
		debuffDamage = 2;
	}
	
	protected void causeEffect(Enemy e)
	{
		if(isFlame == true)
			e.addDebuff(new Debuff(debuffDamage, debuffDuration, DebuffType.Burn, 0.25f, 100));
	}

	void addUpgrades() 
	{
		
	}

	void performUpgrades(Upgrade caller) 
	{
		
	}
	
	protected void damage(long ms)
	{
		for(int t = projectiles.size() - 1; t >= 0; t--)
		{
			int i = projectiles.get(t).act(((float)(ms - lastMS) / (float)1000));
			if(i == 0)
			{
				Vector2 loc = projectiles.get(t).getTarget().getPosition();
				for(int e = level.getEnemies().size - 1; e >= 0; e--)
				{
					if(level.getEnemies().get(e).getPosition().dst(loc) <= aoeSize)
					{
						level.getEnemies().get(e).dealDamage(getDamage(), getArmorPen());
						causeEffect(level.getEnemies().get(e));
						if (!level.getEnemies().get(e).isAlive())
						{
							level.getEnemies().get(e).rewardGold();
							level.removeEnemy(level.getEnemies().get(e));
							for(int j = 0; j < getTargets().size; j++)
							{
								if(getTargets().get(j) == level.getEnemies().get(e))
									getTargets().removeIndex(j);
							}
							
						}
					}
				}
				projectiles.remove(t);
			}
			else if(i == 1)
			{
				projectiles.remove(t);
			}
		}
	}
}
