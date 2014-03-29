package com.group23.towerdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.group23.towerdefense.enemy.Enemy;
import com.group23.towerdefense.tower.Tower;

public class TowerDefense extends Game
{
	public final static int SCREEN_WIDTH = 1920;
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
		Tower.DEBUG_DRAWRANGE = true;
		Tower.DEBUG_DRAWTARGET = true;

		// initialize music and play
		Music planning = ResourceManager.loadMusic("Planning.mp3");
		planning.setLooping(true);
		planning.play();

		// initialize textures and fonts
		Enemy.font = ResourceManager.loadDefaultFont();

		Level.background = ResourceManager.loadTexture("background.png");
		Level.textures = new Texture[8];
		Level.textures[0] = ResourceManager.loadTexture("tile00.png");
		Level.textures[1] = ResourceManager.loadTexture("tile01.png");
		Level.textures[2] = ResourceManager.loadTexture("tile02.png");
		Level.textures[3] = ResourceManager.loadTexture("tile03.png");
		Level.textures[4] = ResourceManager.loadTexture("tile04.png");
		Level.textures[5] = ResourceManager.loadTexture("tile05.png");
		Level.textures[6] = ResourceManager.loadTexture("tile06.png");
		Level.textures[7] = ResourceManager.loadTexture("tile07.png");

		// initialize static batches
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		// set the screen to its initial state
		setScreen(new GameplayScreen(1));
	}

	@Override
	// Called when game is killed, unloads everything
	public void dispose()
	{
		spriteBatch.dispose();
		ResourceManager.dispose();
	}

	static public void changeScreen(Screen screen)
	{
		game.setScreen(screen);
	}
}
