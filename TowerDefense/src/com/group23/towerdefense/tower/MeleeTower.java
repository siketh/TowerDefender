package com.group23.towerdefense.tower;

import com.group23.towerdefense.enemy.Debuff;
import com.group23.towerdefense.enemy.DebuffType;
import com.group23.towerdefense.enemy.Enemy;

public class MeleeTower extends CircularRangeTower
{
	private boolean isStun;
	public MeleeTower()
	{
		setCooldownTime(250L);
		setDamage(9);
		setRange(160.0f);
		setMaxTargets(1);
		setGoldCost(100);
		setProjectileType("sword.png");
		setProjectileSpeed(1000);
		setArmorPen(1);
		addUpgrades();
		isStun = false;
	}
	
	protected void causeEffect(Enemy e)
	{
		if(isStun = true)
			e.addDebuff(new Debuff(0, 0.05f, DebuffType.Slow));
	}
	
	void addUpgrades() 
	{
		Upgrade up = new Upgrade(this);
		up.setName("Damage");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(5);
		up.setId(0);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Range");
		up.setTexName("tgts_button.png");
		up.setCost(50);
		up.setLevels(3);
		up.setId(1);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Armor Piercing");
		up.setTexName("damage_button.png");
		up.setCost(50);
		up.setLevels(3);
		up.setId(2);
		upgrades.add(up);
		
		up = new Upgrade(this);
		up.setName("Stun");
		up.setTexName("ap_button.png");
		up.setCost(150);
		up.setLevels(1);
		up.setId(3);
		upgrades.add(up);
		
	}

	
	void performUpgrades(Upgrade caller) 
	{
		switch(caller.getId())
		{
		case 0:
			setDamage(getDamage() + 3);
			caller.setCost(caller.getCost() + 25);
			break;
		case 1:
			setRange(getRange() + 20);
			break;
		case 2: 
			setArmorPen(getArmorPen() + 3);
			caller.setCost(caller.getCost() + 50);
		case 3: 
			isStun = true;
		}
	}
}
