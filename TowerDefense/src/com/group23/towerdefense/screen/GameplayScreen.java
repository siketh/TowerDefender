package com.group23.towerdefense.screen;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.group23.towerdefense.Level;
import com.group23.towerdefense.ResourceManager;
import com.group23.towerdefense.TowerDefense;
import com.group23.towerdefense.enemy.Enemy;
import com.group23.towerdefense.tower.Tower;
import com.group23.towerdefense.tower.Upgrade;
import com.group23.towerdefense.ui.CircleGroup;
import com.group23.towerdefense.ui.ImageButton;

public class GameplayScreen extends BaseScreen
{
	private enum State
	{
		Win, Lose, Playing
	}

	private boolean paused = false;
	private State state = State.Playing;
	private Level curLevel;
	private TowerSelection towerSelection;
	private TowerSelector towerSelector;
	private SelectedTower selectedTower;
	private PauseMuteButton pauseMute;
	private PauseExitButton pauseExit;

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
		Actor pauseButton = new PauseButtonActor();
		Actor goldDisplay = new GoldDisplayActor();
		Actor healthDisplay = new HealthDisplayActor();
		Actor levelActor = new LevelActor();
		towerSelector = new TowerSelector();
		selectedTower = new SelectedTower();
		pauseMute = new PauseMuteButton();
		pauseExit = new PauseExitButton();

		Stage stage = getStage();

		stage.addActor(startButton);
		stage.addActor(towerButton);
		stage.addActor(pauseButton);
		stage.addActor(goldDisplay);
		stage.addActor(healthDisplay);
		stage.addActor(levelActor);
		stage.addActor(towerSelector);
		stage.addActor(selectedTower);
		stage.addActor(pauseMute);
		stage.addActor(pauseExit);
		pauseMute.setVisible(false);
		pauseExit.setVisible(false);
	}

	/**
	 * Called when the Start button on the top bar is pressed. Starts a new wave
	 * if no wave is playing and the current level has not finished all of its
	 * waves.
	 */
	private void onStartButtonPressed()
	{
		// if ((!curLevel.isWavePlaying() || curLevel.hasFinishedAllWaves()))
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


	private void setEndState(final State state, Actor actor)
	{
		getStage().addActor(actor);
		getStage().addListener(new InputListener()
		{
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button)
			{
				if (state == State.Win)
				{
					// Set the max level to TowerDefense.curLevel <=
					// TowerDefense.maxLevel <= 5
					TowerDefense.maxLevel = Math.min(
							Math.max(TowerDefense.curLevel + 1, TowerDefense.maxLevel),
							5);
				}
				
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

		if (towerSelection == null)
		{
			selectedTower.setTower(curLevel.getTower(tileX, tileY));
		}
		else
		{
			Tower tower = towerSelection.getTowerGenerator().generate();
			towerSelection.setHighlight(false);
			towerSelection = null;
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
			textures = new Texture[9];
			textures[0] = ResourceManager.loadTexture("tile00.png");
			textures[1] = ResourceManager.loadTexture("tile01.png");
			textures[2] = ResourceManager.loadTexture("tile02.png");
			textures[3] = ResourceManager.loadTexture("tile03.png");
			textures[4] = ResourceManager.loadTexture("tile04.png");
			textures[5] = ResourceManager.loadTexture("tile05.png");
			textures[6] = ResourceManager.loadTexture("tile06.png");
			textures[7] = ResourceManager.loadTexture("tile07.png");
			textures[8] = ResourceManager.loadTexture("tile08.png");

			setPosition(0.0f, 0.0f);
			setWidth(width);
			setHeight(height - (height % tsize));
			addListener(new InputListener()
			{
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button)
				{
					if (!paused)
						onLevelPressed(x, y);
					return true;
				}
			});
		}

		@Override
		public void act(float delta)
		{
			if (!paused)
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

	private class PauseButtonActor extends ImageButton
	{
		public PauseButtonActor()
		{
			super("pause_b.png");
			setBounds(1700.0f, 1020.0f, 64.0f, 64.0f);

		}

		protected void onPressed()
		{
			paused = !paused;
			pauseMute.setVisible(paused);
			pauseExit.setVisible(paused);

		}
	}

	private class PauseMuteButton extends ImageButton
	{
		public PauseMuteButton()
		{
			super("mute_b.png");
			setBounds(750.0f, 700.0f, 512.0f, 128.0f);
		}
		
		protected void onPressed()
		{
			
			
		}
	}
	
	private class PauseExitButton extends ImageButton
	{
		public PauseExitButton()
		{
			super("exit_b.png");
			setBounds(750.0f, 500.0f, 512.0f, 128.0f);
		}
		
		protected void onPressed()
		{
			TowerDefense.changeScreen(new LevelSelectScreen());
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

	private static final Color DEFAULT_COLOR = Color.WHITE;
	private static final Color HIGHLIGHT_COLOR = Color.YELLOW;
	private static final Color AVAILABLE_COLOR = Color.GREEN;
	private static final Color UNAVAILABLE_COLOR = Color.RED;

	private class TowerSelection extends VerticalGroup
	{
		private Tower.Generator generator;
		private LabelStyle nameStyle;

		public TowerSelection(Tower.Generator gen)
		{
			generator = gen;

			Image towerImage = new Image(gen.getTexture());
			addActor(towerImage);

			nameStyle = new LabelStyle();
			nameStyle.font = ResourceManager.loadDefaultFont();
			Label towerLabel = new Label(gen.getName(), nameStyle);
			towerLabel.setFontScale(2.0f);
			addActor(towerLabel);

			final LabelStyle goldStyle = new LabelStyle();
			goldStyle.font = ResourceManager.loadDefaultFont();
			Label goldLabel = new Label(Integer.toString(gen.getGoldCost()),
					goldStyle);
			goldLabel.setFontScale(1.75f);
			addActor(goldLabel);

			addAction(new Action()
			{
				@Override
				public boolean act(float delta)
				{
					goldStyle.fontColor = canPurchase() ? AVAILABLE_COLOR
							: UNAVAILABLE_COLOR;
					return false;
				}
			});

			addListener(new InputListener()
			{
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button)
				{
					if (canPurchase())
					{
						if (towerSelection != null)
							towerSelection.setHighlight(false);

						if (towerSelection != TowerSelection.this)
						{
							towerSelection = TowerSelection.this;
							setHighlight(true);
						}
						else
							towerSelection = null;
					}
					return true;
				}
			});

			setHighlight(false);
			space(5.0f);
			pack();
		}

		public void setHighlight(boolean highlight)
		{
			nameStyle.fontColor = highlight ? HIGHLIGHT_COLOR : DEFAULT_COLOR;
		}

		public Tower.Generator getTowerGenerator()
		{
			return generator;
		}

		private boolean canPurchase()
		{
			return curLevel.getGold() >= generator.getGoldCost();
		}
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
			Tower.Generator[] generators = Tower.getTowerGenerators();
			for (Tower.Generator gen : generators)
				addActor(new TowerSelection(gen));

			space(10.0f);
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
	}

	private class SelectedTower extends Group
	{
		private Tower tower;
		ArrayList<Upgrade> upgrades;

		public SelectedTower()
		{
			setTower(null);
		}

		public void createButtons()
		{
			if(tower == null)
				return;
			LabelStyle sellStyle = new LabelStyle();
			sellStyle.font = ResourceManager.loadDefaultFont();
			sellStyle.fontColor = Color.WHITE;
			
			clear();
			CircleGroup group = new CircleGroup();

			Actor sellButton = new ImageButton("sell_button.png")
			{
				@Override
				protected void onPressed()
				{
					onSellPressed();
				}
			};
			
			VerticalGroup theButton = new VerticalGroup();
			
			Label name = new Label("Sell", sellStyle);
			name.setFontScale(2f);
			theButton.addActor(name);
			
			theButton.addActor(sellButton);
			
			Label value = new Label(new Integer(tower.getGoldCost()).toString(), sellStyle);
			value.setFontScale(2f);
			theButton.addActor(value);
			
			theButton.pack();
			group.addActor(theButton);
			if (tower != null)
				upgrades = tower.getUpgrades();
			else
				upgrades = new ArrayList<Upgrade>();

			for (int i = 0; i < upgrades.size(); i++)
			{
				VerticalGroup upgradeButtonGroup = new VerticalGroup();
				final int number = i;
				Actor upgradeButton = new ImageButton(upgrades.get(i)
						.getTexName())
				{
					protected void onPressed()
					{
						upgrades.get(number).press();
						setTower(null);
					}
				};
				Label upgradeName;
				if(upgrades.get(i).getLevels() <= 1)
					upgradeName = new Label(upgrades.get(i).getName(), sellStyle);
				else
					upgradeName = new Label(upgrades.get(i).getName() + "(" + upgrades.get(i).getCurrentLevel() + "/" + upgrades.get(i).getLevels() + ")", sellStyle);
				upgradeName.setFontScale(2f);
				
				Label upgradeCost = new Label(new Integer(upgrades.get(i).getCost()).toString(), sellStyle);
				upgradeCost.setFontScale(2f);
				
				
				upgradeButtonGroup.addActor(upgradeName);
				upgradeButtonGroup.addActor(upgradeButton);
				upgradeButtonGroup.addActor(upgradeCost);
				upgradeButtonGroup.pack();
				group.addActor(upgradeButtonGroup);
			}

			addActor(group);
			group.setRadius(160f);
			group.pack();
		}

		public void setTower(Tower tower)
		{
			boolean sameTower = this.tower != null && this.tower == tower;
			this.tower = !sameTower ? tower : null;
			boolean visible = this.tower != null;
			setVisible(visible);
			if (tower != null)
			{
				createButtons();
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

			setPosition(width / 2.0f - getWidth() / 2.0f, height / 2.0f
					- getHeight() / 2.0f);
		}
	}

	private class LoseImage extends Image
	{
		public LoseImage()
		{
			super(ResourceManager.loadTexture("lose.png"));

			int width = TowerDefense.SCREEN_WIDTH;
			int height = TowerDefense.SCREEN_HEIGHT;

			setPosition(width / 2.0f - getWidth() / 2.0f, height / 2.0f
					- getHeight() / 2.0f);
		}
	}
}
