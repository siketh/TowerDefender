package com.group23.towerdefense.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TowerBar
{

	public static BitmapFont font = null;
	public static Texture[] textures;

	public void draw(SpriteBatch batch)
	{
		batch.draw(textures[0], 512, 0);
		batch.draw(textures[1], 640, 0);
		for (int i = 6; i < 9; i++)
		{
			batch.draw(textures[2], i * 128, 0);
		}

	}

}
