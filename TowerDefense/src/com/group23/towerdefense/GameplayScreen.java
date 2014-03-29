package com.group23.towerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.group23.towerdefense.tower.DirectAttackTower;
import com.group23.towerdefense.tower.Tower;
import com.group23.towerdefense.world.DefaultLevelGenerator;

public class GameplayScreen implements Screen
{
	private Stage stage;
	private Level curLevel;
	private Level.Generator levelGenerator = new DefaultLevelGenerator();

	public GameplayScreen()
	{

	}

	public GameplayScreen(int level)
	{
		loadLevel(level);
	}
	
	public GameplayScreen(Level.Generator levelGenerator)
	{
		this.levelGenerator = levelGenerator;
	}
	
	public GameplayScreen(Level.Generator levelGenerator, int level)
	{
		this.levelGenerator = levelGenerator;
		loadLevel(level);
	}

	public void loadLevel(int level)
	{
		curLevel = levelGenerator.getLevel(level);
	}

	public Level getLevel()
	{
		return curLevel;
	}

	/**
	 * Called when the Start button on the top bar is pressed. Starts a new wave
	 * if no wave is playing and the current level has not finished all of its
	 * waves.
	 */
	protected void onStartButtonPressed()
	{
		if (!(curLevel.isWavePlaying() || curLevel.hasFinishedAllWaves()))
			curLevel.startNextWave();
	}

	/**
	 * Called when the Tower button on the top bar is pressed.
	 */
	protected void onTowerButtonPressed()
	{
		// TODO make pressing the tower button show the tower bar
		System.out.format("onTowerButtonPressed() called\n");
	}

	/**
	 * Called when the game's current Level is pressed
	 * 
	 * @param x
	 *            Stage x-coordinate of the Level pressed
	 * @param y
	 *            Stage y-coordinate of the Level pressed
	 */
	protected void onLevelPressed(float x, float y)
	{
		int tsize = TowerDefense.TILE_SIZE;

		int tileX = (int) (x / tsize);
		int tileY = (int) (y / tsize);
		Tower tower = new DirectAttackTower();

		System.out.format("(%d, %d)\n", tileX, tileY);
		curLevel.placeTower(tower, tileX, tileY);
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		TowerDefense.shapeRenderer.begin(ShapeType.Line);
		stage.draw();
		TowerDefense.shapeRenderer.end();
	}

	@Override
	public void resize(int width, int height)
	{

	}

	@Override
	public void show()
	{
		if (curLevel == null)
			loadLevel(0);

		int width = TowerDefense.SCREEN_WIDTH;
		int height = TowerDefense.SCREEN_HEIGHT;
		SpriteBatch spriteBatch = TowerDefense.spriteBatch;

		Viewport viewport = new FitViewport(width, height);
		viewport.update(width / 2, height / 2, true);

		stage = new Stage(viewport, spriteBatch);
		Gdx.input.setInputProcessor(stage);

		Actor startButton = getStartButtonActor();
		Actor towerButton = getTowerButtonActor();
		Actor goldDisplay = getGoldDisplayActor();
		Actor healthDisplay = getHealthDisplayActor();
		Actor levelActor = getLevelActor();

		stage.addActor(levelActor);
		stage.addActor(startButton);
		stage.addActor(towerButton);
		stage.addActor(goldDisplay);
		stage.addActor(healthDisplay);
	}

	@Override
	public void hide()
	{

	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{

	}

	@Override
	public void dispose()
	{

	}

	private Actor getLevelActor()
	{
		int width = TowerDefense.SCREEN_WIDTH;
		int height = TowerDefense.SCREEN_HEIGHT;
		int tsize = TowerDefense.TILE_SIZE;

		Actor levelActor = new Actor()
		{
			@Override
			public void act(float delta)
			{
				curLevel.act(delta);
			}

			@Override
			public void draw(Batch batch, float parentAlpha)
			{
				curLevel.draw(batch);
			}
		};
		levelActor.setPosition(0.0f, 0.0f);
		levelActor.setWidth(width);
		levelActor.setHeight(height - (height % tsize));
		levelActor.addListener(new InputListener()
		{
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button)
			{
				onLevelPressed(x, y);
				return true;
			}
		});

		return levelActor;
	}

	private Actor getStartButtonActor()
	{
		Image buttonStart = new Image(
				ResourceManager.loadTexture("start_b.png"));

		buttonStart.setBounds(0.0f, 1020.0f, 200.0f, 60.0f);
		buttonStart.addListener(new InputListener()
		{
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button)
			{
				onStartButtonPressed();
				return true;
			}
		});

		return buttonStart;
	}

	private Actor getTowerButtonActor()
	{
		Image buttonTower = new Image(
				ResourceManager.loadTexture("tower_b.png"));

		buttonTower.setBounds(200.0f, 1020.0f, 200.0f, 60.0f);
		buttonTower.addListener(new InputListener()
		{
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button)
			{
				onTowerButtonPressed();
				return true;
			}
		});

		return buttonTower;
	}

	private Actor getHealthDisplayActor()
	{
		Label.LabelStyle healthStyle = new Label.LabelStyle();
		healthStyle.fontColor = Color.WHITE;
		healthStyle.font = ResourceManager.loadDefaultFont();

		Image healthImage = new Image(ResourceManager.loadTexture("health.png"));
		Label healthLabel = new Label("", healthStyle)
		{
			@Override
			public void act(float delta)
			{
				setText(Integer.toString(curLevel.getLives()));
			}
		};
		healthLabel.setFontScale(2.5f);

		Container healthLabelContainer = new Container(healthLabel);
		healthLabelContainer.padLeft(15.0f);

		HorizontalGroup healthGroup = new HorizontalGroup();
		healthGroup.addActor(healthImage);
		healthGroup.addActor(healthLabelContainer);
		healthGroup.setPosition(1500.0f, 1020.0f);
		healthGroup.setHeight(60.0f);

		return healthGroup;
	}

	private Actor getGoldDisplayActor()
	{
		Label.LabelStyle goldStyle = new Label.LabelStyle();
		goldStyle.fontColor = Color.YELLOW;
		goldStyle.font = ResourceManager.loadDefaultFont();

		Image goldImage = new Image(ResourceManager.loadTexture("gold.png"));
		Label goldLabel = new Label("", goldStyle)
		{
			@Override
			public void act(float delta)
			{
				setText(Integer.toString(curLevel.getGold()));
			}
		};
		goldLabel.setFontScale(2.5f);

		Container goldLabelContainer = new Container(goldLabel);
		goldLabelContainer.padLeft(15.0f);

		HorizontalGroup goldGroup = new HorizontalGroup();
		goldGroup.addActor(goldImage);
		goldGroup.addActor(goldLabelContainer);
		goldGroup.setPosition(1300.0f, 1020.0f);
		goldGroup.setHeight(60.0f);

		return goldGroup;
	}

	// @Override
	// public boolean touchDown(int screenX, int screenY, int pointer, int
	// button)
	// {
	// touchPos.x = screenX;
	// touchPos.y = screenY;
	// touchPos.z = 0;
	//
	// camera.unproject(touchPos); // Converts where you touched into pixel
	// // coordinates
	// int x = (int) (touchPos.x) / 128; // Converts to tile coordinates
	// int y = (int) (touchPos.y) / 128; // Converts to tile coordinates
	//
	// if (TowerFlag == 0)
	// {
	// if (y < curLevel.getHeight())
	// {
	// switch (button)
	// {
	// case Buttons.LEFT:
	//
	// if (x == 4 && y == 0)
	// TowerFlag = 1;
	// else if (x == 5 && y == 0)
	// TowerFlag = 2;
	// else
	// curLevel.selectTower(x, y);
	//
	// break;
	//
	// case Buttons.RIGHT:
	// curLevel.removeTower(x, y);
	// break;
	//
	// }
	// }
	// }
	// else
	// {
	//
	// switch (button)
	// {
	// case Buttons.LEFT:
	// TowerGenerator gen = new TowerGenerator() {
	// @Override
	// public Tower newTower()
	// {
	// switch (TowerFlag)
	// {
	// case 1:
	// return new DirectAttackTower();
	// case 2:
	// return new DirectMultiAttackTower();
	// default:
	// return null;
	// }
	// }
	// };
	//
	// curLevel.placeTower(gen, x, y);
	// TowerFlag = 0;
	// break;
	// case Buttons.RIGHT:
	// TowerFlag = 0;
	// break;
	// }
	//
	// }
	// return true;
	// }
}
