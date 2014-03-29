package com.group23.towerdefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class TextureObject
{
	private Texture mTexture;
	private Vector2 mTexturePosition = new Vector2();
	
	public void setTexture(String filename)
	{
		mTexture = ResourceManager.loadTexture(filename);
	}
	
	public Texture getTexture()
	{
		return mTexture;
	}
	
	public void setTexturePosition(float x, float y)
	{
		mTexturePosition.x = x;
		mTexturePosition.y = y;
	}
	
	public void setTexturePosition(Vector2 pos)
	{
		mTexturePosition = pos;
	}
	
	public Vector2 getTexturePosition()
	{
		return mTexturePosition;
	}
	
	public void draw(Batch batch)
	{
		if (mTexture != null)
			batch.draw(mTexture, mTexturePosition.x, mTexturePosition.y);
	}
	
	protected void updateTexturePosition(Vector2 pos)
	{
		Texture texture = getTexture();
		if (texture != null)
		{
			int texWidth = texture.getWidth();
			int texHeight = texture.getHeight();
			
			setTexturePosition(pos.x - texWidth / 2, pos.y - texHeight / 2);
		}
	}
}
