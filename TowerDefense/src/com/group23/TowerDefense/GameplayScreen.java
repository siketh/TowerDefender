package com.group23.TowerDefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.group23.TowerDefense.Level.Level;

public class GameplayScreen implements Screen, InputProcessor{
	
	// Camera defines view space of screen
	private OrthographicCamera camera;
	
	// current level being played
	private Level curLevel;	

	private Vector3 touchPos;
	
	public GameplayScreen(int level)
	{
		// initialize member variables
		curLevel      = new Level();
		touchPos      = new Vector3();
		
		camera = new OrthographicCamera();				
		camera.setToOrtho(false, TowerDefense.SCREEN_WIDTH, TowerDefense.SCREEN_HEIGHT);
	}
	
	public void render(float delta) 
	{		
		// update the current level
		curLevel.update(delta);
		
		SpriteBatch spriteBatch = TowerDefense.spriteBatch;
		ShapeRenderer shapeRenderer = TowerDefense.shapeRenderer;
		
		// update the camera
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		// clear the screen
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// draw to screen
		shapeRenderer.begin(ShapeType.Line);
		spriteBatch.begin();
		
			curLevel.draw(spriteBatch);
		
		spriteBatch.end();
		shapeRenderer.end();			
	}

	
	public void resize(int width, int height) {}

	public void show() 
	{
		Gdx.input.setInputProcessor(this);
	}

	public void hide() {}

	public void pause() {}

	public void resume() {}

	public void dispose() {}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchPos.x = screenX;
		touchPos.y = screenY;
		touchPos.z = 0;
		
		camera.unproject(touchPos);					// Converts where you touched into pixel coordinates
		int x  = (int)(touchPos.x) / 128;			// Converts to tile coordinates
		int y  = (int)(touchPos.y) / 128;			// Converts to tile coordinates
		
		if (y < curLevel.getHeight())
		{
			switch (button)
			{
			case Buttons.LEFT:  curLevel.placeTower(x, y);  break;
			case Buttons.RIGHT: curLevel.removeTower(x, y); break;
			}
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
