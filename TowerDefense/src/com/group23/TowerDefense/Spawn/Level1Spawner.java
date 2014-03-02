package com.group23.TowerDefense.Spawn;

import com.badlogic.gdx.utils.Array;
import com.group23.TowerDefense.EnemyTypes;
import com.group23.TowerDefense.Enemy.Enemy;
import com.group23.TowerDefense.Level.Level;

public class Level1Spawner extends LevelSpawner
{
	public Level1Spawner(Array<Enemy> enemies, Level map) 
	{
		super(enemies, map);
	}

	protected void setTotalWaves() 
	{
		totalWaves = 5;
	}

	protected void setUpWaves() 
	{
		waves[0].addSpawn(0.1, EnemyTypes.enemy);
		waves[0].addSpawn(0.8, EnemyTypes.enemy);
		waves[0].addSpawn(1.7, EnemyTypes.enemy);
		waves[0].addSpawn(3.5, EnemyTypes.enemy);
		waves[0].addSpawn(5, EnemyTypes.enemy);
		
		waves[1].addSpawn(0.1, EnemyTypes.enemy);
		waves[1].addSpawn(0.3, EnemyTypes.enemy);
		waves[1].addSpawn(0.5, EnemyTypes.enemy);
		waves[1].addSpawn(0.7, EnemyTypes.enemy);
		waves[1].addSpawn(0.9, EnemyTypes.enemy);
		waves[1].addSpawn(1.1, EnemyTypes.enemy);
		waves[1].addSpawn(1.3, EnemyTypes.enemy);
		
		waves[2].addSpawn(0.1, EnemyTypes.enemy);
		waves[2].addSpawn(0.2, EnemyTypes.enemy);
		waves[2].addSpawn(0.3, EnemyTypes.enemy);
		
		waves[3].addSpawn(0.1, EnemyTypes.enemy);
		
		waves[4].addSpawn(0.1, EnemyTypes.enemy);
		
	}

}
