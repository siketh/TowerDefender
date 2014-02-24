package com.group23.TowerDefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class LevelSelectScreen implements Screen {
	
	private OrthographicCamera camera;
	private Stage stage;
	private Button level1;
	
	public LevelSelectScreen(final TowerDefense game)
	{
		camera = new OrthographicCamera();
		level1 = new Button();
		stage.addActor(level1);
		level1.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
			{
				game.setScreen(new GameplayScreen(1));
				return true;
			}
		});
	}
	
	@Override
	public void render(float delta) 
	{
		SpriteBatch spriteBatch = TowerDefense.spriteBatch;
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        stage.act();
        
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
	}
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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

}
