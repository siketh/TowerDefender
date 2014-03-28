package com.group23.towerdefense.tower;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.group23.towerdefense.TextureObject;
import com.group23.towerdefense.TowerDefense;
import com.group23.towerdefense.enemy.Enemy;
import com.group23.towerdefense.level.Level;

public abstract class Tower extends TextureObject
{
	public static boolean DEBUG_DRAWRANGE = true;
	public static boolean DEBUG_DRAWTARGET = true;

	// Tile index position in the map array
	private int tile;

	// World coordinate position
	private Vector2 pos = new Vector2();

	// Time in between shots
	private long lastShotFired = TimeUtils.millis();

	private Level level;
	private Array<Enemy> targets = new Array<Enemy>();

	private int damage = 0;
	private long cooldownTime = 0L;
	private int goldCost = 0;
	private boolean isSelected = false;

	/**
	 * Finds targets to attack and adds them to the input <code>Array</code>.
	 * Place algorithms to search for target enemies in your overridden
	 * implementation.
	 * 
	 * @param targets
	 *            The <code>Array</code> containing the target enemies
	 */
	abstract void findTargets(Array<Enemy> targets);

	public final void registerToLevel(Level level, int x, int y)
	{
		this.level = level;
		this.tile = y * level.getWidth() + x;
		this.pos.x = (x * Level.TILE_WIDTH) + (Level.TILE_WIDTH / 2);
		this.pos.y = (y * Level.TILE_HEIGHT) + (Level.TILE_HEIGHT / 2);
		updateTexturePosition(pos);
	}

	public void update()
	{
		// remove any targets that left range
		findTargets(targets);

		// save variable so result are same
		long ms = TimeUtils.millis();

		// attack the target(s) after cooldown
		if (ms - lastShotFired >= getCooldownTime())
		{
			lastShotFired = ms;

			/**
			 * Attack an enemy in the <code>targets</code> array. If the enemy
			 * is dead, remove them from the map and the <code>targets</code>
			 * array.
			 */
			Iterator<Enemy> iter = targets.iterator();
			while (iter.hasNext())
			{
				Enemy e = iter.next();
				e.dealDamage(getDamage());
				if (!e.isAlive())
				{
					e.rewardGold();
					level.removeEnemy(e);
					iter.remove();
				}
			}
		}
	}

	public void draw(SpriteBatch batch)
	{
		super.draw(batch);

		ShapeRenderer shapeRenderer = TowerDefense.shapeRenderer;
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

	public int getGoldCost()
	{
		return goldCost;
	}

	public void setGoldCost(int goldCost)
	{
		this.goldCost = goldCost;
	}

	public boolean isSelected()
	{
		return isSelected;
	}

	public void setSelected(boolean isSelected)
	{
		this.isSelected = isSelected;
	}

	@Override
	public void setTexture(String filename)
	{
		super.setTexture(filename);
		updateTexturePosition(pos);
	}

	public Vector2 getPosition()
	{
		return pos;
	}
}