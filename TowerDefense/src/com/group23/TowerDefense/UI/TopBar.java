package com.group23.TowerDefense.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group23.TowerDefense.Level.Level;

public class TopBar {

	public static BitmapFont health_font = null;
	public static BitmapFont gold_font = null;
	
	public static Texture texture = null;
	public static Texture start_b = null;
	public static Texture tower_b = null;
	public static Texture health = null;
	public static Texture gold = null;
	private static final int texWidth = 180;
	private static final int texHeight = 60;
	
	
	//Drawing Function
	public void draw(SpriteBatch batch, Level level) {
		batch.draw(start_b, 0, 1020, texWidth, texHeight);
		batch.draw(tower_b, 200, 1020, texWidth, texHeight);
		batch.draw(gold, 1300, 1020, 60, texHeight);
		batch.draw(health, 1500, 1020, 60, texHeight);
		
	//Draw Health
		health_font.setScale(2.5f);
		health_font.setColor(Color.WHITE);
		health_font.draw(batch, String.format("%d", level.getLives()), 1580, 1070);
		
	//Draw Gold
		health_font.setScale(2.5f);
		health_font.setColor(Color.YELLOW);
		health_font.draw(batch, String.format("%d", level.getGold()), 1400, 1070);
	}

		
}