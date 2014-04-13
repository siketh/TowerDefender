package com.group23.towerdefense.tower;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.group23.towerdefense.SpriteObject;
import com.group23.towerdefense.enemy.Enemy;

public class Projectile extends SpriteObject
{
	private Vector2 pos = new Vector2();
	private int speed;
	private int numBounces;
	private Enemy target;
	private ArrayList<Enemy> pastTargets;
	
	public ArrayList<Enemy> getPastTargets() {
		return pastTargets;
	}

	public void setPastTargets(ArrayList<Enemy> pastTargets) {
		this.pastTargets = pastTargets;
	}

	public Projectile(float x, float y, Enemy target, String name, int speed)
	{
		pastTargets = new ArrayList<Enemy>();
		pos.x = x;
		pos.y = y;
		this.target = target;
		setTexture(name);
		this.speed = speed;
		pastTargets.add(target);
	}
	
	public Projectile(float x, float y, Enemy target, String name, int speed, int numBounces)
	{
		pastTargets = new ArrayList<Enemy>();
		pos.x = x;
		pos.y = y;
		this.target = target;
		setTexture(name);
		this.speed = speed;
		this.setNumBounces(numBounces);
		pastTargets.add(target);
	}
	
	public Projectile(float x, float y, Enemy target, String name, int speed, int numBounces, ArrayList<Enemy> targets)
	{
		this.pastTargets = targets;
		pos.x = x;
		pos.y = y;
		this.target = target;
		setTexture(name);
		this.speed = speed;
		this.setNumBounces(numBounces);
		pastTargets.add(target);
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
		
		if(pos.dst(target.getPosition()) <= 30)
		{
			return 0;
		}
		speed = (int)((double)speed * 1.1);
		
		return 2;
	}
	
	//Returns false if the target has not been in the array before
	public boolean checkTarget(Enemy x)
	{
		for(Enemy e: pastTargets)
		{
			if(e == x)
				return true;
		}
		return false;
	}
	
	public Enemy getTarget()
	{
		return target;
	}

	public int getNumBounces() {
		return numBounces;
	}
	
	public Vector2 getPosition(){
		return pos;
	}

	public void setNumBounces(int numBounces) {
		this.numBounces = numBounces;
	}
}
