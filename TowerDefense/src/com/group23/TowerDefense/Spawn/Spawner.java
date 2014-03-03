package com.group23.TowerDefense.Spawn;

import com.badlogic.gdx.utils.Array;
import com.group23.TowerDefense.EnemyTypes;
import com.group23.TowerDefense.Enemy.*;
import com.group23.TowerDefense.Level.Level;

public class Spawner 
{
	private double time;				//Time into the wave at which the Enemy should spawn
	private EnemyTypes name;			//String holding the type of enemy
	private Array<Enemy> enemies;		//Array to put the enemy inside
	private Level map;					//Level pointer to pass to the enemies
	
	public Spawner(double time, EnemyTypes name, Array<Enemy> enemies, Level map)
	{
		this.time = time;
		this.name = name;
		this.enemies = enemies;
		this.map = map;
	}
	
	//Spawns the enemy
	public void spawnEnemy()
	{
		switch(name)
		{
		case enemy:
			enemies.add(new Enemy1(map));
			break;
		default:
			break;
			
		}
	}
	
	//Returns whether or not the enemy is ready to spawn
	public boolean checkTime(double waveTime)
	{
		return waveTime >= time;
	}
	
	
}
