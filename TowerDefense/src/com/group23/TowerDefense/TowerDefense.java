package com.group23.TowerDefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TowerDefense extends Game 
{
	public final static int SCREEN_WIDTH  = 1920;
	public final static int SCREEN_HEIGHT = 1080;
	public final static int TILE_SIZE = 128; // width and height of a tile
	
	public static ShapeRenderer shapeRenderer;
	public static SpriteBatch spriteBatch;
	
	private static TowerDefense game;
	
	@Override
	// Essentially just loads the game
	public void create() 
	{		
		game = this;
		
		// set debug variables
		Tower.DEBUG_DRAWRANGE  = true;
		Tower.DEBUG_DRAWTARGET = true;
		
		// initialize textures
		Enemy.font    = new BitmapFont();
		Enemy.texture = new Texture(Gdx.files.internal("enemy00.png"));
		Tower.texture = new Texture(Gdx.files.internal("tower00.png"));
		
		Level.textures    = new Texture[4];
		Level.textures[0] = new Texture(Gdx.files.internal("tile00.png"));
		Level.textures[1] = new Texture(Gdx.files.internal("tile01.png"));
		Level.textures[2] = new Texture(Gdx.files.internal("tile02.png"));
		Level.textures[3] = new Texture(Gdx.files.internal("tile03.png"));
		
		// initialize static batches
		spriteBatch   = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		// set the screen to its initial state
		setScreen(new GameplayScreen(1));
	}

	@Override
	// Called when game is killed, unloads everything
	public void dispose() 
	{
		for (Texture t : Level.textures)
			t.dispose();
		Enemy.texture.dispose();
		spriteBatch.dispose();
	}
	
	static public void changeScreen(Screen screen)
	{
		game.setScreen(screen);
	}
}
