package com.group23.towerdefense.tower;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.group23.towerdefense.TowerDefense;
import com.group23.towerdefense.enemy.Enemy;

public abstract class CircularRangeTower extends Tower
{
	private float range;
	
	@Override
	protected void findTargets(Array<Enemy> targets)
	{
		Array<Enemy> enemies = getMap().getEnemies();
		
		// must be at least one enemy
		if (enemies.size > 0)
		{
			final Vector2 pos = getPos();
			
			// find all enemies within range
			for (Enemy e : enemies)
				if (pos.dst(e.getPosition()) <= getRange())
					targets.add(e);
		}
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{
		super.draw(batch);
		
		ShapeRenderer shapeRenderer = TowerDefense.shapeRenderer;
		Vector2 pos = getPos();
		
		// draw the radius of the range
		if (DEBUG_DRAWRANGE)
		{
			shapeRenderer.setColor(1.0f, 0.0f, 0.0f, 0.5f);
			shapeRenderer.circle(pos.x, pos.y, getRange());
		}
	}

	public float getRange()
	{
		return range;
	}

	public void setRange(float range)
	{
		this.range = range;
	}
}
