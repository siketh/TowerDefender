package com.group23.towerdefense.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.group23.towerdefense.DefaultLevelGenerator;
import com.group23.towerdefense.Level;
import com.group23.towerdefense.Level.Generator;
import com.group23.towerdefense.TowerDefense;

public class LevelSelectScreen extends BaseScreen
{
	@Override
	public void show()
	{
		super.show();

		int width = TowerDefense.SCREEN_WIDTH;

		Stage stage = getStage();

		Actor level1Actor = new LevelSelectButton("level1_b.png",
				new CreateLevelGenerator()
				{
					@Override
					public Generator getLevelGenerator()
					{
						return new DefaultLevelGenerator();
					}
				});
		level1Actor.setBounds(width / 2.0f - level1Actor.getWidth() / 2.0f,
				0.0f, 300.0f, 300.0f);

		stage.addActor(level1Actor);
	}

	private interface CreateLevelGenerator
	{
		public Level.Generator getLevelGenerator();
	}

	private class LevelSelectButton extends ImageButton
	{
		private CreateLevelGenerator generator;

		public LevelSelectButton(String imageFilename,
				CreateLevelGenerator generator)
		{
			super(imageFilename);
			this.generator = generator;
		}

		@Override
		protected void onPressed()
		{
			Level.Generator gen = generator.getLevelGenerator();
			int level = 0;

			TowerDefense.changeScreen(new GameplayScreen(gen, level));
		}
	}
}
