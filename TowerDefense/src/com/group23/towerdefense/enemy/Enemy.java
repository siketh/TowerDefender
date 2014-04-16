package com.group23.towerdefense.enemy;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.group23.towerdefense.Dir;
import com.group23.towerdefense.Level;
import com.group23.towerdefense.TextureObject;
import com.group23.towerdefense.TowerDefense;

public abstract class Enemy extends TextureObject
{
	public static BitmapFont font = null;

	protected int texWidth, texHeight;
	protected int hp, maxHP; // Stores current hp of enemy
	protected int moveSpeed; // Stores movement speed of enemy
	protected int baseMoveSpeed; //Stores base move speed of the enemy
	protected Dir direction; // Stores direction of enemy
	protected Vector2 pos; // Store pixel coordinates of enemy
	protected Level path; // Points to level
	protected int curTile; // Stores current tile index of enemy
	protected float distTraveled; // Stores distance traveled since last new
									// tile
	protected double scaling; // Stores how much scaling the enemy needs from
								// the default
	protected int goldValue; // Stores the enemies value in gold
	protected int livesValue; // The amount of lives the enemy is worth
	protected int armor;
	protected int healthRegen; // Health Regen, defaults to 0
	protected float timeToRegen;
	protected float healReduction;
	
	protected ArrayList<Debuff>  debuffs;	//Stores all the effects on the enemy
	protected boolean isBurning;	//Stores whether the enemy is on fire
	
	private boolean isAlive = true;
	private Color color;

	// Constructor for enemy
	public Enemy(Level map, double scale)
	{
		debuffs = new ArrayList<Debuff>();
		healthRegen = 0;
		timeToRegen = 1;
		setBaseStats();
		if(TowerDefense.difficulty == 0)
		{
			hp *= .75;
			maxHP *= .75;
		}
		else if(TowerDefense.difficulty == 2)
		{
			hp *= 1.25;
			maxHP *= 1.25;
		}
		
		baseMoveSpeed = moveSpeed;
		scaling = scale;
		maxHP *= scale;
		hp *= scale;
		isBurning = false;
		distTraveled = 0;
		path = map;
		direction = path.getStartDir(); // Pulls starting direction from map

		// Converts from tile coordinates to pixel coordinates and centers
		// enemy in tile and offsets for image height and width
		pos = new Vector2();

		color = new Color(Color.WHITE);

		// Calculates tiles index based on width of tile map
		curTile = path.getStart();

		pos.x = (curTile % path.getWidth()) * 128 + 64;
		pos.y = (curTile / path.getWidth()) * 128 + 64;
	}

	// Put base stats of the monster here
	abstract protected void setBaseStats();

	//Spreads the burn if it can find an enemy not on fire in range
	protected void burnSpread(Debuff d)
	{
		Array<Enemy> e = path.getEnemies();
		for(int i = 0; i < e.size; i++)
		{
			if(e.get(i).isBurning == false && getPosition().dst(e.get(i).getPosition()) <= d.getRange())
			{
				System.out.println(i);
				e.get(i).addDebuff(new Debuff(d.getStrength(), d.getBaseDuration() * .66f, d.getType(), d.getCooldown(), d.getRange()));
				e.get(i).isBurning = true;
				return;
			}
		}
	}
	
	//Calculates the movespeed of the enemy for the segment
	protected void calcMoveSpeed(float dt)
	{
		isBurning = false;
		float speedModifier = 1;
		healReduction = 1;
		for(Debuff e: debuffs)
		{
			 e.decreaseDuration(dt); 
			 switch(e.getType())
			 {
				case Burn:
					if(e.tick(dt))
					{
						isBurning = true;
						burnSpread(e);
						if(dealDamage((int)(e.getStrength())) <= 1)
							hp = 1;
					}
					break;
				case HealRed:
					if(e.getStrength() < healReduction)
						healReduction = e.getStrength();
					break;
				case Poison:
					if(e.tick(dt))
					{
						if(dealDamage((int)(e.getStrength())) <= 1)
							hp = 1;
					}
					break;
				case Slow: 
					if(e.getStrength() < speedModifier)
						speedModifier = e.getStrength();
					break;
				default:
					break;
			 }
		}
		
		for(int i = debuffs.size() - 1; i >= 0; i--)
		{
			if(debuffs.get(i).getDuration() <= 0)
				debuffs.remove(i);
		}
		
		moveSpeed = (int)(baseMoveSpeed * speedModifier);
	}
	
	// Returns true if reached the end
	public boolean act(float dt)
	{
		calcMoveSpeed(dt);
		timeToRegen -= dt;
		// Handles Health Regeneration
		if (timeToRegen <= 0)
		{
			hp += healthRegen * scaling * healReduction;
			if (hp > maxHP)
				hp = maxHP;
			timeToRegen = 1;
		}
		// Updates units position based on change in time and unit's movement
		// speed
		// 0.71 is for diagonal cases to match movement speed to horizontal and
		// vertical cases
		switch (direction)
		{
		case N:
			pos.y -= moveSpeed * dt;
			break;
		case NE:
			pos.x += moveSpeed * 0.71 * dt;
			pos.y -= moveSpeed * 0.71 * dt;
			break;
		case E:
			pos.x += moveSpeed * dt;
			break;
		case SE:
			pos.x += moveSpeed * 0.71 * dt;
			pos.y += moveSpeed * 0.71 * dt;
			break;
		case S:
			pos.y += moveSpeed * dt;
			break;
		case SW:
			pos.x -= moveSpeed * 0.71 * dt;
			pos.y += moveSpeed * 0.71 * dt;
			break;
		case W:
			pos.x -= moveSpeed * dt;
			break;
		case NW:
			pos.x -= moveSpeed * 0.71 * dt;
			pos.y -= moveSpeed * 0.71 * dt;
			break;
		case End:
			return true;
		default:
			break;
		}

		// Even cases are diagonal cases
		if (direction == Dir.NE || direction == Dir.SE || direction == Dir.SW
				|| direction == Dir.NW)
			distTraveled += moveSpeed * 0.71 * dt;
		// Odd cases are horizontal and vertical cases
		else
			distTraveled += moveSpeed * dt;

		// If the enemy has moved across one tile, center it in the next tile
		// and update it's current tile
		// Add or subtract path.getWidth() for moving up or down a row
		// Add or subtract 1 for moving right or left
		if (distTraveled >= 128)
		{
			switch (direction)
			{
			case N:
				curTile -= path.getWidth();
				break;
			case NE:
				curTile = curTile - path.getWidth() + 1;
				break;
			case E:
				curTile += 1;
				break;
			case SE:
				curTile = curTile + path.getWidth() + 1;
				break;
			case S:
				curTile += path.getWidth();
				break;
			case SW:
				curTile = curTile + path.getWidth() - 1;
				break;
			case W:
				curTile -= 1;
				break;
			case NW:
				curTile = curTile - path.getWidth() - 1;
				break;
			default:
				break;
			}

			// Centers enemy at a new tile
			pos.x = (curTile % path.getWidth()) * 128 + 64;
			pos.y = (curTile / path.getWidth()) * 128 + 64;
			distTraveled = 0;
		}

		// Gets enemy a new direction
		direction = path.getDirection(curTile);

		if (path.getEnd() == curTile)
			path.enemyReachedEnd(this);

		updateTexturePosition(pos);

		return false;
	}

	public void draw(Batch batch)
	{
		super.draw(batch);

		// draw health
		float percent = (float) Math.floor((float) hp / maxHP * 100.0f);
		if(percent > 100f)
			percent = 100f;
		font.setScale(2.0f);
		font.setColor(transitionColor(Color.GREEN, Color.RED, (float) hp
				/ maxHP, color));
		font.draw(batch, String.format("%d%%", (int) percent), pos.x - texWidth
				/ 2, pos.y - texHeight / 2);
	}

	public Dir getDir()
	{
		return direction;
	}

	/**
	 * Damages the enemy
	 * 
	 * @param damage
	 * @return The new enemy's health
	 */
	public int dealDamage(int damage)
	{
		if(armor > damage)
			return hp -= 1;
		return hp -= (damage - armor);
	}
	
	public int dealDamage(int damage, int armorPen)
	{
		if(armorPen > armor)
			return hp -= damage;
		else if(armor > damage)
			return hp -= 1;
		else
		{
			hp = hp - damage + armorPen;
			return hp;
		}
	}

	public Vector2 getPosition()
	{
		return pos;
	}

	/**
	 * Transitions a color from one to another
	 * 
	 * @param from
	 *            Color to transition from
	 * @param to
	 *            Color to transition to
	 * @param mult
	 *            Modifier from color from to color to
	 * @param color
	 *            Color to modify
	 * @return color
	 */
	private Color transitionColor(Color from, Color to, float mult, Color color)
	{
		float inv = 1.0f - mult;

		color.r = (from.r * mult) + (to.r * inv);
		color.g = (from.g * mult) + (to.g * inv);
		color.b = (from.b * mult) + (to.b * inv);

		return color;
	}

	public void rewardGold()
	{
		path.giveGold(goldValue);
		goldValue = 0; // So its not accidentally given twice.
	}

	public int getLives()
	{
		return livesValue;
	}

	public boolean isAlive()
	{
		return isAlive && hp > 0;
	}

	public void setAlive(boolean alive)
	{
		isAlive = alive;
	}

	public double getModifier()
	{
		return scaling;
	}

	public void setModifier(float modifier)
	{
		scaling = (float)modifier;
		hp *= scaling;
		maxHP += scaling;
		
	}
	
	public void addDebuff(Debuff d)
	{
		boolean added = false;
		for(int i = debuffs.size() - 1; i >= 0; i--)
		{
			if(debuffs.get(i).getType() == d.getType())
			{
				switch(d.getType())
				{
				case Burn:
					added = true;
					if(debuffs.get(i).getStrength() < d.getStrength())
					{
						debuffs.get(i).setStrength(d.getStrength());
					}
					if(debuffs.get(i).getDuration() < d.getDuration())
					{
						debuffs.get(i).setDuration(d.getDuration());
						debuffs.get(i).setBaseDuration(d.getDuration());
					}
					break;
				case HealRed:
					added = true;
					if(debuffs.get(i).getStrength() < d.getStrength())
					{
						debuffs.get(i).setStrength(d.getStrength());
					}
					if(debuffs.get(i).getDuration() < d.getDuration())
					{
						debuffs.get(i).setDuration(d.getDuration());
					}
					break;
				case Poison:
					added = true;
					if(debuffs.get(i).getStrength() < d.getStrength())
					{
						debuffs.get(i).setStrength(d.getStrength());
					}
					if(debuffs.get(i).getDuration() < d.getDuration())
					{
						debuffs.get(i).setDuration(d.getDuration());
					}
					break;
				case Slow:
					if(debuffs.get(i).getStrength() < d.getStrength() && debuffs.get(i).getDuration() < d.getDuration())
						debuffs.remove(i);
					break;
				default:
					break;
				}
			}
		}
		if(added == false)
			debuffs.add(d);
	}
}
