package com.group23.towerdefense.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

public class CircleGroup extends Group
{
	private float radius;

	public float getRadius()
	{
		return radius;
	}

	public void setRadius(float radius)
	{
		this.radius = radius;
	}

	protected void pack()
	{
		SnapshotArray<Actor> actors = getChildren();
		int i = 0;
		double increment = (2.0f * Math.PI) / actors.size;
		for (Actor actor : actors)
		{
			double v = increment * i++;
			double x = Math.cos(v * i) * radius;
			double y = Math.sin(v * i) * radius;
			actor.setPosition((float) x, (float) y);
		}
	}
}