package com.group23.towerdefense.enemy;

public class Debuff 
{
	private float strength;
	private float duration;
	private DebuffType type;
	
	public Debuff(float Strength, float duration, DebuffType type)
	{
		this.setStrength(Strength);
		this.setDuration(duration);
		this.setType(type);
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
}
