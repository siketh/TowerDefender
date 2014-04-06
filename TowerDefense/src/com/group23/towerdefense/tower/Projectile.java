package com.group23.towerdefense.tower;

import com.badlogic.gdx.math.Vector2;
import com.group23.towerdefense.SpriteObject;
import com.group23.towerdefense.enemy.Enemy;

public class Projectile extends SpriteObject
{
	private Vector2 pos = new Vector2();
	private int speed;
	private Enemy target;
	
	public Projectile(float x, float y, Enemy target, String name, int speed)
	{
		pos.x = x;
		pos.y = y;
		this.target = target;
		setTexture(name);
		this.speed = speed;
	}

	//Update function
	//Returns 0 if reached target
	//Returns 1 if target died
	//Returns 2 if fine
	public int act(float dt)
	{
		if(target == null)
			return 1;
		
		Vector2 distance = new Vector2(target.getPosition().x, target.getPosition().y);
		distance.sub(pos);
		distance.nor();
		pos.x += distance.x * speed * dt;
		pos.y += distance.y * speed * dt;
		setRotation(distance);
		setTexturePosition(pos);
		
		if(pos.dst(target.getPosition()) <= 200)
		{
			return 0;
		}
		speed = (int)((double)speed * 1.1);
		
		return 2;
	}
	
	public Enemy getTarget()
	{
		return target;
	}
}
