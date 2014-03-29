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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.group23.towerdefense.tower.DirectAttackTower;
import com.group23.towerdefense.tower.DirectMultiAttackTower;
import com.group23.towerdefense.tower.Tower;
import com.group23.towerdefense.world.DefaultLevelGenerator;

public class GameplayScreen implements Screen
{
	private Stage stage;
	private Level curLevel;
	private Level.Generator levelGenerator = new DefaultLevelGenerator();
	private TowerGenerator towerGenerator;
	private TowerSelector towerSelector;

	/**
	 * Uses DefaultLevelGenerator for its Level.Generator, and starts at level
	 * 0.
	 */
	public GameplayScreen()
	{

	}

	/**
	 * Uses DefaultLevelGenerator for its Level.Generator, and starts at level
	 * at the inputed level.
	 * 
	 * @param level
	 */
	public GameplayScreen(int level)
	{
		loadLevel(level);
	}

	/**
	 * Uses an inputed Level.Generator, starting at level 0
	 * 
	 * @param levelGenerator
	 */
	public GameplayScreen(Level.Generator levelGenerator)
	{
		this.levelGenerator = levelGenerator;
	}

	/**
	 * Uses an inputed Level.Generator, starting at the specified level.
	 * 
	 * @param levelGenerator
	 * @param level
	 */
	public GameplayScreen(Level.Generator levelGenerator, int level)
	{
		this.levelGenerator = levelGenerator;
		loadLevel(level);
	}

	/**
	 * Sets the current level as the input level number from the Level.Generator
	 * 
	 * @param level
	 */
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
		if (!towerSelector.isMoving())
			towerSelector.setVisible(!towerSelector.isVisible());
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

		if (towerGenerator != null)
		{
			Tower tower = towerGenerator.newTower();
			towerGenerator = null;
			curLevel.placeTower(tower, tileX, tileY);
		}
		else
			curLevel.selectTower(tileX, tileY);
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

		Actor startButton = getStartButtonActor();
		Actor towerButton = getTowerButtonActor();
		Actor goldDisplay = getGoldDisplayActor();
		Actor healthDisplay = getHealthDisplayActor();
		Actor levelActor = getLevelActor();
		towerSelector = new TowerSelector();

		stage.addActor(levelActor);
		stage.addActor(startButton);
		stage.addActor(towerButton);
		stage.addActor(goldDisplay);
		stage.addActor(healthDisplay);
		stage.addActor(towerSelector);

		Gdx.input.setInputProcessor(stage);
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

	/**
	 * Helper function to generate the Actor that represents the current Level.
	 * 
	 * @return Actor representing the current Level
	 * @see GameplayScreen.onLevelPressed
	 */
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

	/**
	 * Helper function to generate the Actor for the Start button. When pressed,
	 * the next wave in the level is started.
	 * 
	 * @return Actor for the Start button
	 * @see GameplayScreen.onStartButtonPressed
	 */
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

	/**
	 * Helper function to generate the Actor for the Tower button. When pressed,
	 * show/hide the TowerSelector.
	 * 
	 * @return Actor for the Tower button
	 * @see GameplayScreen.onTowerButtonPressed
	 */
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

	/**
	 * Helper function to generate the Actor to display the player's health.
	 * 
	 * @return Actor to display the player's health
	 */
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
		healthGroup.pack();

		return healthGroup;
	}

	/**
	 * Helper function to generate the Actor to display's the player's gold.
	 * 
	 * @return Actor to display the player's gold
	 */
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
		goldGroup.pack();

		return goldGroup;
	}

	/**
	 * Internal utility interface to generate a tower. The GameplayScreen holds
	 * a reference to a TowerGenerator, which is used when the user has selected
	 * a tower and places a tower on the level.
	 * 
	 * @author Robert
	 * 
	 */
	private interface TowerGenerator
	{
		public abstract Tower newTower();
	}

	/**
	 * Internal utility class for allowing player's to select a tower they want
	 * to place. The TowerSelector is displayed when the player selects the
	 * Tower button on the top of the screen. It is hidden when the Tower button
	 * is selected again or a tower to place has been chosen.
	 * 
	 * @author Robert
	 * 
	 */
	private class TowerSelector extends HorizontalGroup
	{
		private static final float DURATION = 0.25f;

		private MoveToAction showAction = new MoveToAction();
		private MoveToAction hideAction = new MoveToAction();
		private boolean visible = false;

		public TowerSelector()
		{
			addActor(generateButton("towerbar00.png", new TowerGenerator()
			{
				@Override
				public Tower newTower()
				{
					return new DirectAttackTower();
				}
			}));

			addActor(generateButton("towerbar01.png", new TowerGenerator()
			{
				@Override
				public Tower newTower()
				{
					return new DirectMultiAttackTower();
				}
			}));

			pack();
			setPosition(TowerDefense.SCREEN_WIDTH / 2 - getWidth() / 2,
					-getHeight());

			showAction.setDuration(DURATION);
			showAction.setPosition(getX(), 0.0f);

			hideAction.setDuration(DURATION);
			hideAction.setPosition(getX(), -getHeight());
		}

		public boolean isMoving()
		{
			return getActions().size != 0;
		}

		@Override
		public void setVisible(boolean visible)
		{
			if (visible)
				show();
			else
				hide();
		}

		@Override
		public boolean isVisible()
		{
			return visible;
		}

		public void show()
		{
			visible = true;
			showAction.restart();
			addAction(showAction);
		}

		public void hide()
		{
			hideAction.restart();
			addAction(Actions.sequence(hideAction, Actions.run(new Runnable()
			{
				public void run()
				{
					visible = false;
				}
			})));
		}

		private Actor generateButton(String filename, final TowerGenerator gen)
		{
			Image tower = new Image(ResourceManager.loadTexture(filename));
			tower.addListener(new InputListener()
			{
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button)
				{
					towerGenerator = gen;
					towerSelector.setVisible(false);
					return true;
				}
			});
			return tower;
		}
	}
}
