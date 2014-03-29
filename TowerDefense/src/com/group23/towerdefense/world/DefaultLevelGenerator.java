package com.group23.towerdefense.world;

import com.group23.towerdefense.Level;
import com.group23.towerdefense.enemy.Enemy1;
import com.group23.towerdefense.enemy.Enemy2;
import com.group23.towerdefense.enemy.Slime;
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
				}
			})
			.addWave(new Wave.Generator() 
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Enemy1.class)
						.addSpawn(300,  Enemy1.class)
						.addSpawn(500,  Enemy1.class)
						.addSpawn(700,  Enemy1.class)
						.addSpawn(900,  Enemy1.class)
						.addSpawn(1100, Enemy1.class)
						.addSpawn(1300, Enemy1.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Enemy2.class)
						.addSpawn(200, Enemy1.class)
						.addSpawn(300, Enemy1.class)
						.addSpawn(340, Enemy1.class)
						.addSpawn(420, Enemy1.class)
						.addSpawn(500, Enemy1.class)
						.addSpawn(550, Enemy1.class)
						.addSpawn(650, Enemy2.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Enemy1.class)
						.addSpawn(130, Enemy2.class)
						.addSpawn(160, Enemy2.class)
						.addSpawn(190, Enemy2.class)
						.addSpawn(220, Enemy2.class)
						.addSpawn(250, Enemy2.class)
						.addSpawn(280, Enemy2.class)
						.addSpawn(300, Enemy2.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Enemy1.class);
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
					wave.addSpawn(100,  Slime.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Enemy1.class)
						.addSpawn(300,  Enemy1.class)
						.addSpawn(500,  Enemy1.class)
						.addSpawn(700,  Enemy1.class)
						.addSpawn(900,  Enemy1.class)
						.addSpawn(1100, Enemy1.class)
						.addSpawn(1300, Enemy1.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Enemy2.class)
						.addSpawn(200, Enemy1.class)
						.addSpawn(300, Enemy1.class)
						.addSpawn(340, Enemy1.class)
						.addSpawn(420, Enemy1.class)
						.addSpawn(500, Enemy1.class)
						.addSpawn(550, Enemy1.class)
						.addSpawn(650, Enemy2.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Enemy1.class)
						.addSpawn(130, Enemy2.class)
						.addSpawn(160, Enemy2.class)
						.addSpawn(190, Enemy2.class)
						.addSpawn(220, Enemy2.class)
						.addSpawn(250, Enemy2.class)
						.addSpawn(280, Enemy2.class)
						.addSpawn(300, Enemy2.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Enemy1.class);
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
					wave.addSpawn(0,  Enemy1.class)
						.addSpawn(800,  Enemy1.class)
						.addSpawn(1700, Enemy2.class)
						.addSpawn(3500, Enemy1.class)
						.addSpawn(5000, Enemy1.class)
						.addSpawn(6000, Enemy2.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Enemy1.class)
						.addSpawn(300,  Enemy1.class)
						.addSpawn(500,  Enemy1.class)
						.addSpawn(700,  Enemy1.class)
						.addSpawn(900,  Enemy1.class)
						.addSpawn(1100, Enemy1.class)
						.addSpawn(1300, Enemy1.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Enemy2.class)
						.addSpawn(200, Enemy1.class)
						.addSpawn(300, Enemy1.class)
						.addSpawn(340, Enemy1.class)
						.addSpawn(420, Enemy1.class)
						.addSpawn(500, Enemy1.class)
						.addSpawn(550, Enemy1.class)
						.addSpawn(650, Enemy2.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Enemy1.class)
						.addSpawn(130, Enemy2.class)
						.addSpawn(160, Enemy2.class)
						.addSpawn(190, Enemy2.class)
						.addSpawn(220, Enemy2.class)
						.addSpawn(250, Enemy2.class)
						.addSpawn(280, Enemy2.class)
						.addSpawn(300, Enemy2.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave)
				{
					wave.addSpawn(100, Enemy1.class);
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
					wave.addSpawn(100,  Enemy1.class)
						.addSpawn(800,  Enemy1.class)
						.addSpawn(1700, Enemy2.class)
						.addSpawn(3500, Enemy1.class)
						.addSpawn(5000, Enemy1.class)
						.addSpawn(6000, Enemy2.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100,  Enemy1.class)
						.addSpawn(300,  Enemy1.class)
						.addSpawn(500,  Enemy1.class)
						.addSpawn(700,  Enemy1.class)
						.addSpawn(900,  Enemy1.class)
						.addSpawn(1100, Enemy1.class)
						.addSpawn(1300, Enemy1.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Enemy2.class)
						.addSpawn(200, Enemy1.class)
						.addSpawn(300, Enemy1.class)
						.addSpawn(340, Enemy1.class)
						.addSpawn(420, Enemy1.class)
						.addSpawn(500, Enemy1.class)
						.addSpawn(550, Enemy1.class)
						.addSpawn(650, Enemy2.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Enemy1.class)
						.addSpawn(130, Enemy2.class)
						.addSpawn(160, Enemy2.class)
						.addSpawn(190, Enemy2.class)
						.addSpawn(220, Enemy2.class)
						.addSpawn(250, Enemy2.class)
						.addSpawn(280, Enemy2.class)
						.addSpawn(300, Enemy2.class);
				}
			})
			.addWave(new Wave.Generator()
			{
				public void generate(Wave wave) 
				{
					wave.addSpawn(100, Enemy1.class);
				}
			})
			.build();
	}
}
