package com.group23.towerdefense.tower;

import com.group23.towerdefense.enemy.Debuff;
import com.group23.towerdefense.enemy.DebuffType;
import com.group23.towerdefense.enemy.Enemy;

public class MagicTower extends CircularRangeTower
{
	private boolean isFlame;
	private float duration;
	
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
		case 3:
			caller.setCost(caller.getCost() + 25);
			setDamage(getDamage() + 5);
			break;
		case 4: 
			caller.setCost(caller.getCost() + 25);
			setCooldownTime((long)(getCooldownTime() * .9));
			break;
		case 5:
			setRange(getRange() + 50);
			break;
		case 6: 
			caller.setCost(caller.getCost() + 25);
			setNumBounces(getNumBounces() + 1);
			break;
		case 7: 
			caller.setCost(caller.getCost() + 25);
			setDamage(getDamage() + 3);
			break;
		case 8:
			caller.setCost(caller.getCost() + 25);
			duration++;
			break;
		case 9:
			caller.setCost(caller.getCost() + 50);
			setDamage(getDamage() + 1);
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
		setTexture("arcanemagictower.png");

		Upgrade up = new Upgrade(this);
		up.setName("Damage");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(5);
		up.setId(3);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Attack Speed");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(5);
		up.setId(4);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Range");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(5);
		up.setId(5);
		upgrades.add(up);
	}

	private void makeLightningTower() 
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		setProjectileType("lightning.png");
		setNumBounces(2);
		setDamage(7);
		setCooldownTime(1000L);
		setTexture("lightningmagictower.png");
		
		Upgrade up = new Upgrade(this);
		up.setName("Bounces");
		up.setTexName("damage_button.png");
		up.setCost(75);
		up.setLevels(3);
		up.setId(6);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Damage");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(5);
		up.setId(7);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Range");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(5);
		up.setId(5);
		upgrades.add(up);
	}

	private void makeFlameTower() 
	{
		for(int i = 0; i < 3; i++)
			upgrades.remove(0);
		isFlame = true;
		setProjectileType("flamethrower.png");
		setCooldownTime(80L);
		setRange(275f);
		setDamage(2);
		setTexture("firemagictower.png");
		
		Upgrade up = new Upgrade(this);
		up.setName("Duration");
		up.setTexName("damage_button.png");
		up.setCost(25);
		up.setLevels(3);
		up.setId(8);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Attack Speed");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(5);
		up.setId(4);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Damage");
		up.setTexName("damage_button.png");
		up.setCost(100);
		up.setLevels(3);
		up.setId(9);
		upgrades.add(up);
	}
}
