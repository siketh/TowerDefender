package com.group23.TowerDefense;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class TowerDefense implements ApplicationListener , InputProcessor {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private int select;
	
	private Texture[] textures;
	private int[] tiles;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		
		Gdx.input.setInputProcessor(this);
		
		select = -1;
		
		batch = new SpriteBatch();
		
		textures = new Texture[4];
		textures[0] = new Texture(Gdx.files.internal("tile00.png"));
		textures[1] = new Texture(Gdx.files.internal("tile01.png"));
		textures[2] = new Texture(Gdx.files.internal("tile02.png"));
		textures[3] = new Texture(Gdx.files.internal("tile03.png"));
		
		Level1.initialize();
		tiles = Level1.tile;
	}

	@Override
	public void dispose() {
		batch.dispose();
		for (Texture t : textures)
			t.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int y = 0; y < Gdx.graphics.getHeight() / 128; y++)
			for (int x = 0; x < Gdx.graphics.getWidth() / 128; x++)
			{
				final int w = Gdx.graphics.getWidth() / 128;
				if( select ==  y * w + x)
				{
					batch.draw(textures[3], x * 128, y * 128);
				}
				else
				{
					batch.draw(textures[tiles[y * w + x]], x * 128, y * 128);
				}
			}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
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

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		 
		 Vector3 worldCoordinates = new Vector3(screenX, screenY, 0);
		 camera.unproject(worldCoordinates);
		 int x  = (int)(worldCoordinates.x) / 128;
		 int y  = (int)(worldCoordinates.y) / 128;
		 select = y * Gdx.graphics.getWidth() / 128 + x;
		 System.out.println(select);
		 
		 
		return true;
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
