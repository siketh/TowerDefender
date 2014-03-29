package com.group23.towerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.group23.towerdefense.tower.DirectAttackTower;
import com.group23.towerdefense.tower.Tower;
import com.group23.towerdefense.world.World1;

public class GameplayScreen implements Screen
{
	private Stage stage;
	private Level curLevel;
	private Level.Generator levelGenerator = new World1();

	public GameplayScreen()
	{

	}

	public GameplayScreen(int level)
	{
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
	
	private void onStartButtonPressed()
	{
		// TODO make pressing the start button, start the next wave
	}

	private void onTowerButtonPressed()
	{
		// TODO make pressing the tower button show the tower bar
	}

	private void onLevelPressed(float x, float y)
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
			loadLevel(1);
		
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
		Actor levelActor = getLevelActor();

		stage.addActor(startButton);
		stage.addActor(towerButton);
		stage.addActor(goldDisplay);
		stage.addActor(levelActor);
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
		int height = TowerDefense.SCREEN_HEIGHT;
		int tsize = TowerDefense.TILE_SIZE;
		
		Sprite sprite = new Sprite(ResourceManager.loadTexture("start_b.png"));
		sprite.setScale((height % tsize) / sprite.getHeight());

		ImageButton buttonStart = new ImageButton(new SpriteDrawable(sprite));
		buttonStart.setPosition(0.0f, height - buttonStart.getHeight());
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
		int height = TowerDefense.SCREEN_HEIGHT;
		int tsize = TowerDefense.TILE_SIZE;
		
		Sprite sprite = new Sprite(ResourceManager.loadTexture("tower_b.png"));
		sprite.setScale((height % tsize) / sprite.getHeight());
		SpriteDrawable spriteDrawable = new SpriteDrawable(sprite);
		ImageButton buttonTower = new ImageButton(spriteDrawable);
		buttonTower.setPosition(200.0f, height - buttonTower.getHeight());
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
				setText(Integer.toString(curLevel.getLives()));
			}
		};
		goldLabel.setFontScale(2.5f);

		HorizontalGroup goldGroup = new HorizontalGroup();
		goldGroup.addActor(goldImage);
		goldGroup.addActor(goldLabel);
		goldGroup.setPosition(0.0f, 300.0f);
		
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
