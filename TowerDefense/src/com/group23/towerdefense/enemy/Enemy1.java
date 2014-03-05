package com.group23.towerdefense.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group23.towerdefense.level.Level;

public class Enemy1 extends Enemy
{
	public static Texture texture;
	
	public Enemy1(Level map) 
	{
		super(map);
	}

	protected void setBaseStats() 
	{
		hp = maxHP = 100;
		moveSpeed = 128;
		texWidth = 64;
		texHeight = 64;
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture, pos.x - texWidth / 2, pos.y - texHeight / 2);
		super.draw(batch);
	}
}
