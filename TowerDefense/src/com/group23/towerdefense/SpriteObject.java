package com.group23.towerdefense;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class SpriteObject extends TextureObject
{
	private Sprite mSprite;
	
	public void setTexture(String filename)
	{
		super.setTexture(filename);
		mSprite = new Sprite(getTexture());
		mSprite.setOriginCenter();
	}
	
	public void setTexturePosition(float x, float y)
	{
		mSprite.setPosition(x, y);
	}
	
	public void setTexturePosition(Vector2 pos)
	{
		mSprite.setPosition(pos.x, pos.y);
	}
	
	public Vector2 getTexturePosition()
	{
		return new Vector2(mSprite.getX(), mSprite.getY());
	}
	
	public void draw(Batch batch)
	{
		if (mSprite != null)
			mSprite.draw(batch);
	}
	
	public void setRotation(Vector2 dir)
	{
		 mSprite.setRotation((float) (Math.atan2(dir.y,dir.x)/Math.PI*180));
	}
}
