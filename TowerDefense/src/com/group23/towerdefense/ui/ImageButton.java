package com.group23.towerdefense.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.group23.towerdefense.ResourceManager;

public abstract class ImageButton extends Image
{
	public ImageButton(String imageFilename)
	{
		super(ResourceManager.loadTexture(imageFilename));
		addListener(new InputListener()
		{
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button)
			{
				onPressed();
				return true;
			}
		});
	}
	
	protected abstract void onPressed();
}
