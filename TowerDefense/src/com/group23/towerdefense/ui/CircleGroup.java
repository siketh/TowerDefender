package com.group23.towerdefense.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class CircleGroup extends Group
{
	static double xOffset = -32;
	static double yOffset = -32;
	
	private float radius = 1.0f;
	
	public CircleGroup()
	{
		
	}
	
	public CircleGroup(float radius)
	{
		this.radius = radius;
	}

	public float getRadius()
	{
		return radius;
	}

	public void setRadius(float radius)
	{
		this.radius = radius;
	}

	public void pack()
	{
		Array<Actor> actors = getChildren();
		double increment = 2.0 * Math.PI / (double) actors.size;
		for (int i = 0; i < actors.size; i++)
		{
			double v = increment * i;
			double x = Math.sin(v) * radius + xOffset;
			double y = -Math.cos(v) * radius + yOffset;
			
			Actor actor = actors.get(i);
			actor.setPosition((float) x, (float) y);
		}
	}
}