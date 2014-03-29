package com.group23.towerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.group23.towerdefense.tower.DirectAttackTower;
import com.group23.towerdefense.tower.Tower;
import com.group23.towerdefense.world.World1;

public class GameplayScreen implements Screen
{
	private Stage stage;
	
	private Level.Generator levelGenerator = new World1();

	// current level being played
	private Level curLevel;

	private int TowerFlag;

	public GameplayScreen(int level)
	{
		curLevel = levelGenerator.getLevel(level);
		
		/**
		 * Setup actors
		 */
		
		stage = new Stage(TowerDefense.SCREEN_WIDTH, TowerDefense.SCREEN_HEIGHT, true, TowerDefense.spriteBatch);
		
		stage.addActor(new LevelActor());
		stage.addActor(new TowerButton());
	}

	public void render(float delta)
	{
		TowerDefense.shapeRenderer.begin(ShapeType.Line);
			stage.act(delta);
			stage.draw();
		TowerDefense.shapeRenderer.end();
	}

	public void resize(int width, int height)
	{
		
	}

	public void show()
	{
		Gdx.input.setInputProcessor(stage);
	}

	public void hide()
	{
		
	}

	public void pause()
	{
		
	}

	public void resume()
	{
		
	}

	public void dispose()
	{
		
	}
	
	private class LevelActor extends Actor
	{
		public LevelActor()
		{
			int width = TowerDefense.SCREEN_WIDTH;
			int height = TowerDefense.SCREEN_HEIGHT;
			int tsize = TowerDefense.TILE_SIZE;
			
			setPosition(0.0f, 0.0f);
			setWidth(width);
			setHeight(height - (height % tsize));
			
			addListener(new EventListener()
			{
				@Override
				public boolean handle(Event event)
				{
					InputEvent input = (InputEvent) event;
					if (input.getType() == InputEvent.Type.touchDown)
					{
						int x = (int) (input.getStageX() / TowerDefense.TILE_SIZE);
						int y = (int) (input.getStageY() / TowerDefense.TILE_SIZE);
						Tower tower = new DirectAttackTower();
						
						System.out.format("(%d, %d)\n", x, y);
						curLevel.placeTower(tower, x, y);
					}
					return true;
				}
			});
		}
		
		@Override
		public void act(float delta)
		{
			curLevel.update(delta);
		}
		
		@Override
		public void draw(SpriteBatch batch, float parentAlpha)
		{
			curLevel.draw(batch);
		}
	}
	
	private class TowerButton extends Actor
	{
		private Texture texture = ResourceManager.loadTexture("tower_b.png");

		public TowerButton()
		{
			int height = TowerDefense.SCREEN_HEIGHT;
			int tsize = TowerDefense.TILE_SIZE;
			
			setPosition(200.0f, height - (height % tsize));
			setWidth(texture.getWidth());
			setHeight(texture.getHeight());
			
			addListener(new EventListener()
			{
				@Override
				public boolean handle(Event event)
				{
					InputEvent input = (InputEvent) event;
					if (input.getType() == InputEvent.Type.touchDown)
						System.out.println("TowerButton pressed");
					return true;
				}
			});
		}
		
		@Override
		public void draw(SpriteBatch batch, float parentAlpha)
		{
			batch.draw(texture, getX(), getY());
		}
	}

//	@Override
//	public boolean touchDown(int screenX, int screenY, int pointer, int button)
//	{
//		touchPos.x = screenX;
//		touchPos.y = screenY;
//		touchPos.z = 0;
//
//		camera.unproject(touchPos); // Converts where you touched into pixel
//									// coordinates
//		int x = (int) (touchPos.x) / 128; // Converts to tile coordinates
//		int y = (int) (touchPos.y) / 128; // Converts to tile coordinates
//
//		if (TowerFlag == 0)
//		{
//			if (y < curLevel.getHeight())
//			{
//				switch (button)
//				{
//				case Buttons.LEFT:
//
//					if (x == 4 && y == 0)
//						TowerFlag = 1;
//					else if (x == 5 && y == 0)
//						TowerFlag = 2;
//					else
//						curLevel.selectTower(x, y);
//
//					break;
//
//				case Buttons.RIGHT:
//					curLevel.removeTower(x, y);
//					break;
//
//				}
//			}
//		}
//		else
//		{
//
//			switch (button)
//			{
//			case Buttons.LEFT:
//				TowerGenerator gen = new TowerGenerator() {
//					@Override
//					public Tower newTower()
//					{
//						switch (TowerFlag)
//						{
//						case 1:
//							return new DirectAttackTower();
//						case 2:
//							return new DirectMultiAttackTower();
//						default:
//							return null;
//						}
//					}
//				};
//
//				curLevel.placeTower(gen, x, y);
//				TowerFlag = 0;
//				break;
//			case Buttons.RIGHT:
//				TowerFlag = 0;
//				break;
//			}
//
//		}
//		return true;
//	}
}
