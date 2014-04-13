package com.group23.towerdefense.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.group23.towerdefense.DefaultLevelGenerator;
import com.group23.towerdefense.Level;
import com.group23.towerdefense.ResourceManager;
import com.group23.towerdefense.TowerDefense;
import com.group23.towerdefense.ui.ImageButton;


public class LevelSelectScreen extends BaseScreen
{
	private Level.Generator generator = new DefaultLevelGenerator();
	private FileHandle handle = Gdx.files.local("data/user-progress.xml");
	public static int levelTrack;
	private Actor[] LevelButtons = new Actor[5];

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
			levelSelectButton.setBounds(800.0f, (5 - i) * 100.0f + 50.0f, 200.0f, 60.0f);
			
			stage.addActor(levelSelectButton);
			LevelButtons[i] = levelSelectButton;
		}
		Actor loadButton = new LoadButton();
		stage.addActor(loadButton);
		
		Actor startButton = new StartButton();
		stage.addActor(startButton);
		startButton.setVisible(false);
		
		if(handle.readString().equals("0")){
			for(int i = 0; i <5; i++)
				LevelButtons[i].setVisible(false);
			loadButton.setVisible(false);
			startButton.setVisible(true);
		}
		else if(handle.readString().equals("1")){
			for(int i = 1; i < 5; i++)
				LevelButtons[i].setVisible(false);
		}
		else if(handle.readString().equals("2")){
			for(int i = 2; i < 5; i++)
				LevelButtons[i].setVisible(false);
		}
		else if(handle.readString().equals("3")){
			for(int i = 3; i < 5; i++)
				LevelButtons[i].setVisible(false);
		}
		else if(handle.readString().equals("4")){
			LevelButtons[4].setVisible(false);
		}
		
	};
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
			setBounds(650.0f, 720.0f, 512.0f, 128.0f);
		}
		
		protected void onPressed()
		{
			if(handle.readString().equals("1")){
				Level level = generator.getLevel(0);
				TowerDefense.changeScreen(new GameplayScreen(level));
			}
			else if(handle.readString().equals("2")){
				Level level = generator.getLevel(1);
				TowerDefense.changeScreen(new GameplayScreen(level));
			}
			else if(handle.readString().equals("3")){
				Level level = generator.getLevel(2);
				TowerDefense.changeScreen(new GameplayScreen(level));
			}
			else if(handle.readString().equals("4")){
				Level level = generator.getLevel(3);
				TowerDefense.changeScreen(new GameplayScreen(level));
			}
			else if(handle.readString().equals("5")){
				Level level = generator.getLevel(4);
				TowerDefense.changeScreen(new GameplayScreen(level));
			}
			else if(handle.readString().equals(null)){
				
			}
		}
	}
	
	private class StartButton extends ImageButton
	{
		
		public StartButton()
		{
			super("begin_b.png");
			setBounds(650.0f, 720.0f, 512.0f, 256.0f);
		}
		
		protected void onPressed()
		{
			handle.writeString("1", false);
			levelTrack = 0;
			Level level = generator.getLevel(0);
			TowerDefense.changeScreen(new GameplayScreen(level));
		}
	}
}
