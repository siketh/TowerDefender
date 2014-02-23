package com.group23.TowerDefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class GameplayScreen implements Screen, InputProcessor{
	private OrthographicCamera camera;
	public static ShapeRenderer shapeRenderer;
	private SpriteBatch SpriteBatch;						// Canvas that you draw sprites on to
	private Level curLevel;							// Level currently being played
	private Array<Enemy> enemies;					// Enemy on screen
	private Array<Tower> towers;
	private double lastSpawnTime;
	private TowerDefense game;
	
	private Vector3 touchPos;
	
	public GameplayScreen (TowerDefense game, int level)
	{
		this.game = game;
		enemies = new Array<Enemy>();
		towers = new Array<Tower>();
		SpriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		curLevel = new Level();
		lastSpawnTime = 0;
		camera = new OrthographicCamera();				
		camera.setToOrtho(false, TowerDefense.SCREEN_WIDTH, TowerDefense.SCREEN_HEIGHT);
		touchPos = new Vector3();
	}
	
	public void render(float delta) 
	{
		curLevel.update(Gdx.graphics.getDeltaTime());
		camera.update();
		lastSpawnTime += delta;
		if(lastSpawnTime > 2)
		{
			enemies.add(new Enemy(curLevel));
			lastSpawnTime = 0;
		}								
		
		Gdx.gl.glClearColor(1, 1, 1, 1);								// Clears screen 
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);										
		
		SpriteBatch.setProjectionMatrix(camera.combined);						// Don't know
		shapeRenderer.begin(ShapeType.Line);
		SpriteBatch.begin();
		
			curLevel.draw(SpriteBatch);
		
		SpriteBatch.end();
		shapeRenderer.end();				
	}

	
	public void resize(int width, int height) {}

	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	public void hide() {}

	public void pause() {}

	public void resume() {}

	public void dispose() {}

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
		touchPos.x = screenX;
		touchPos.y = screenY;
		touchPos.z = 0;
		
		camera.unproject(touchPos);					// Converts where you touched into pixel coordinates
		int x  = (int)(touchPos.x) / 128;			// Converts to tile coordinates
		int y  = (int)(touchPos.y) / 128;			// Converts to tile coordinates
		curLevel.placeTower(x, y);
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
