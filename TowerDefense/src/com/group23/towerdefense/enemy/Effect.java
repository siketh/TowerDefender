package com.group23.towerdefense.enemy;

public class Effect 
{
	private float length;
	private float duration;
	private String type;
	
	public Effect(float length, float duration, String type)
	{
		this.setLength(length);
		this.setDuration(duration);
		this.setType(type);
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
