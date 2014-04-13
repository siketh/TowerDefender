package com.group23.towerdefense.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group23.TweenAccessors.SpriteAccessor;
import com.group23.towerdefense.ResourceManager;
import com.group23.towerdefense.TowerDefense;

public class SplashScreen extends BaseScreen{

	private TweenManager manager;
	private SpriteBatch batcher;
	private Sprite sprite;
	private TowerDefense game;
	
	public SplashScreen(TowerDefense game) {
		this.game = game;
	}
	
	@Override
	public void show()
	{
		sprite = new Sprite(ResourceManager.loadTexture("splash.png"));
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		float desiredWidth = width * .7f;
		float scale = desiredWidth / sprite.getWidth();

		sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
		sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
				- (sprite.getHeight() / 2));
		sprite.setColor(1, 1, 1, 0);
		
		setupEffect();
		batcher = new SpriteBatch();
	}
	
	//Add transition effect to SplashScreen
	private void setupEffect() {
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		manager = new TweenManager();

		TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(new LevelSelectScreen());
			}
		};

		Tween.to(sprite, SpriteAccessor.ALPHA, .8f).target(1)
		.ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f)
		.setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(manager);
	}

	
	@Override
	public void render(float delta) {
		//Delta will determine the time to display the screen
		manager.update(delta/2);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		sprite.draw(batcher);
		batcher.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
