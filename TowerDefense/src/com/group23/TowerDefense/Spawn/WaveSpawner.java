package com.group23.TowerDefense.Spawn;

import com.badlogic.gdx.utils.Array;
import com.group23.TowerDefense.EnemyTypes;
import com.group23.TowerDefense.Level;
import com.group23.TowerDefense.Enemy.Enemy;

public class WaveSpawner 
{
	private Array<Spawner> spawns;			//Array to hold the enemies in the wave
	private Array<Enemy> enemies;			//Array to put the enemy inside
	private Level map;						//Level pointer to pass to the enemies
	
	public WaveSpawner(Array<Enemy> enemies, Level map)
	{
		 spawns = new Array<Spawner>();
		 this.enemies = enemies;
		 this.map = map;
	}
	
	//Checks to see if any enemies are ready to be spawned
	//Returns true if no more enemies
	public boolean update(double t)
	{
		while(spawns.get(0).checkTime(t))
		{
			spawns.get(0).spawnEnemy();
			spawns.removeIndex(0);
			
			//Sees if the array is empty
			if(spawns.size == 0)
				return true;
		}
		
		return false;
	}
	
	public void addSpawn(double time, EnemyTypes name)
	{
		spawns.add(new Spawner(time, name, enemies, map));
	}
}
