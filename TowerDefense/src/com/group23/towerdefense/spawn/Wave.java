package com.group23.towerdefense.spawn;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.TimeUtils;
import com.group23.towerdefense.enemy.Enemy;
import com.group23.towerdefense.level.Level;

public class Wave 
{
	private long startSpawnTime;
	private int curSpawnIndex;
	private ArrayMap<Long, Class<? extends Enemy>> spawn;
	private Array<Enemy> enemies;
	
	public Wave()
	{
		startSpawnTime = 0;
		curSpawnIndex = -1;
		
		spawn = new ArrayMap<Long, Class<? extends Enemy>>();
		spawn.ordered = true;
		
		enemies = null;
	}
	
	/**
	 * Adds an enemy class to be spawned.<br/>
	 * <b>Note:</b> Do not call after start()
	 * @param ms The time (in milliseconds) to spawn the enemy since starting the wave
	 * @param enemyType The <i>Enemy</i> class to spawn
	 * @return This to allow chain method calls
	 */
	public Wave addSpawn(long ms, Class<? extends Enemy> enemyType)
	{
		spawn.put(ms, enemyType);
		return this;
	}
	
	/**
	 * Starts the wave, allowing enemies to be spawned.<br/>
	 * <b>Must only be called once</b>
	 */
	public void start(Array<Enemy> enemies)
	{
		this.enemies = enemies;
		startSpawnTime = TimeUtils.millis();
		curSpawnIndex = 0;
	}
	
	/**
	 * Updates the wave, spawning enemies if their time to spawn has arrived.<br/>
	 * <b>Note:</b> Only call after calling start() <i>once</i>
	 */
	public void update(Level level)
	{
		long ms = TimeUtils.millis();
		while (!isFinished() && 
				ms - startSpawnTime >= spawn.getKeyAt(curSpawnIndex).longValue())
		{
		
				Class<? extends Enemy> type;
				Constructor<? extends Enemy> constructor;
				Enemy enemy;
				
				try {
					type = spawn.getValueAt(curSpawnIndex);
					constructor = type.getConstructor(Level.class);
					enemy = constructor.newInstance(level);
					enemies.add(enemy);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			curSpawnIndex++;
		}
	}
	
	/**
	 * Returns whether the wave has finished spawning all its enemies
	 * @return True if no more enemies left to spawn
	 */
	public boolean isFinished()
	{
		return !(0 <= curSpawnIndex && curSpawnIndex < spawn.size);
	}
}
