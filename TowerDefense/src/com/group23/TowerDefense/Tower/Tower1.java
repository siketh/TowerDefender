package com.group23.TowerDefense.Tower;

import com.group23.TowerDefense.Enemy.Enemy;
import com.group23.TowerDefense.Level.Level;

public class Tower1 extends Tower 
{
	public Tower1(Level map, int x, int y) 
	{
		super(map, x, y);
	}

	public void update()
	{
		if (targets.size != 1)
		{
			Enemy e = findClosestTarget();
			if (e != null) targets.add(e);
		}	
		
		super.update();
	}

	public int getDamage()
	{ 
		return 2; 
	}
	
	public float getRange()
	{ 
		return 250.0f; 
	}
	
	public long getCooldownTime()
	{ 
		return 100; 
	}
}
