package com.group23.towerdefense.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.group23.towerdefense.DefaultLevelGenerator;
import com.group23.towerdefense.Level;
import com.group23.towerdefense.ResourceManager;
import com.group23.towerdefense.TowerDefense;
import com.group23.towerdefense.ui.ImageButton;

public class LevelSelectScreen extends BaseScreen
{
	private Level.Generator generator = new DefaultLevelGenerator();
	private Actor[] LevelButtons = new Actor[5];

	@Override
	public void show()
	{
		super.show();

		Stage stage = getStage();

		Image background = new Image(
				ResourceManager.loadTexture("levelmenu.png"));
		stage.addActor(background);

		if (TowerDefense.maxLevel != 0)
		{
			for (int i = 0; i < 5; i++)
			{
				String imageFilename = String.format("level%d_b.png", i + 1);
				Actor levelSelectButton = new LevelSelectButton(imageFilename,
						i);
				levelSelectButton.setBounds(800.0f, (5 - i) * 100.0f + 50.0f,
						200.0f, 60.0f);
				levelSelectButton.setVisible(i <= TowerDefense.maxLevel);

				stage.addActor(levelSelectButton);
				LevelButtons[i] = levelSelectButton;
			}
		}
		else
		{
			Actor startButton = new NewGameButton();
			stage.addActor(startButton);
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
			TowerDefense.curLevel = levelNum;
			Level level = generator.getLevel(levelNum);
			TowerDefense.changeScreen(new GameplayScreen(level));
		}
	}

	private class NewGameButton extends LevelSelectButton
	{
		public NewGameButton()
		{
			super("begin_b.png", 0);
			setBounds(650.0f, 720.0f, 512.0f, 256.0f);
		}
	}
}
