package com.group23.TowerDefense.Spawn;

import com.badlogic.gdx.utils.Array;
import com.group23.TowerDefense.Enemy.Enemy;
import com.group23.TowerDefense.Enemy.Enemy1;
import com.group23.TowerDefense.Enemy.Enemy2;
import com.group23.TowerDefense.Level.Level;

// TODO make LevelWave abstract
public class LevelWave 
{
	private Array<Enemy> enemies;
	private Array<Wave> waves;
	private int curWaveIndex;
	
	public LevelWave()
	{
		enemies = null;
		waves = new Array<Wave>();
		curWaveIndex = 0;
		
		load();
	}
	
	/**
	 * Adds a wave created by a <i>WaveGenerator</i>
	 * @param gen The <i>WaveGenerator</i> to generate the enemies of a wave
	 */
	protected void addWave(WaveGenerator gen)
	{
		Wave wave = new Wave();
		gen.generate(wave);
		waves.add(wave);
	}
	
	// TODO make into abstract method
	public void load()
	{
		// Wave 1
		addWave(new WaveGenerator() {
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
		addWave(new WaveGenerator() {
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
		addWave(new WaveGenerator() {
			public void generate(Wave wave) {
				wave.addSpawn(100, Enemy2.class)
					.addSpawn(200, Enemy1.class)
					.addSpawn(300, Enemy1.class);
			}
		});
		
		// Wave 4
		addWave(new WaveGenerator() {
			public void generate(Wave wave) {
				wave.addSpawn(100, Enemy1.class);
			}
		});
		
		// Wave 5
		addWave(new WaveGenerator() {
			public void generate(Wave wave) {
				wave.addSpawn(100, Enemy1.class);
			}
		});
	}
	
	public void next(Array<Enemy> enemies)
	{
		this.enemies = enemies;
		waves.get(curWaveIndex).start(enemies);
	}
	
	public void update(Level level)
	{
		if (isFinished() || !isPlaying())
			return;
		
		Wave curWave = waves.get(curWaveIndex);
		curWave.update(level);
		
		if (curWave.isFinished() && enemies.size == 0)
		{
			enemies = null;
			curWaveIndex++;
		}
	}
	
	/**
	 * @return True if a wave is currently being played
	 */
	public boolean isPlaying()
	{
		return enemies != null;
	}
	
	/**
	 * @return True if there are no more waves remaining
	 */
	public boolean isFinished()
	{
		return curWaveIndex == waves.size;
	}
}