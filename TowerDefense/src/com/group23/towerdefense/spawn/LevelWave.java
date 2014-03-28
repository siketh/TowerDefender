package com.group23.towerdefense.spawn;

import com.badlogic.gdx.utils.Array;
import com.group23.towerdefense.Level;
import com.group23.towerdefense.enemy.Enemy;

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
	}
	
	/**
	 * Adds a wave created by a <i>WaveGenerator</i>
	 * @param gen The <i>WaveGenerator</i> to generate the enemies of a wave
	 */
	public void addWave(WaveGenerator gen)
	{
		Wave wave = new Wave();
		gen.generate(wave);
		waves.add(wave);
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