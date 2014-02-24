package com.group23.TowerDefense;

import java.util.Comparator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Tower
{
	public static Texture texture       = null;
	private static final int texWidth  = 64;
	private static final int texHeight = 64;
	
	public static boolean DEBUG_DRAWRANGE = true;
	public static boolean DEBUG_DRAWTARGET = true;
	private int damage;
	
	// Tile index position in the map array
	private int tile;
	
	// World coordinate of tower
	private Vector2 pos;
	
	// Range the tower has to acquire an an enemy
	private float range;
	
	// Time in between shots
	private long cooldown;
	private long lastShotFired;
	
	private Level map;
	private Enemy target;
	private Array<Enemy> inRange;
	
	public Tower(Level map, int x, int y) 
	{
		this.tile = y * map.getWidth() + x;
		
		this.range  = 250.0f;
		this.damage = 2;
		
		this.map     = map;
		this.target  = null;
		this.inRange = new Array<Enemy>();
		
		this.cooldown = 100;
		this.lastShotFired = TimeUtils.millis();
		
		// initialize position
		this.pos = new Vector2();
		this.pos.x = x * 128 + 64;
		this.pos.y = y * 128 + 64;
	}
	
	public void update(float dt)
	{
		// find a target if no target available or if current target exits range
		if (target == null || pos.dst(target.getPosition()) > range)
			target = findTarget();
		else if(target != null)
		{
			// save variable so result are same
			long ms = TimeUtils.millis();
			
			// attack the enemy after cooldown
			if (ms - lastShotFired >= cooldown)
			{
				lastShotFired = ms;
				
				if (target.dealDamage(damage) <= 0)
				{
					map.removeEnemy(target);
					target = findTarget();
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
			shapeRenderer.circle(pos.x, pos.y, range);
		}
		
		// draw the line to the target (if applicable)
		if (DEBUG_DRAWTARGET && target != null)
		{
			shapeRenderer.setColor(1.0f, 1.0f, 0.0f, 0.5f);
			shapeRenderer.line(pos, target.getPosition());
		}
	}
	
	/**
	 * Finds the closest Enemy in the map to the tower
	 * @return The closest Enemy to the tower or null is no target found
	 */
	private Enemy findTarget()
	{
		Enemy target = null;
		
		Array<Enemy> enemies = map.getEnemies();
		inRange.clear();
		
		// must be at least one enemy
		if (enemies.size > 0)
		{
			// find all enemies within range
			for (Enemy e : enemies)
				if (pos.dst(e.getPosition()) <= range)
					inRange.add(e);
			
			// must be at least one enemy in range
			if (inRange.size == 1)
				target = inRange.get(0);
			else if (inRange.size > 1)
			{
				// sort the enemies in range by closest first
				inRange.sort(new Comparator<Enemy>() {
					public int compare(Enemy e1, Enemy e2) {
						int dst1 = (int) pos.dst(e1.getPosition());
						int dst2 = (int) pos.dst(e2.getPosition());
						return dst1 - dst2;
					}
				});
				
				// set the target to the closest target
				target = inRange.get(0);
			}
		}

		return target;
	}

	public int getTile()
	{
		return tile;
	}
}