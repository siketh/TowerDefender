package com.group23.towerdefense.tower;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.group23.towerdefense.enemy.Enemy;

public abstract class CircularRangeTower extends Tower
{
	private float range;
	protected int maxTargets;
	
	public float getRange()
	{
		return range;
	}

	public void setRange(float range)
	{
		this.range = range;
	}
	
	public int getMaxTargets()
	{
		return maxTargets;
	}
	
	public void setMaxTargets(int numTargets)
	{
		maxTargets = numTargets;
	}
	
	@Override
	protected void findTargets(Array<Enemy> targets)
	{
		targets.clear();
		Array<Enemy> enemies = getMap().getEnemies();
		
		// must be at least one enemy
		if (enemies.size > 0)
		{
			final Vector2 pos = getPosition();
			
			int numTargets = 0;
			// find all enemies within range
			for (Enemy e : enemies)
			{
				if (pos.dst(e.getPosition()) <= getRange())
				{
					targets.add(e);
					numTargets++;
				}
				if(numTargets >= maxTargets)
					break;
			}
		}
	}
	
	@Override
	public void drawShapes(ShapeRenderer shapeRenderer)
	{
		super.drawShapes(shapeRenderer);
		
		// draw the radius of the range
		if (DEBUG_DRAWRANGE)
		{
			Vector2 pos = getPosition();
			
			shapeRenderer.setColor(1.0f, 0.0f, 0.0f, 0.5f);
			shapeRenderer.circle(pos.x, pos.y, getRange());
		}
	}
}
