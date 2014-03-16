package com.group23.towerdefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(mTexture, mTexturePosition.x, mTexturePosition.y);
	}
}
