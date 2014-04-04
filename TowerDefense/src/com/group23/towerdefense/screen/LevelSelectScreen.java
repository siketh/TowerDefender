package com.group23.towerdefense.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.group23.towerdefense.DefaultLevelGenerator;
import com.group23.towerdefense.Level;
import com.group23.towerdefense.TowerDefense;

public class LevelSelectScreen extends BaseScreen
{
	private Level.Generator generator = new DefaultLevelGenerator();

	@Override
	public void show()
	{
		super.show();

		Stage stage = getStage();
		for (int i = 0; i < 5; i++)
		{
			String imageFilename = String.format("level%d_b.png", i+1);
			Actor levelSelectButton = new LevelSelectButton(imageFilename, i);
			levelSelectButton.setBounds(i * 256.0f, 0.0f, 256.0f, 256.0f);
			
			stage.addActor(levelSelectButton);
		}
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
			Level level = generator.getLevel(levelNum);
			TowerDefense.changeScreen(new GameplayScreen(level));
		}
	}
}
