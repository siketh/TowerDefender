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
						.addSpawn(600, Goblin.class)
						.addSpawn(1200,  Ogre.class);
				}
			})
			.addWave(new Wave.Generator() 
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Slime.class)
						.addSpawn(700,  Slime.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Troll.class)
						.addSpawn(700,  Troll.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Necromancer.class)
						.addSpawn(700,  Necromancer.class);
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
					wave.addSpawn(100, Goblin.class, .8f)
						.addSpawn(550, Goblin.class, .8f)
						.addSpawn(1100, Goblin.class, .8f)
						.addSpawn(1500, Ogre.class, .8f)
						.addSpawn(2000, Necromancer.class, .8f)
						.addSpawn(2500, Troll.class, .8f);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Slime.class, .7f)
						.addSpawn(750, Slime.class, .7f)
						.addSpawn(1300, Ogre.class, .7f)
						.addSpawn(2500, Goblin.class, .7f)
						.addSpawn(3200, Goblin.class, .7f)
						.addSpawn(3800, Goblin.class, .7f)
						.addSpawn(4500, Necromancer.class, .7f);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Ogre.class, 300f)
						.addSpawn(500, Ogre.class, 300f)
						.addSpawn(900, Ogre.class, 300f)
						.addSpawn(1300, Ogre.class, 300f)
						.addSpawn(1700, Ogre.class, 300f)
						.addSpawn(2100, Ogre.class, 300f)
						.addSpawn(2500, Ogre.class, 300f);
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
						.addSpawn(400,  Goblin.class)
						.addSpawn(700,  Goblin.class)
						.addSpawn(1000,  Ogre.class)
						.addSpawn(1300, Goblin.class)
						.addSpawn(1700,  Goblin.class)
						.addSpawn(2000,  Goblin.class)
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
