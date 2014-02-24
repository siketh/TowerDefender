package com.group23.TowerDefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
	
	public static Texture texture = null;
	public static Texture start_b = null;
	public static Texture tower_b = null;
	public static Texture health = null;
	public static Texture gold = null;
	private static final int texWidth  = 180;
	private static final int texHeight = 80;
	

public void draw(SpriteBatch batch){
	batch.draw(start_b, 0, 1000, texWidth, texHeight);
	batch.draw(tower_b, 200, 1000, texWidth, texHeight);
	batch.draw(gold, 1300, 1020, 60, 60);
	batch.draw(health, 1500, 1020, 60, 60);
}

}