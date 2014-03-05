package com.group23.towerdefense.level;

import com.group23.towerdefense.Dir;
import com.group23.towerdefense.enemy.Enemy1;
import com.group23.towerdefense.enemy.Enemy2;
import com.group23.towerdefense.spawn.LevelWave;
import com.group23.towerdefense.spawn.Wave;
import com.group23.towerdefense.spawn.WaveGenerator;

public class Level1 extends Level 
{
	protected int[] loadTiles() 
	{
		return new int[]
			{
				0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0,		//Bottom Right
				1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 2,
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 2,
				1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 2,		
				1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0,
				1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0,
				1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0			//Top Right
			};
	}

	protected void loadWaves(LevelWave waves) 
	{
		// Wave 1
		waves.addWave(new WaveGenerator() {
			public void generate(Wave wave) {
				wave.addSpawn(100,  Enemy1.class)
					.addSpawn(800,  Enemy1.class)
					.addSpawn(1700, Enemy2.class)
					.addSpawn(3500, Enemy1.class)
					.addSpawn(5000, Enemy1.class)
					.addSpawn(6000, Enemy2.class);
			}
		});
		
		// Wave 2
		waves.addWave(new WaveGenerator() {
			public void generate(Wave wave) {
				wave.addSpawn(100,  Enemy1.class)
					.addSpawn(300,  Enemy1.class)
					.addSpawn(500,  Enemy1.class)
					.addSpawn(700,  Enemy1.class)
					.addSpawn(900,  Enemy1.class)
					.addSpawn(1100, Enemy1.class)
					.addSpawn(1300, Enemy1.class);
			}
		});
		
		// Wave 3
		waves.addWave(new WaveGenerator() {
			public void generate(Wave wave) {
				wave.addSpawn(100, Enemy2.class)
					.addSpawn(200, Enemy1.class)
					.addSpawn(300, Enemy1.class);
			}
		});
		
		// Wave 4
		waves.addWave(new WaveGenerator() {
			public void generate(Wave wave) {
				wave.addSpawn(100, Enemy1.class);
			}
		});
		
		// Wave 5
		waves.addWave(new WaveGenerator() {
			public void generate(Wave wave) {
				wave.addSpawn(100, Enemy1.class);
			}
		});
	}

	public int getStartX() 
	{
		return 0;
	}

	public int getStartY() 
	{
		return 1;
	}

	public Dir getStartDir() 
	{
		return Dir.S;
	}
}
