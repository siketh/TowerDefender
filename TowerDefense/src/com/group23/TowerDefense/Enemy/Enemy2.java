package com.group23.TowerDefense.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group23.TowerDefense.Level.Level;

public class Enemy2 extends Enemy
{
	public static Texture texture;
	
	public Enemy2(Level map) 
	{
		super(map);
	}

	protected void setBaseStats() 
	{
		hp = maxHP = 200;
		moveSpeed = 64;
		texWidth = 64;
		texHeight = 64;
		goldValue = 30;
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture, pos.x - texWidth / 2, pos.y - texHeight / 2);
		super.draw(batch);
	}
}
