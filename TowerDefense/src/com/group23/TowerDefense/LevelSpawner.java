package com.group23.TowerDefense;

import com.badlogic.gdx.utils.Array;

public class LevelSpawner 
{
	private int wave;						//Stores the current wave
	private int totalWaves;					//Stores total number of waves
	private float delta;					//Time since wave started
	private WaveSpawner[] waves;			//Stores the various waves
	private boolean currentlySpawning;		//Whether or not the spawner is in the middle of spawning a wave
	
	public LevelSpawner(Array<Enemy> enemies, Level map)
	{
		totalWaves = 5;
		
		currentlySpawning = false;
		wave = 0;
		delta = 0;
		waves = new WaveSpawner[totalWaves];
		
		for(int i = 0; i < totalWaves; i++)
		{
			waves[i] = new WaveSpawner(enemies, map);
		}
		setUpWaves();
	}

	//Place where you put all the waves
	private void setUpWaves()
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
	
	//Update function that calls the current wave
	//Once the current wave is completely spawned, turns off the spawner and increments the wave counter
	//Returns true once the wave is completely spawned
	//Returns false if there is more to spawn
	public boolean update(double t)
	{
		//Sees if the wave is already done
		if(currentlySpawning == false)
			return true;
		
		delta += t;
		if(waves[wave].update(delta))
		{
			wave++;
			delta = 0;
			currentlySpawning = false;
			return true;
		}
		
		return false;
	}
	
	public int getWave() {
		return wave;
	}
	
	public void startWave() {
		currentlySpawning = true;
	}
	
	//Returns whether or not the level is finished
	public boolean finished() {
		return wave == totalWaves;	
	}
}
