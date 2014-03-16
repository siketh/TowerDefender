package com.group23.TowerDefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.group23.TowerDefense.Enemy.Enemy;
import com.group23.TowerDefense.Enemy.Enemy1;
import com.group23.TowerDefense.Enemy.Enemy2;
import com.group23.TowerDefense.Level.Level;
import com.group23.TowerDefense.Tower.Tower;
import com.group23.TowerDefense.UI.TopBar;
import com.group23.TowerDefense.UI.TowerBar;

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
		
		// initialize textures and fonts
		Enemy.font     = new BitmapFont();
		TopBar.health_font = new BitmapFont();
		TopBar.gold_font = new BitmapFont();
		
		Enemy1.texture = new Texture(Gdx.files.internal("enemy01.png")); 
		Enemy2.texture = new Texture(Gdx.files.internal("enemy02.png"));
		Tower.texture  = new Texture(Gdx.files.internal("tower00.png"));
		TopBar.texture = new Texture(Gdx.files.internal("Button.png"));
		TopBar.start_b = new Texture(Gdx.files.internal("start_b.png"));
		TopBar.tower_b = new Texture(Gdx.files.internal("tower_b.png"));
		TopBar.health  = new Texture(Gdx.files.internal("health.png"));
		TopBar.gold    = new Texture(Gdx.files.internal("gold.png"));
		
		Level.background = new Texture(Gdx.files.internal("background.png"));
		Level.textures    = new Texture[8];
		Level.textures[0] = new Texture(Gdx.files.internal("tile00.png"));
		Level.textures[1] = new Texture(Gdx.files.internal("tile01.png"));
		Level.textures[2] = new Texture(Gdx.files.internal("tile02.png"));
		Level.textures[3] = new Texture(Gdx.files.internal("tile03.png"));
		Level.textures[4] = new Texture(Gdx.files.internal("tile04.png"));
		Level.textures[5] = new Texture(Gdx.files.internal("tile05.png"));
		Level.textures[6] = new Texture(Gdx.files.internal("tile06.png"));
		Level.textures[7] = new Texture(Gdx.files.internal("tile07.png"));
		
		
		TowerBar.textures = new Texture[2];
		TowerBar.textures[0] = new Texture(Gdx.files.internal("towerbar00.png"));
		TowerBar.textures[1] = new Texture(Gdx.files.internal("towerbar01.png"));
		
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
		Level.background.dispose();
		Tower.texture.dispose();
		TopBar.texture.dispose();
		TopBar.start_b.dispose();;
		TopBar.tower_b.dispose();
		TopBar.health.dispose();
		TopBar.gold.dispose();
		Enemy1.texture.dispose();
		Enemy2.texture.dispose();
		spriteBatch.dispose();
	}
	
	static public void changeScreen(Screen screen)
	{
		game.setScreen(screen);
	}
}
