package com.group23.TowerDefense.Tower;

import java.util.Comparator;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.group23.TowerDefense.TowerDefense;
import com.group23.TowerDefense.Enemy.Enemy;
import com.group23.TowerDefense.Level.Level;

public class Tower
{
	public  static Texture texture      = null;
	private static final int texWidth  = 64;
	private static final int texHeight = 64;
	
	public static boolean DEBUG_DRAWRANGE = true;
	public static boolean DEBUG_DRAWTARGET = true;
	
	// Tile index position in the map array
	private int tile;
	
	// World coordinate of tower
	private Vector2 pos;
	
	// Time in between shots
	private long lastShotFired;
	
	private Level map;
	private Array<Enemy> targets;
	private Array<Enemy> inRange;
	
	public int getDamage() { return 2; }
	public float getRange() { return 250.0f; }
	public long getCooldownTime() { return 100; }
	
	public Tower(Level map, int x, int y) 
	{
		this.tile = y * map.getWidth() + x;
		
		this.map     = map;
		this.targets = new Array<Enemy>();
		this.inRange = new Array<Enemy>();
		
		this.lastShotFired = TimeUtils.millis();
		
		// initialize position
		this.pos = new Vector2();
		this.pos.x = x * 128 + 64;
		this.pos.y = y * 128 + 64;
	}
	
	public void update(float dt)
	{
		Iterator<Enemy> iter;
		
		// remove any targets that left range
		iter = targets.iterator();
		while (iter.hasNext())
			if (pos.dst(iter.next().getPosition()) > getRange())
				iter.remove();
		
		// see if any new targets have entered range
		// DEBUG all in range enemies are targets
		targets = findInRangeTargets();
		
		// save variable so result are same
		long ms = TimeUtils.millis();
		
		// attack the target(s) after cooldown
		if (ms - lastShotFired >= getCooldownTime())
		{
			lastShotFired = ms;
			
			iter = targets.iterator();
			while (iter.hasNext())
			{
				Enemy e = iter.next();
				if (e.dealDamage(getDamage()) <= 0)
				{
					iter.remove();
					map.removeEnemy(e);
				}
			}
		}
	}
	
	public void draw(SpriteBatch batch)
	{
		ShapeRenderer shapeRenderer = TowerDefense.shapeRenderer;
		batch.draw(texture, pos.x - texWidth / 2.0f, pos.y - texHeight / 2.0f);
		
		// draw the radius of the range
		if (DEBUG_DRAWRANGE)
		{
			shapeRenderer.setColor(1.0f, 0.0f, 0.0f, 0.5f);
			shapeRenderer.circle(pos.x, pos.y, getRange());
		}
		
		// draw the line(s) to the target(s) (if applicable)
		if (DEBUG_DRAWTARGET)
			for (Enemy e : targets)
			{
				shapeRenderer.setColor(1.0f, 1.0f, 0.0f, 0.5f);
				shapeRenderer.line(pos, e.getPosition());
			}
	}
	
	/**
	 * Finds the closest Enemy in the map to the tower
	 * @return The closest Enemy to the tower or null is no target found
	 */
	private Enemy findClosestTarget()
	{
		Array<Enemy> inRange = findInRangeTargets();
		return inRange.size != 0 ? inRange.get(0) : null;
	}
	
	/**
	 * Finds an array of enemies within range
	 * @return An array of enemies, sorted by closest first
	 */
	private Array<Enemy> findInRangeTargets()
	{
		Array<Enemy> enemies = map.getEnemies();
		inRange.clear();
		
		// must be at least one enemy
		if (enemies.size > 0)
		{
			// find all enemies within range
			for (Enemy e : enemies)
				if (pos.dst(e.getPosition()) <= getRange())
					inRange.add(e);
			
			// sort the enemies in range by closest first
			inRange.sort(new Comparator<Enemy>() {
				public int compare(Enemy e1, Enemy e2) {
					int dst1 = (int) pos.dst(e1.getPosition());
					int dst2 = (int) pos.dst(e2.getPosition());
					return dst1 - dst2;
				}
			});
		}

		return inRange;
	}

	public int getTile()
	{
		return tile;
	}
}