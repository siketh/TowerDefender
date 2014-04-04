package com.group23.towerdefense.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.group23.towerdefense.ResourceManager;
import com.group23.towerdefense.TowerDefense;

public abstract class BaseScreen implements Screen
{
	private Stage stage;

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height);
	}

	@Override
	public void show()
	{
		int width = TowerDefense.SCREEN_WIDTH;
		int height = TowerDefense.SCREEN_HEIGHT;
		SpriteBatch spriteBatch = TowerDefense.spriteBatch;

		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);

		Viewport viewport = new FillViewport(width, height, camera);
		stage = new Stage(viewport, spriteBatch);
		
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
	
	public Stage getStage()
	{
		return stage;
	}
	
	public static abstract class ImageButton extends Image
	{
		public ImageButton(String imageFilename)
		{
			super(ResourceManager.loadTexture(imageFilename));
			addListener(new InputListener()
			{
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button)
				{
					onPressed();
					return true;
				}
			});
		}
		
		protected abstract void onPressed();
	}
}
