package com.group23.towerdefense.tower;

import com.badlogic.gdx.math.Vector2;
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
	private boolean isAOE;
	private float aoeSize;
	
	public SlowTower()
	{
		setCooldownTime(400L);
		setRange(200.0f);
		setDamage(7);
		setGoldCost(150);
		setProjectileType("poison.png");
		setProjectileSpeed(100);
		maxTargets = 1;
		addUpgrades();
		upgradeType = 0;
		slowAmount = .75f;
		slowDuration = 1;
		isAOE = false;
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
		case 3:
			slowDuration += 0.5;
			caller.setCost(caller.getCost() + 25);
			break;
		case 4:
			poisonDuration += 2;
			caller.setCost(caller.getCost() + 25);
			break;
		case 5:
			slowAmount -= 0.05;
			caller.setCost(caller.getCost() + 25);
			break;
		case 6: 
			setDamage(5);
			isAOE = true;
			aoeSize = 50;
			upgrades.remove(2);
			Upgrade up = new Upgrade(this);
			up.setName("AOE Size");
			up.setTexName("damage_button.png");
			up.setCost(50);
			up.setLevels(3);
			up.setId(7);
			upgrades.add(up);
			break;
		case 7:
			aoeSize += 15;
			caller.setCost(caller.getCost() + 25);
			break;
		case 8:
			poisonAmount += 2;
			caller.setCost(caller.getCost() + 25);
			break;
		case 9:
			setMaxTargets(getMaxTargets() + 1);
			caller.setCost(caller.getCost() + 100);
			break;
		case 10:
			slowDuration += 0.05;
			caller.setCost(caller.getCost() + 50);
			break;
		}
	}
	
	void makeFrostTower()
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		slowAmount -= .15;
		slowDuration += 0.5;
		
		Upgrade up = new Upgrade(this);
		up.setName("Duration");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(3);
		up.setId(3);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Slow Amount");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(3);
		up.setId(5);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("AOE");
		up.setTexName("damage_button.png");
		up.setCost(150);
		up.setLevels(1);
		up.setId(6);
		upgrades.add(up);
	}
	
	void makePoisonTower()
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		upgradeType = 1;
		slowAmount += .05;
		poisonAmount = 3;
		poisonDuration = 3;
		
		Upgrade up = new Upgrade(this);
		up.setName("Duration");
		up.setTexName("damage_button.png");
		up.setCost(75);
		up.setLevels(3);
		up.setId(4);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Poison Damage");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(5);
		up.setId(8);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Max Targets");
		up.setTexName("tgts_button.png");
		up.setCost(150);
		up.setLevels(3);
		up.setId(9);
		upgrades.add(up);
	}
	
	void makeIceTower()
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		slowAmount = 0f;
		slowDuration = .1f;
		
		Upgrade up = new Upgrade(this);
		up.setName("Duration");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(2);
		up.setId(10);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Max Targets");
		up.setTexName("tgts_button.png");
		up.setCost(150);
		up.setLevels(1);
		up.setId(9);
		upgrades.add(up);
	}
	
	protected void damage(long ms)
	{
		if(isAOE == false)
			super.damage(ms);
		else
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
}
