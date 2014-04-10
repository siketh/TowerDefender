package com.group23.towerdefense.tower;

public class Upgrade 
{
	private String name;
	private String texName;
	
	private int cost;
	private int levels;
	private int currentLevel;
	private Tower parent;
	private int id;
	
	
	public Upgrade(Tower parent)
	{
		this.parent = parent;
		currentLevel = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public Tower getParent() {
		return parent;
	}

	public void setParent(Tower parent) {
		this.parent = parent;
	}

	public String getTexName() {
		return texName;
	}

	public void setTexName(String texName) {
		this.texName = texName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void press()
	{
		if(parent.getMap().getGold() >= getCost() && currentLevel < levels)
		{
			parent.getMap().subtractGold(getCost());
			parent.performUpgrades(this);
			incrementLevel();
		}
	}

	public void incrementLevel() 
	{
		currentLevel++;
		
	}
	
}
