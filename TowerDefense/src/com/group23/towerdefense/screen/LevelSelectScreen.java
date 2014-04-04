package com.group23.towerdefense.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.group23.towerdefense.TowerDefense;

public class LevelSelectScreen implements Screen, InputProcessor {
	
	private OrthographicCamera camera;
	
	private Vector3 touchPos;
	
	private TowerDefense game;
	
	public LevelSelectScreen(final TowerDefense game)
	{
		
		touchPos = new Vector3();
		
		camera = new OrthographicCamera();
		
		this.game = game;
		
		
	}
	
	@Override
	public void render(float delta) 
	{
		SpriteBatch spriteBatch = TowerDefense.spriteBatch;
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        spriteBatch.setProjectionMatrix(camera.combined);
        
        spriteBatch.begin();
        
        spriteBatch.end();
	}
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchPos.x = screenX;
		touchPos.y = screenY;
		touchPos.z = 0;

		camera.unproject(touchPos); // Converts where you touched into pixel
									// coordinates
		int x = (int) (touchPos.x) / 128; // Converts to tile coordinates
		int y = (int) (touchPos.y) / 128; // Converts to tile coordinates
		
		switch(button){
		
		case Buttons.LEFT:
			
			//if(x <= 4 && x >= 8 && y == 13 )
			
			game.setScreen(new GameplayScreen(1));			
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
