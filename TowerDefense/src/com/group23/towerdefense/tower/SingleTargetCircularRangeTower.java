package com.group23.towerdefense.tower;

import java.util.Comparator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.group23.towerdefense.enemy.Enemy;

public abstract class SingleTargetCircularRangeTower extends CircularRangeTower
{
	@Override
	protected void findTargets(Array<Enemy> targets)
	{
		/** Already has a target, do nothing */
		if (targets.size == 1)
		{
			if (!isInRange(targets.get(0)))
				targets.clear();
			else
				return;
		}

		super.findTargets(targets);

		if (targets.size > 0)
		{
			final Vector2 pos = getPosition();

			// sort the enemies in range by closest first
			targets.sort(new Comparator<Enemy>() {
				@Override
				public int compare(Enemy e1, Enemy e2)
				{
					int dst1 = (int) pos.dst(e1.getPosition());
					int dst2 = (int) pos.dst(e2.getPosition());
					return dst1 - dst2;
				}
			});

			Enemy closestEnemy = targets.get(0);
			targets.clear();
			targets.add(closestEnemy);
		}
	}

	private boolean isInRange(Enemy enemy)
	{
		return getPosition().dst(enemy.getPosition()) <= getRange();
	}
}
