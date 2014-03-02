package com.group23.TowerDefense.Spawn;

import com.badlogic.gdx.utils.Array;
import com.group23.TowerDefense.Level;
import com.group23.TowerDefense.Enemy.Enemy;

public abstract class LevelSpawner 
{
	protected int wave;						//Stores the current wave
	protected int totalWaves;					//Stores total number of waves
	protected float delta;					//Time since wave started
	protected WaveSpawner[] waves;			//Stores the various waves
	protected boolean currentlySpawning;		//Whether or not the spawner is in the middle of spawning a wave
	
	public LevelSpawner(Array<Enemy> enemies, Level map)
	{
		setTotalWaves();
		
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

	abstract protected void setTotalWaves();
	
	//Place where you put all the waves
	abstract protected void setUpWaves();
	
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
