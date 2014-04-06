package com.group23.towerdefense.tower;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.group23.towerdefense.Level;
import com.group23.towerdefense.TextureObject;
import com.group23.towerdefense.enemy.Enemy;

public abstract class Tower extends TextureObject
{

	public static abstract class Generator
	{
		public abstract String getName();

		public abstract int getGoldCost();

		public abstract Texture getTexture();

		protected abstract Tower getTower();

		public Tower generate()
		{
			Tower tower = getTower();
			tower.setTexture(getTexture());
			tower.setGoldCost(getGoldCost());

			return tower;
		}
	}

	/**
	 * The the defining array to generate towers. To add a a new tower to the
	 * tower bar, create a new anonymous Generator class and implement its
	 * methods. It will then be dynamically added, with the resulting created
	 * tower using the same texture as <code>Tower.Generator.getTexture()</code>
	 * .
	 */
	private static Generator[] towerGenerators = new Generator[]
		{ new Generator()
		{
			private Texture texture = com.group23.towerdefense.ResourceManager
					.loadTexture("tower00.png");

			@Override
			public String getName()
			{
				return "Arrow Tower";
			}

			@Override
			public int getGoldCost()
			{
				return 100;
			}

			@Override
			protected Tower getTower()
			{
				return new ArrowTower();
			}

			@Override
			public Texture getTexture()
			{
				return texture;
			}
		},
		
		new Generator()
		{
			private Texture texture = com.group23.towerdefense.ResourceManager
					.loadTexture("tower01.png");

			@Override
			public String getName()
			{
				return "Slow Tower";
			}

			@Override
			public int getGoldCost()
			{
				return 150;
			}

			@Override
			public Texture getTexture()
			{
				return texture;
			}

			@Override
			protected Tower getTower()
			{
				return new SlowTower();
			}
		}, 
		
		
		
		};

	public static Generator[] getTowerGenerators()
	{
		return towerGenerators;
	}

	public static boolean DEBUG_DRAWRANGE = true;
	public static boolean DEBUG_DRAWTARGET = true;

	// Tile index position in the map array
	private int tile;

	// World coordinate position
	private Vector2 pos = new Vector2();

	// Time in between shots
	private long lastShotFired = TimeUtils.millis();

	private Level level;
	private Array<Enemy> targets = new Array<Enemy>();
	private Array<Upgrade> appliedUpgrades = new Array<Upgrade>();
	protected ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
	
	public ArrayList<Upgrade> getUpgrades() 
	{
		return upgrades;
	}

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private String projectileType;
	private int projectileSpeed;

	private int damage = 0;
	private long cooldownTime = 0L;
	private int goldCost = 0;
	private long lastMS;

	/**
	 * Finds targets to attack and adds them to the input <code>Array</code>.
	 * Place algorithms to search for target enemies in your overridden
	 * implementation.
	 * 
	 * @param targets
	 *            The <code>Array</code> containing the target enemies
	 */
	abstract void findTargets(Array<Enemy> targets);

	abstract void addUpgrades();
	
	public final void registerToLevel(Level level, int x, int y)
	{
		this.level = level;
		this.tile = y * level.getWidth() + x;
		this.pos.x = (x * Level.TILE_WIDTH) + (Level.TILE_WIDTH / 2);
		this.pos.y = (y * Level.TILE_HEIGHT) + (Level.TILE_HEIGHT / 2);
		updateTexturePosition(pos);
	}

	public void act()
	{
		// remove any targets that left range
		findTargets(targets);

		// save variable so result are same
		long ms = TimeUtils.millis();
		
		// attack the target(s) after cooldown
		if (ms - lastShotFired >= getCooldownTime())
		{
			lastShotFired = ms;

			/**
			 * Attack an enemy in the <code>targets</code> array. If the enemy
			 * is dead, remove them from the map and the <code>targets</code>
			 * array.
			 */
			Iterator<Enemy> iter = targets.iterator();
			while (iter.hasNext())
			{
				Enemy e = iter.next();
				projectiles.add(new Projectile(pos.x, pos.y, e, projectileType, projectileSpeed));
			}
		}
		
		
		for(int t = projectiles.size() - 1; t >= 0; t--)
		{
			int i = projectiles.get(t).act(((float)(ms - lastMS) / (float)1000));
			if(i == 0)
			{
				projectiles.get(t).getTarget().dealDamage(getDamage());
				causeEffect(projectiles.get(t).getTarget());
				if (!projectiles.get(t).getTarget().isAlive())
				{
					projectiles.get(t).getTarget().rewardGold();
					level.removeEnemy(projectiles.get(t).getTarget());
					for(int j = 0; j < targets.size; j++)
					{
						if(targets.get(j) == projectiles.get(i).getTarget())
							targets.removeIndex(j);
					}
					
				}
				projectiles.remove(t);
			}
			else if(i == 1)
			{
				projectiles.remove(t);
			}
		}
		lastMS = ms;
	}

	// A class that causes the effect to the enemy
	protected void causeEffect(Enemy e)
	{

	}

	public void drawShapes(ShapeRenderer shapeRenderer)
	{
		// draw the line(s) to the target(s) (if applicable)
		if (DEBUG_DRAWTARGET)
			for (Enemy e : targets)
			{
				shapeRenderer.setColor(1.0f, 1.0f, 0.0f, 0.5f);
				shapeRenderer.line(pos, e.getPosition());
			}
	}
	
	abstract void performUpgrades(Upgrade caller);

	public Level getMap()
	{
		return level;
	}

	public int getTile()
	{
		return tile;
	}

	public int getDamage()
	{
		return damage;
	}

	public void setDamage(int damage)
	{
		this.damage = damage;
	}

	public long getCooldownTime()
	{
		return cooldownTime;
	}

	public void setCooldownTime(long cooldown)
	{
		this.cooldownTime = cooldown;
	}

	public int getGoldCost()
	{
		return goldCost;
	}

	public void setGoldCost(int goldCost)
	{
		this.goldCost = goldCost;
	}

	@Override
	public void setTexture(String filename)
	{
		super.setTexture(filename);
		updateTexturePosition(pos);
	}

	public Vector2 getPosition()
	{
		return pos;
	}

	public boolean hasAppliedUpgrade(Upgrade upgrade)
	{
		for (Upgrade u : appliedUpgrades)
			if (u.equals(upgrade))
				return true;
		return false;
	}

	public String getProjectileType() {
		return projectileType;
	}

	public void setProjectileType(String projectileType) {
		this.projectileType = projectileType;
	}
	
	public void setProjectileSpeed(int speed)
	{
		projectileSpeed = speed;
	}
	
	public void draw(Batch batch)
	{
		super.draw(batch);
		for(Projectile p: projectiles)
		{
			p.draw(batch);
		}
		
	}
}