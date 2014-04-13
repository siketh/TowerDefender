package com.group23.towerdefense.tower;

import com.group23.towerdefense.enemy.Debuff;
import com.group23.towerdefense.enemy.DebuffType;
import com.group23.towerdefense.enemy.Enemy;

public class MagicTower extends CircularRangeTower
{
	private boolean isFlame;
	private float duration;
	private boolean isLightning;
	
	public MagicTower()
	{
		setCooldownTime(750L);
		setDamage(15);
		setRange(300.0f);
		setMaxTargets(1);
		setGoldCost(150);
		setProjectileType("magic.png");
		setProjectileSpeed(500);
		setArmorPen(4);
		addUpgrades();
		isFlame = false;
		duration = 3;
		isLightning = false;
	}
	
	protected void causeEffect(Enemy e)
	{
		if(isFlame == true)
		{
			e.addDebuff(new Debuff(0f, duration, DebuffType.HealRed));
		}
	}
	
	void addUpgrades() 
	{
		Upgrade up = new Upgrade(this);
		up.setName("Fire Tower");
		up.setTexName("damage_button.png");
		up.setCost(100);
		up.setLevels(1);
		up.setId(0);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Lightning Tower");
		up.setTexName("tgts_button.png");
		up.setCost(100);
		up.setLevels(1);
		up.setId(1);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Arcane Tower");
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
			makeFlameTower();
			break;
		case 1:
			makeLightningTower();
			break;
		case 2:
			makeArcaneTower();
			break;
		}
	}

	private void makeArcaneTower() 
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		setProjectileType("arcane.png");
		setRange(400f);
		setDamage(25);
		setCooldownTime(1000L);
		setArmorPen(6);
	}

	private void makeLightningTower() 
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		setProjectileType("lightning.png");
		setNumBounces(2);
		setDamage(7);
		setCooldownTime(1000L);
		
	}

	private void makeFlameTower() 
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		isFlame = true;
		setProjectileType("flamethrower.png");
		setCooldownTime(50L);
		setRange(275f);
		setDamage(1);
	}
}
