package com.group23.towerdefense.enemy;

public class Debuff 
{
	private float strength;
	private float duration, baseDuration;
	private DebuffType type;
	private float cooldown;
	private float timeToTick;
	
	public Debuff(float Strength, float duration, DebuffType type)
	{
		this.setStrength(Strength);
		this.setDuration(duration);
		this.setType(type);
		baseDuration = duration;
		cooldown = 0;
		timeToTick = 0;
	}

	public Debuff(float Strength, float duration, DebuffType type, float cooldown, float timeToTick)
	{
		this.setStrength(Strength);
		this.setDuration(duration);
		this.setType(type);
		baseDuration = duration;
		this.cooldown = cooldown;
		this.timeToTick = timeToTick;
	}
	
	public float getStrength() {
		return strength;
	}

	public void setStrength(float length) {
		this.strength = length;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	public void decreaseDuration(float duration)
	{
		this.duration -= duration;
	}

	public DebuffType getType() {
		return type;
	}

	public void setType(DebuffType type) {
		this.type = type;
	}
	
	public float getBaseDuration()
	{
		return baseDuration;
	}
	
	//Ticks down for stuff such as poison
	//Returns true if the tick happens
	public boolean tick(float duration)
	{
		timeToTick -= duration;
		if(timeToTick <= 0)
		{
			timeToTick = cooldown;
			return true;
		}
			
		return false;
	}
	
	
}
