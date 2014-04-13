package com.group23.towerdefense.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.group23.towerdefense.ResourceManager;

public class CircleGroup extends Group
{
	//static double xOffset = -42;
	static double yOffset = -72;
	
	private float radius = 1.0f;
	private LabelStyle sellStyle;
	
	public CircleGroup()
	{
		sellStyle = new LabelStyle();
		sellStyle.font = ResourceManager.loadDefaultFont();
		sellStyle.fontColor = Color.WHITE;
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


	/*// Sell Button
	
	// Throws a NullPointerException
	//Label sellLabel = new Label(Integer.toString(tower.getGoldCost()), sellStyle);
	//
	//Container LabelContainer = new Container(sellLabel);
	//LabelContainer.setPosition(0.0f, -45.0f);
	//addActor(LabelContainer);	*/		
	public void pack()
	{
		
		Array<Actor> actors = getChildren();
		double increment = 2.0 * Math.PI / (double) actors.size;
		int n = actors.size;
		for (int i = 0; i < n; i++)
		{
			Actor actor = actors.get(i);
			double v = increment * i;
			double x = Math.sin(v) * radius - ((float)actor.getWidth() / 2f);
			double y = -Math.cos(v) * radius + yOffset;
			
			actor.setPosition((float) x, (float) y);
			/*if(strings.get(i) != null)
			{
				Label name = new Label(strings.get(i), sellStyle);
				name.setFontScale(1.5f);
				name.setPosition((float) x , (float) y +80);
				addActor(name);
			}*/
		}
	}
}