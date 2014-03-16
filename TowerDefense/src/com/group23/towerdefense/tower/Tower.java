package com.group23.towerdefense.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.group23.towerdefense.TowerDefense;
import com.group23.towerdefense.enemy.Enemy;
import com.group23.towerdefense.level.Level;

public abstract class Tower
{
	public  static Texture texture      = null;
	private static final int texWidth  = 64;
	private static final int texHeight = 64;
	
	public static boolean DEBUG_DRAWRANGE = true;
	public static boolean DEBUG_DRAWTARGET = true;
	
	// Tile index position in the map array
	private int tile;
	
	// World coordinate of tower
	private Vector2 pos = new Vector2();
	
	// Time in between shots
	private long lastShotFired = TimeUtils.millis();
	
	private Level level;
	private Array<Enemy> targets = new Array<Enemy>();
	
	private int damage = 0;
	private long cooldownTime = 0L;
	
	/**
	 * Finds targets to attack and adds them to the input <code>Array</code>.
	 * Place algorithms to search for target enemies in your overridden implementation.
	 * 
	 * @param targets The <code>Array</code> containing the target enemies
	 */
	abstract void findTargets(Array<Enemy> targets);
	
	public final void registerToLevel(Level level, int x, int y)
	{
		this.level = level;
		this.tile = y * level.getWidth() + x;
		this.pos.x = x * 128 + texWidth;
		this.pos.y = y * 128 + texHeight;
	}
	
	public void update()
	{
		// remove any targets that left range
		targets.clear();
		findTargets(targets);
		
		// save variable so result are same
		long ms = TimeUtils.millis();
		
		// attack the target(s) after cooldown
		if (ms - lastShotFired >= getCooldownTime())
		{
			lastShotFired = ms;
			
			/** 
			 * Attack an enemy in the <code>targets</code> array.
			 * If the enemy is dead, remove them from the map.
			 * The <code>targets</code> array is cleared every update,
			 * so its reference is destroyed.
			 */
			for (Enemy e : targets)
				if (e.dealDamage(getDamage()) <= 0)
					level.removeEnemy(e);
		}
	}
	
	public void draw(SpriteBatch batch)
	{
		ShapeRenderer shapeRenderer = TowerDefense.shapeRenderer;
		batch.draw(texture, pos.x - texWidth / 2.0f, pos.y - texHeight / 2.0f);
		
		// draw the line(s) to the target(s) (if applicable)
		if (DEBUG_DRAWTARGET)
			for (Enemy e : targets)
			{
				shapeRenderer.setColor(1.0f, 1.0f, 0.0f, 0.5f);
				shapeRenderer.line(pos, e.getPosition());
			}
	}
	
	public Level getMap()
	{
		return level;
	}

	public int getTile()
	{
		return tile;
	}
	
	public Vector2 getPos()
	{
		return pos;
	}

	public int getDamage()
	{
		return damage;
	}

	public void setDamage(int damage)
	{
		this.damage = damage;
	}

	public long getCooldownTime()
	{
		return cooldownTime;
	}

	public void setCooldownTime(long cooldown)
	{
		this.cooldownTime = cooldown;
	}
}