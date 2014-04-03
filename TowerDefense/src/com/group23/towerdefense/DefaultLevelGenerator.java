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
		}
	}
	
	private Level getLevel1()
	{	
		return new Level.Builder()
			.setTiles(new int[]
			{
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,		
				0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 2,
				0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0,
				0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0,
				3, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0			
			})
			.addWave(new Wave.Generator() 
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Slime.class);
					wave.addSpawn(500,  Necromancer.class);
				}
			})
			.addWave(new Wave.Generator() 
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Goblin.class)
						.addSpawn(300,  Goblin.class)
						.addSpawn(500,  Goblin.class)
						.addSpawn(700,  Goblin.class)
						.addSpawn(900,  Goblin.class)
						.addSpawn(1100, Goblin.class)
						.addSpawn(1300, Goblin.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Ogre.class)
						.addSpawn(200, Goblin.class)
						.addSpawn(300, Goblin.class)
						.addSpawn(340, Goblin.class)
						.addSpawn(420, Goblin.class)
						.addSpawn(500, Goblin.class)
						.addSpawn(550, Goblin.class)
						.addSpawn(650, Ogre.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Goblin.class)
						.addSpawn(130, Ogre.class)
						.addSpawn(160, Ogre.class)
						.addSpawn(190, Ogre.class)
						.addSpawn(220, Ogre.class)
						.addSpawn(250, Ogre.class)
						.addSpawn(280, Ogre.class)
						.addSpawn(300, Ogre.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Goblin.class);
				}
			})
			.build();
	}
	
	private Level getLevel2()
	{
		return new Level.Builder()
			.setTiles(new int[]
			{
				0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,		//Bottom Right
				0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0			//Top Right
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Troll.class);
					wave.addSpawn(500,  Necromancer.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Ogre.class);
					//	.addSpawn(1000,  Ogre.class, 3.0);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Ogre.class)
						.addSpawn(200, Goblin.class)
						.addSpawn(300, Goblin.class)
						.addSpawn(340, Goblin.class)
						.addSpawn(420, Goblin.class)
						.addSpawn(500, Goblin.class)
						.addSpawn(550, Goblin.class)
						.addSpawn(650, Ogre.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Goblin.class)
						.addSpawn(130, Ogre.class)
						.addSpawn(160, Ogre.class)
						.addSpawn(190, Ogre.class)
						.addSpawn(220, Ogre.class)
						.addSpawn(250, Ogre.class)
						.addSpawn(280, Ogre.class)
						.addSpawn(300, Ogre.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Goblin.class);
				}
			})
			.build();
	}
	
	private Level getLevel3()
	{
		return new Level.Builder()
			.setTiles(new int[]
			{
				0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,		
				0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0		//Top Right
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(0,  Goblin.class)
						.addSpawn(800,  Goblin.class)
						.addSpawn(1700, Ogre.class)
						.addSpawn(3500, Goblin.class)
						.addSpawn(5000, Goblin.class)
						.addSpawn(6000, Ogre.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Goblin.class)
						.addSpawn(300,  Goblin.class)
						.addSpawn(500,  Goblin.class)
						.addSpawn(700,  Goblin.class)
						.addSpawn(900,  Goblin.class)
						.addSpawn(1100, Goblin.class)
						.addSpawn(1300, Goblin.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Ogre.class)
						.addSpawn(200, Goblin.class)
						.addSpawn(300, Goblin.class)
						.addSpawn(340, Goblin.class)
						.addSpawn(420, Goblin.class)
						.addSpawn(500, Goblin.class)
						.addSpawn(550, Goblin.class)
						.addSpawn(650, Ogre.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Goblin.class)
						.addSpawn(130, Ogre.class)
						.addSpawn(160, Ogre.class)
						.addSpawn(190, Ogre.class)
						.addSpawn(220, Ogre.class)
						.addSpawn(250, Ogre.class)
						.addSpawn(280, Ogre.class)
						.addSpawn(300, Ogre.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave)
				{
					wave.addSpawn(100, Goblin.class);
				}
			})
			.build();
	}
	
	private Level getLevel4()
	{
		return new Level.Builder()
			.setTiles(new int[]
			{
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,		//Bottom Right
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0			//Top Right
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Goblin.class)
						.addSpawn(800,  Goblin.class)
						.addSpawn(1700, Ogre.class)
						.addSpawn(3500, Goblin.class)
						.addSpawn(5000, Goblin.class)
						.addSpawn(6000, Ogre.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Goblin.class)
						.addSpawn(300,  Goblin.class)
						.addSpawn(500,  Goblin.class)
						.addSpawn(700,  Goblin.class)
						.addSpawn(900,  Goblin.class)
						.addSpawn(1100, Goblin.class)
						.addSpawn(1300, Goblin.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Ogre.class)
						.addSpawn(200, Goblin.class)
						.addSpawn(300, Goblin.class)
						.addSpawn(340, Goblin.class)
						.addSpawn(420, Goblin.class)
						.addSpawn(500, Goblin.class)
						.addSpawn(550, Goblin.class)
						.addSpawn(650, Ogre.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Goblin.class)
						.addSpawn(130, Ogre.class)
						.addSpawn(160, Ogre.class)
						.addSpawn(190, Ogre.class)
						.addSpawn(220, Ogre.class)
						.addSpawn(250, Ogre.class)
						.addSpawn(280, Ogre.class)
						.addSpawn(300, Ogre.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Goblin.class);
				}
			})
			.build();
	}
}
