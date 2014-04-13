package com.group23.towerdefense;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.group23.towerdefense.enemy.Enemy;
import com.group23.towerdefense.screen.LevelSelectScreen;
import com.group23.towerdefense.tower.Tower;

public class TowerDefense extends Game
{
	private static final String TAG = "TowerDefense";
	private static final String SAVE_FILENAME = "save.dat";

	public final static int SCREEN_WIDTH = 1920;
	public final static int SCREEN_HEIGHT = 1080;
	public final static int TILE_SIZE = 128; // width and height of a tile

	public static ShapeRenderer shapeRenderer;
	public static SpriteBatch spriteBatch;
	
	public static int curLevel;
	public static int maxLevel = 1;

	private static TowerDefense game;

	@Override
	// Essentially just loads the game
	public void create()
	{	
		game = this;

		// set debug variables
		Tower.DEBUG_DRAWRANGE = true;
		Tower.DEBUG_DRAWTARGET = false;

		// initialize music and play
		Music planning = ResourceManager.loadMusic("Planning.mp3");
		planning.setLooping(true);
		planning.play();

		// initialize textures and fonts
		Enemy.font = ResourceManager.loadDefaultFont();

		// initialize static batches
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		// load the previous game
		load();

		// set the screen to its initial state
		setScreen(new LevelSelectScreen());
	}

	@Override
	// Called when game is killed, unloads everything
	public void dispose()
	{
		super.dispose();
		spriteBatch.dispose();
		ResourceManager.dispose();
	}

	@Override
	public void pause()
	{
		super.pause();
		save();
	}

	static public void changeScreen(Screen screen)
	{
		game.setScreen(screen);
	}

	static public void load()
	{
		try
		{
			DataInputStream inputStream = openSaveInputStream();
			maxLevel = inputStream.readInt();
			inputStream.close();
		}
		catch (FileNotFoundException e)
		{
			Gdx.app.log(TAG, e.getMessage());
		}
		catch (EOFException e)
		{
			Gdx.app.log(TAG, e.getMessage());
		}
		catch (IOException e)
		{
			Gdx.app.log(TAG, e.getMessage());
		}
	}

	static public void save()
	{
		try
		{
			DataOutputStream outputStream = openSaveOutputStream();
			outputStream.writeInt(maxLevel);
			outputStream.close();
		}
		catch (FileNotFoundException e)
		{
			Gdx.app.log(TAG, e.getMessage());
		}
		catch (EOFException e)
		{
			Gdx.app.log(TAG, e.getMessage());	
		}
		catch (IOException e)
		{
			Gdx.app.log(TAG, e.getMessage());
		}
	}

	static private DataInputStream openSaveInputStream()
			throws FileNotFoundException
	{
		return new DataInputStream(new FileInputStream(openSaveFile()));
	}

	static private DataOutputStream openSaveOutputStream()
			throws FileNotFoundException
	{
		return new DataOutputStream(new FileOutputStream(openSaveFile()));
	}

	static private File openSaveFile()
	{
		FileHandle fileHandle = Gdx.files.local(SAVE_FILENAME);
		return fileHandle.file();
	}
}
