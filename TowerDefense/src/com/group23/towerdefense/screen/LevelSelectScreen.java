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

		int width = TowerDefense.SCREEN_WIDTH;

		Stage stage = getStage();

		Actor level1Actor = new LevelSelectButton("level1_b.png", 1);
		level1Actor.setBounds(width / 2.0f - level1Actor.getWidth() / 2.0f,
				0.0f, 300.0f, 300.0f);

		stage.addActor(level1Actor);
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
