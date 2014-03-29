package com.group23.TowerDefense;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.group23.towerdefense.TowerDefense;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "TowerDefense";
		cfg.useGL30 = false;
		cfg.width = 1920 / 2;
		cfg.height = 1080 / 2;
		
		new LwjglApplication(new TowerDefense(), cfg);
	}
}
