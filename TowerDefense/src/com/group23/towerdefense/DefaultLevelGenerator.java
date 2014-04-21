package com.group23.towerdefense;

import com.group23.towerdefense.enemy.Goblin;
import com.group23.towerdefense.enemy.Necromancer;
import com.group23.towerdefense.enemy.Ogre;
import com.group23.towerdefense.enemy.Slime;
import com.group23.towerdefense.enemy.Troll;
import com.group23.towerdefense.spawn.Wave;

public class DefaultLevelGenerator extends Level.Generator
{
	@Override
	public Level getLevel(int level)
	{
		switch (level)
		{
		default:
		case 0: return getLevel1();
		case 1: return getLevel2();
		case 2: return getLevel3();
		case 3: return getLevel4();
		case 4: return getLevel5();
		}
	}
	
	private Level getLevel1()
	{	
		return new Level.Builder()
			.setTiles(new int[]
			{
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 7, 1, 1, 1, 8, 0, 0, 0, 7, 1, 1, 2,
				0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0,
				0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0,
				3, 1, 1, 6, 0, 0, 0, 5, 1, 1, 1, 6, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0			
			})
			.addWave(new Wave.Generator() 
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Goblin.class)
						.addSpawn(400, Goblin.class)
						.addSpawn(700,  Goblin.class)
						.addSpawn(1000, Goblin.class)
						.addSpawn(1500, Ogre.class);
				}
			})
			.addWave(new Wave.Generator() 
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(50,  Slime.class)
						.addSpawn(1000,  Goblin.class)
						.addSpawn(1400, Goblin.class)
						.addSpawn(1800, Goblin.class)
						.addSpawn(2300, Slime.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Troll.class)
						.addSpawn(500,  Troll.class)
						.addSpawn(1000, Ogre.class)
						.addSpawn(1300, Ogre.class)
						.addSpawn(1600, Ogre.class)
						.addSpawn(2000, Necromancer.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Necromancer.class)
						.addSpawn(500, Troll.class, 1.2f)
						.addSpawn(700, Troll.class, 1.2f)
						.addSpawn(1000, Ogre.class, 1.5f)
						.addSpawn(1500, Goblin.class, 1.5f)
						.addSpawn(1750, Goblin.class, 1.5f)
						.addSpawn(2000, Goblin.class, 1.5f)
						.addSpawn(2200, Necromancer.class, 2f);
				}
			})
			.build();
	}
	
	private Level getLevel2()
	{
		return new Level.Builder()
			.setTiles(new int[]
			{
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,		
					3, 1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 4, 0, 7, 1, 8, 0, 7, 1, 8, 0, 0, 0, 0,
					0, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 7, 1, 2,
					0, 0, 4, 0, 4, 0, 4, 0, 4, 0, 5, 1, 6, 0, 0,
					0, 0, 4, 0, 4, 0, 5, 1, 6, 0, 0, 0, 0, 0, 0,
					0, 0, 5, 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0		//Top Right
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Goblin.class)
						.addSpawn(400, Goblin.class)
						.addSpawn(700,  Goblin.class)
						.addSpawn(1000, Goblin.class)
						.addSpawn(1500, Ogre.class);
				}	
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Slime.class)
						.addSpawn(1000, Goblin.class)
						.addSpawn(1200, Goblin.class)
						.addSpawn(1400, Goblin.class)
						.addSpawn(1500, Necromancer.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Slime.class)
						.addSpawn(750, Slime.class)
						.addSpawn(1300, Ogre.class)
						.addSpawn(2500, Goblin.class)
						.addSpawn(3200, Goblin.class)
						.addSpawn(3800, Goblin.class)
						.addSpawn(4500, Necromancer.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Goblin.class, 2f)
						.addSpawn(350, Goblin.class, 2f)
						.addSpawn(600, Goblin.class, 2f)
						.addSpawn(1000, Troll.class, 1.5f)
						.addSpawn(1200, Ogre.class, 1.5f)
						.addSpawn(1400, Ogre.class, 1.5f)
						.addSpawn(1500, Necromancer.class, 2f);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Ogre.class, 1.75f)
					.addSpawn(350, Ogre.class, 1.75f)
					.addSpawn(600, Ogre.class, 1.75f)
					.addSpawn(1000, Troll.class, 1.75f)
					.addSpawn(1200, Slime.class, 1.75f)
					.addSpawn(1400, Slime.class, 1.75f)
					.addSpawn(1500, Necromancer.class, 2f);
				}
			})
			.build();
	}
	
	private Level getLevel3()
	{
		return new Level.Builder()
			.setTiles(new int[]
			{
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,		
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 1, 1, 2,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0,
					0, 0, 0, 7, 1, 1, 1, 1, 1, 1, 1, 6, 0, 0, 0,
					0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					3, 1, 1, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Goblin.class)
						.addSpawn(400, Goblin.class)
						.addSpawn(700,  Goblin.class)
						.addSpawn(1000, Goblin.class)
						.addSpawn(1500, Ogre.class);
				}	
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Slime.class)
						.addSpawn(1000, Goblin.class)
						.addSpawn(1200, Goblin.class)
						.addSpawn(1400, Goblin.class)
						.addSpawn(1500, Necromancer.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Slime.class)
						.addSpawn(750, Slime.class)
						.addSpawn(1300, Ogre.class)
						.addSpawn(2500, Goblin.class)
						.addSpawn(3200, Goblin.class)
						.addSpawn(3800, Goblin.class)
						.addSpawn(4500, Necromancer.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Goblin.class, 2f)
						.addSpawn(350, Goblin.class, 2f)
						.addSpawn(600, Goblin.class, 2f)
						.addSpawn(1000, Troll.class, 1.5f)
						.addSpawn(1200, Ogre.class, 1.5f)
						.addSpawn(1400, Ogre.class, 1.5f)
						.addSpawn(1500, Necromancer.class, 2f);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Ogre.class, 1.75f)
					.addSpawn(350, Ogre.class, 1.75f)
					.addSpawn(600, Ogre.class, 1.75f)
					.addSpawn(1000, Troll.class, 1.75f)
					.addSpawn(1200, Slime.class, 1.75f)
					.addSpawn(1400, Slime.class, 1.75f)
					.addSpawn(1500, Necromancer.class, 2f);
				}
			})
			.build();
	}
	
	private Level getLevel4()
	{
		return new Level.Builder()
			.setTiles(new int[]
			{
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,		
					3, 1, 1, 8, 0, 0, 7, 1, 8, 0, 0, 7, 1, 1, 2,
					0, 0, 0, 4, 0, 0, 4, 0, 4, 0, 0, 4, 0, 0, 0,
					0, 0, 0, 4, 0, 0, 4, 0, 4, 0, 0, 4, 0, 0, 0,
					0, 0, 0, 4, 0, 0, 4, 0, 4, 0, 0, 4, 0, 0, 0,
					0, 0, 0, 4, 0, 0, 4, 0, 4, 0, 0, 4, 0, 0, 0,
					0, 0, 0, 5, 1, 1, 6, 0, 5, 1, 1, 6, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Goblin.class)
						.addSpawn(400, Goblin.class)
						.addSpawn(700,  Goblin.class)
						.addSpawn(1000, Goblin.class)
						.addSpawn(1500, Ogre.class);
				}	
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Slime.class)
						.addSpawn(1000, Goblin.class)
						.addSpawn(1200, Goblin.class)
						.addSpawn(1400, Goblin.class)
						.addSpawn(1500, Necromancer.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Slime.class)
						.addSpawn(750, Slime.class)
						.addSpawn(1300, Ogre.class)
						.addSpawn(2500, Goblin.class)
						.addSpawn(3200, Goblin.class)
						.addSpawn(3800, Goblin.class)
						.addSpawn(4500, Necromancer.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Goblin.class, 2f)
						.addSpawn(350, Goblin.class, 2f)
						.addSpawn(600, Goblin.class, 2f)
						.addSpawn(1000, Troll.class, 1.5f)
						.addSpawn(1200, Ogre.class, 1.5f)
						.addSpawn(1400, Ogre.class, 1.5f)
						.addSpawn(1500, Necromancer.class, 2f);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Ogre.class, 1.75f)
					.addSpawn(350, Ogre.class, 1.75f)
					.addSpawn(600, Ogre.class, 1.75f)
					.addSpawn(1000, Troll.class, 1.75f)
					.addSpawn(1200, Slime.class, 1.75f)
					.addSpawn(1400, Slime.class, 1.75f)
					.addSpawn(1500, Necromancer.class, 2f);
				}
			})
			.build();
	}
	
	private Level getLevel5()
	{
		return new Level.Builder()
			.setTiles(new int[]
			{
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,		//Bottom Right
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				3, 1, 8, 0, 7, 1, 8, 0, 7, 1, 8, 0, 7, 1, 2,
				0, 0, 5, 1, 6, 0, 5, 1, 6, 0, 5, 1, 6, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0			//Top Right
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Goblin.class)
						.addSpawn(400, Goblin.class)
						.addSpawn(700,  Goblin.class)
						.addSpawn(1000, Goblin.class)
						.addSpawn(1500, Ogre.class);
				}	
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Slime.class)
						.addSpawn(1000, Goblin.class)
						.addSpawn(1200, Goblin.class)
						.addSpawn(1400, Goblin.class)
						.addSpawn(1500, Necromancer.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Slime.class)
						.addSpawn(750, Slime.class)
						.addSpawn(1300, Ogre.class)
						.addSpawn(2500, Goblin.class)
						.addSpawn(3200, Goblin.class)
						.addSpawn(3800, Goblin.class)
						.addSpawn(4500, Necromancer.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Goblin.class, 2f)
						.addSpawn(350, Goblin.class, 2f)
						.addSpawn(600, Goblin.class, 2f)
						.addSpawn(1000, Troll.class, 1.5f)
						.addSpawn(1200, Ogre.class, 1.5f)
						.addSpawn(1400, Ogre.class, 1.5f)
						.addSpawn(1500, Necromancer.class, 2f);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Ogre.class, 1.75f)
					.addSpawn(350, Ogre.class, 1.75f)
					.addSpawn(600, Ogre.class, 1.75f)
					.addSpawn(1000, Troll.class, 1.75f)
					.addSpawn(1200, Slime.class, 1.75f)
					.addSpawn(1400, Slime.class, 1.75f)
					.addSpawn(1500, Necromancer.class, 2f);
				}
			})
			.build();
	}
}
