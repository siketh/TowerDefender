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
		setCooldownTime(500L);
		setDamage(5);
		setRange(300.0f);
		setMaxTargets(1);
		setGoldCost(150);
		setProjectileType("arrow.png");
		setProjectileSpeed(400);
		setArmorPen(0);
		addUpgrades();
		aoeSize = 40;
		isFlame = false;
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
		Upgrade up = new Upgrade(this);
		up.setName("Stomp Tower");
		up.setTexName("damage_button.png");
		up.setCost(100);
		up.setLevels(1);
		up.setId(0);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Bomb Tower");
		up.setTexName("tgts_button.png");
		up.setCost(100);
		up.setLevels(1);
		up.setId(1);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Flame Tower");
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
			makeStompTower();
			break;
		case 1: 
			makeBombTower();
			break;
		case 2:
			makeFlameTower();
			break;
		}
	}
	
	private void makeStompTower()
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		setRange(160f);
		setCooldownTime(750L);
		aoeSize = 0;
		setMaxTargets(99);
		setDamage(4);
	}
	
	private void makeBombTower()
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		
		setDamage(8);
		aoeSize = 50;
		setCooldownTime(600L);
	}
	
	private void makeFlameTower()
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		aoeSize = 0;
		setCooldownTime(350L);
		isFlame = true;
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
							for(int j = getTargets().size - 1; j >= 0; j++)
							{
								if(getTargets().get(j) == level.getEnemies().get(e))
									getTargets().removeIndex(j);
							}
							level.removeEnemy(level.getEnemies().get(e));
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
