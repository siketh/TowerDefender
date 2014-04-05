package com.group23.towerdefense.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.files.FileHandle;
import com.group23.towerdefense.Level;
import com.group23.towerdefense.ResourceManager;
import com.group23.towerdefense.TowerDefense;
import com.group23.towerdefense.enemy.Enemy;
import com.group23.towerdefense.tower.ArrowTower;
import com.group23.towerdefense.tower.MultiArrowTower;
import com.group23.towerdefense.tower.Tower;

public class GameplayScreen extends BaseScreen
{
	private enum State
	{
		Win,
		Lose,
		Playing
	}
	
	private State state = State.Playing;
	private Level curLevel;
	private TowerGenerator towerGenerator;
	private TowerSelector towerSelector;
	private SelectedTower selectedTower;
	private FileHandle handle = Gdx.files.local("data/user-progress.xml");

	/**
	 * Uses an inputed Level.Generator, starting at the specified level.
	 * 
	 * @param levelGenerator
	 * @param level
	 */
	public GameplayScreen(Level curLevel)
	{
		this.curLevel = curLevel;
	}

	@Override
	public void act(float delta)
	{
		if (state != State.Playing)
			return;
		
		super.act(delta);
		
		if (isDefeated())
			setEndState(State.Lose, new LoseImage());
		else if (hasWon())
			setEndState(State.Win, new WinImage());
	}

	@Override
	public void show()
	{
		super.show();

		Actor startButton = new StartButtonActor();
		Actor towerButton = new TowerButtonActor();
		Actor goldDisplay = new GoldDisplayActor();
		Actor healthDisplay = new HealthDisplayActor();
		Actor saveButton = new SaveButtonActor();
		Actor levelActor = new LevelActor();
		towerSelector = new TowerSelector();
		selectedTower = new SelectedTower();

		Stage stage = getStage();

		stage.addActor(levelActor);
		stage.addActor(startButton);
		stage.addActor(towerButton);
		stage.addActor(goldDisplay);
		stage.addActor(healthDisplay);
		stage.addActor(saveButton);
		stage.addActor(towerSelector);
		stage.addActor(selectedTower);
	}
	
	/**
	 * Called when the Start button on the top bar is pressed. Starts a new wave
	 * if no wave is playing and the current level has not finished all of its
	 * waves.
	 */
	private void onStartButtonPressed()
	{
		if (!(curLevel.isWavePlaying() || curLevel.hasFinishedAllWaves()))
			curLevel.startNextWave();
	}

	/**
	 * Called when the Tower button on the top bar is pressed.
	 */
	private void onTowerButtonPressed()
	{
		if (!towerSelector.isMoving())
			towerSelector.setVisible(!towerSelector.isVisible());
	}
	
	/**
	 * Called when the Save button on the top bar is pressed. Saves the users
	 * current level progress.
	 *
	 */
	private void onSaveButtonPressed()
	{
		handle.writeString("1", false);
	}
	
	private void setEndState(State state, Actor actor)
	{
		getStage().addActor(actor);
		getStage().addListener(new InputListener()
		{
			@Override
			public boolean touchDown(InputEvent event, float x,
					float y, int pointer, int button)
			{
				TowerDefense.changeScreen(new LevelSelectScreen());
				return true;
			}
		});
		this.state = state;
	}

	/**
	 * Called when the game's current Level is pressed
	 * 
	 * @param x
	 *            Stage x-coordinate of the Level pressed
	 * @param y
	 *            Stage y-coordinate of the Level pressed
	 */
	private void onLevelPressed(float x, float y)
	{
		int tsize = TowerDefense.TILE_SIZE;
		int tileX = (int) (x / tsize);
		int tileY = (int) (y / tsize);

		if (towerGenerator == null)
			selectedTower.setTower(curLevel.getTower(tileX, tileY));
		else
		{
			Tower tower = towerGenerator.newTower();
			towerGenerator = null;
			curLevel.placeTower(tower, tileX, tileY);
		}
	}

	public Level getLevel()
	{
		return curLevel;
	}
	
	public boolean hasWon()
	{
		return curLevel.hasFinishedAllWaves();
	}
	
	public boolean isDefeated()
	{
		return curLevel.getLives() <= 0;
	}

	/**
	 * Internal utility class to be the Actor representation of a Level. This
	 * actor handles the drawing of the Level.
	 * 
	 * @author Robert
	 * @see GameplayScreen.onLevelPressed
	 */
	private class LevelActor extends Actor
	{
		private Texture background;
		private Texture[] textures;

		public LevelActor()
		{
			int width = TowerDefense.SCREEN_WIDTH;
			int height = TowerDefense.SCREEN_HEIGHT;
			int tsize = TowerDefense.TILE_SIZE;

			background = ResourceManager.loadTexture("background.png");
			textures = new Texture[8];
			textures[0] = ResourceManager.loadTexture("tile00.png");
			textures[1] = ResourceManager.loadTexture("tile01.png");
			textures[2] = ResourceManager.loadTexture("tile02.png");
			textures[3] = ResourceManager.loadTexture("tile03.png");
			textures[4] = ResourceManager.loadTexture("tile04.png");
			textures[5] = ResourceManager.loadTexture("tile05.png");
			textures[6] = ResourceManager.loadTexture("tile06.png");
			textures[7] = ResourceManager.loadTexture("tile07.png");

			setPosition(0.0f, 0.0f);
			setWidth(width);
			setHeight(height - (height % tsize));
			addListener(new InputListener()
			{
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button)
				{
					onLevelPressed(x, y);
					return true;
				}
			});
		}

		@Override
		public void act(float delta)
		{
			curLevel.act(delta);
		}

		@Override
		public void draw(Batch batch, float parentAlpha)
		{
			// Draw background
			batch.draw(background, 0, 0);

			// Draws level tiles
			for (int y = 0; y < Level.NUM_TILES_HEIGHT; y++)
				for (int x = 0; x < Level.NUM_TILES_WIDTH; x++)
					if (curLevel.getTile(x, y) != 0)
						batch.draw(textures[curLevel.getTile(x, y)], x * 128,
								y * 128);

			// Draw enemies
			for (Enemy e : curLevel.getEnemies())
				e.draw(batch);

			// Draw towers
			for (Tower t : curLevel.getTowers())
				t.draw(batch);
		}
	}

	/**
	 * Actor representing the Start button on the top bar. Pressing it will
	 * start the next wave if the conditions are met.
	 * 
	 * @author Robert
	 * @see GameplayScreen.onStartButtonPressed
	 */
	private class StartButtonActor extends ImageButton
	{
		public StartButtonActor()
		{
			super("start_b.png");
			setBounds(0.0f, 1020.0f, 200.0f, 60.0f);
		}

		@Override
		protected void onPressed()
		{
			onStartButtonPressed();
		}
	}

	/**
	 * Actor representing the Tower button on the top bar. Pressing it will show
	 * the towers that the user can place at the bottom of the screen.
	 * 
	 * @author Robert
	 * @see GameplayScreen.onTowerButtonPressed
	 */
	private class TowerButtonActor extends ImageButton
	{
		public TowerButtonActor()
		{
			super("tower_b.png");
			setBounds(200.0f, 1020.0f, 200.0f, 60.0f);
		}

		@Override
		protected void onPressed()
		{
			onTowerButtonPressed();
		}
	}

	/**
	 * Actor representing the Save button on the top bar. Pressing it will save
	 * the users current level progress.
	 * @author Jacob
	 * @see GameplayScreen.onTowerButtonPressed
	 */
	
	private class SaveButtonActor extends ImageButton
	{
		public SaveButtonActor()
		{
			super("save_b.png");
			setBounds(400.0f, 1020.0f, 200.0f, 60.0f);
		}
		
		protected void onPressed()
		{
			onSaveButtonPressed();
		}
	}

	/**
	 * Actor to display the player's current health.
	 * 
	 * @author Robert
	 * 
	 */
	private class HealthDisplayActor extends HorizontalGroup
	{
		public HealthDisplayActor()
		{
			Label.LabelStyle healthStyle = new Label.LabelStyle();
			healthStyle.fontColor = Color.WHITE;
			healthStyle.font = ResourceManager.loadDefaultFont();

			Image healthImage = new Image(
					ResourceManager.loadTexture("health.png"));
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

			addActor(healthImage);
			addActor(healthLabelContainer);
			setPosition(1500.0f, 1020.0f);
			pack();
		}
	}

	/**
	 * Actor to display the player's current gold amount.
	 * 
	 * @author Robert
	 * 
	 */
	private class GoldDisplayActor extends HorizontalGroup
	{
		public GoldDisplayActor()
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

			addActor(goldImage);
			addActor(goldLabelContainer);
			setPosition(1300.0f, 1020.0f);
			pack();
		}
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
					return new ArrowTower();
				}
			}));

			addActor(generateButton("towerbar01.png", new TowerGenerator()
			{
				@Override
				public Tower newTower()
				{
					return new MultiArrowTower();
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
					// towerSelector.setVisible(false);
					return true;
				}
			});
			return tower;
		}
	}
	
	private class SelectedTower extends Group
	{
		private Tower tower;
		
		public SelectedTower()
		{
			// Sell Button
			Actor sellButton = new ImageButton("sell_button.png")
			{
				@Override
				protected void onPressed()
				{
					onSellPressed();
				}
			};
			sellButton.setPosition(-32.0f, -128.0f);
			
			addActor(sellButton);
			
			setTower(null);
		}
		
		public void setTower(Tower tower)
		{
			this.tower = tower;
			boolean visible = tower != null;
			setVisible(visible);
			if (tower != null)
			{
				Vector2 pos = tower.getPosition();
				setPosition(pos.x, pos.y);
			}
		}

		@Override
		public void draw(Batch batch, float parentAlpha)
		{
			super.draw(batch, parentAlpha);
			
			if (tower != null)
			{
				batch.end();
	
				ShapeRenderer shapeRenderer = TowerDefense.shapeRenderer;
				shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
				shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
	
				shapeRenderer.begin(ShapeType.Line);
				tower.drawShapes(shapeRenderer);
				shapeRenderer.end();
	
				batch.begin();
			}
		}
		
		private void onSellPressed()
		{
			curLevel.removeTower(tower);
			curLevel.giveGold(tower.getGoldCost());
			setTower(null);
		}
	}
	
	private class WinImage extends Image
	{
		public WinImage()
		{
			super(ResourceManager.loadTexture("win.png"));
			
			int width = TowerDefense.SCREEN_WIDTH;
			int height = TowerDefense.SCREEN_HEIGHT;
			
			setPosition(width / 2.0f - getWidth() / 2.0f, height / 2.0f - getHeight() / 2.0f);
		}
	}
	
	private class LoseImage extends Image
	{
		public LoseImage()
		{
			super(ResourceManager.loadTexture("lose.png"));
			
			int width = TowerDefense.SCREEN_WIDTH;
			int height = TowerDefense.SCREEN_HEIGHT;
			
			setPosition(width / 2.0f - getWidth() / 2.0f, height / 2.0f - getHeight() / 2.0f);
		}
	}
}
