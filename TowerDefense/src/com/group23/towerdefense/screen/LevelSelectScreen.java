package com.group23.towerdefense.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.group23.towerdefense.DefaultLevelGenerator;
import com.group23.towerdefense.Level;
import com.group23.towerdefense.ResourceManager;
import com.group23.towerdefense.TowerDefense;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


public class LevelSelectScreen extends BaseScreen
{
	private Level.Generator generator = new DefaultLevelGenerator();
	private FileHandle handle = Gdx.files.local("data/user-progress.xml");
	public static int levelTrack;

	@Override
	public void show()
	{
		super.show();

		Stage stage = getStage();
		
		Actor menuActor = new MenuActor();
		stage.addActor(menuActor);
		
		for (int i = 0; i < 5; i++)
		{
			String imageFilename = String.format("level%d_b.png", i+1);
			Actor levelSelectButton = new LevelSelectButton(imageFilename, i);
			levelSelectButton.setBounds(i * 256.0f, 100.0f, 200.0f, 60.0f);
			
			stage.addActor(levelSelectButton);
		}
		Actor loadButton = new LoadButton();
		stage.addActor(loadButton);
	}

	private class LevelSelectButton extends ImageButton
	{
		private int levelNum;
		
		public LevelSelectButton(String imageFilename, int level)
		{
			super(imageFilename);
			this.levelNum = level;
		}

		@Override
		protected void onPressed()
		{
			levelTrack = levelNum;
			Level level = generator.getLevel(levelNum);
			TowerDefense.changeScreen(new GameplayScreen(level));
		}
		
	}
	
	
	private class MenuActor extends Actor
	{
		private Texture background;
		
		public MenuActor()
		{
			int width = TowerDefense.SCREEN_WIDTH;
			int height = TowerDefense.SCREEN_HEIGHT;
			
			background = ResourceManager.loadTexture("levelmenu.png");
			
			setPosition(0.0f,0.0f);
			setWidth(width);
			setHeight(height);
		}
		
		public void draw(Batch batch, float parentAlpha)
		{
			batch.draw(background, 0, 0);
		}
	}
	
	private class LoadButton extends ImageButton
	{
		
		public LoadButton()
		{
			super("load_b.png");
			setBounds(0.0f, 512.0f, 200.0f, 60.0f);
		}
		
		protected void onPressed()
		{
			if(handle.readString().equals("1")){
				Level level = generator.getLevel(1);
				TowerDefense.changeScreen(new GameplayScreen(level));
			}
			else if(handle.readString().equals("2")){
				Level level = generator.getLevel(2);
				TowerDefense.changeScreen(new GameplayScreen(level));
			}
			else if(handle.readString().equals("3")){
				Level level = generator.getLevel(3);
				TowerDefense.changeScreen(new GameplayScreen(level));
			}
			else if(handle.readString().equals("4")){
				Level level = generator.getLevel(4);
				TowerDefense.changeScreen(new GameplayScreen(level));
			}
			else if(handle.readString().equals("5")){
				Level level = generator.getLevel(5);
				TowerDefense.changeScreen(new GameplayScreen(level));
			}
			else if(handle.readString().equals(null)){
				
			}
		}
	}
}
