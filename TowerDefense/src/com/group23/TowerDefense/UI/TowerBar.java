package com.group23.TowerDefense.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.group23.TowerDefense.Dir;
import com.group23.TowerDefense.Level.Level;
import com.group23.TowerDefense.TowerDefense;


public class TowerBar {

	public static BitmapFont font       = null;
	public static Texture[] textures;
	private static final int texWidth  = 132;
	private static final int texHeight = 132;
	
	
	
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(textures[0], 512, 0);
		for(int i = 5; i < 9; i++){
			batch.draw(textures[1], i * 128, 0);
		}
		
		
	}

}
