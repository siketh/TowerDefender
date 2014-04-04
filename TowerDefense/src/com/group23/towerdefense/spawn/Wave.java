package com.group23.towerdefense.spawn;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.TimeUtils;
import com.group23.towerdefense.Level;
import com.group23.towerdefense.enemy.Enemy;

public class Wave
{
	private long startSpawnTime;
	private int curSpawnIndex;
	private ArrayMap<Long, SpawnInfo> spawn;
	private Array<Enemy> enemies;

	public static interface Generator
	{
		void generate(Wave wave);
	}

	public Wave()
	{
		startSpawnTime = 0;
		curSpawnIndex = -1;

		spawn = new ArrayMap<Long, SpawnInfo>();
		spawn.ordered = true;

		enemies = null;
	}

	/**
	 * Adds an enemy class to be spawned.<br/>
	 * <b>Note:</b> Do not call after start()
	 * 
	 * @param ms
	 *            The time (in milliseconds) to spawn the enemy since starting
	 *            the wave
	 * @param enemyType
	 *            The <code>Enemy</code> class to spawn
	 * @return This to allow chain method calls
	 * @throws IllegalStateException
	 *             If start() has been called before a call to addSpawn(...)
	 */
	public Wave addSpawn(long ms, Class<? extends Enemy> enemyType)
			throws IllegalStateException
	{
		addSpawn(ms, enemyType, 1.0f);
		return this;
	}

	/**
	 * Adds an enemy class to be spawned.<br/>
	 * <b>Note:</b> Do not call after start()
	 * 
	 * @param ms
	 *            The time (in milliseconds) to spawn the enemy since the
	 *            starting wave
	 * @param enemyType
	 *            The <code>Enemy</code> class to spawn
	 * @param modifier
	 *            The modifier to multiply the enemy stats by
	 * @return This to allow chain method calls
	 * @throws IllegalStateException
	 *             If start() has been called before a call to addSpawn(...)
	 */
	public Wave addSpawn(long ms, Class<? extends Enemy> enemyType,
			float modifier) throws IllegalStateException
	{
		if (hasStarted())
			throw new IllegalStateException("addSpawn(...) cannot be called after the Wave has been started");

		SpawnInfo info = new SpawnInfo();
		info.enemyType = enemyType;
		info.modifier = modifier;

		spawn.put(ms, info);
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
		while (!isFinished()
				&& ms - startSpawnTime >= spawn.getKeyAt(curSpawnIndex)
						.longValue())
		{

			Class<? extends Enemy> type;
			Constructor<? extends Enemy> constructor;
			Enemy enemy;

			try
			{
				SpawnInfo info = spawn.getValueAt(curSpawnIndex);
				type = info.enemyType;
				constructor = type.getConstructor(Level.class);
				enemy = constructor.newInstance(level);
				enemy.setModifier(info.modifier);
				enemies.add(enemy);
			}
			catch (SecurityException e)
			{

			}
			catch (NoSuchMethodException e)
			{

			}
			catch (IllegalArgumentException e)
			{

			}
			catch (InstantiationException e)
			{

			}
			catch (IllegalAccessException e)
			{

			}
			catch (InvocationTargetException e)
			{

			}

			curSpawnIndex++;
		}
	}

	public boolean hasStarted()
	{
		return curSpawnIndex != -1;
	}

	/**
	 * Returns whether the wave has finished spawning all its enemies
	 * 
	 * @return True if no more enemies left to spawn
	 */
	public boolean isFinished()
	{
		return !(0 <= curSpawnIndex && curSpawnIndex < spawn.size);
	}

	private class SpawnInfo
	{
		public Class<? extends Enemy> enemyType;
		public float modifier;
	}
}
