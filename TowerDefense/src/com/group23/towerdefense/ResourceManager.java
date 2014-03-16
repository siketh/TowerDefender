package com.group23.towerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ResourceManager
{
	private static final String TAG = "ResourceManager";
	private static ArrayMap<String, Texture> mTextureMap = new ArrayMap<String, Texture>();
		
	private ResourceManager()
	{
		// Class is non-instantiable
	}
	
	public static Texture loadTexture(String filename)
	{
		try
		{
			Texture texture = mTextureMap.get(filename);
			if (texture == null)
			{
				texture = new Texture(Gdx.files.internal(filename));
				mTextureMap.put(filename, texture);
			}
			return texture;
		}
		catch (GdxRuntimeException e)
		{
			Gdx.app.log(TAG, e.getMessage());
			return null;
		}
	}
	
	public static void dispose()
	{
		ArrayMap.Values<Texture> textures = mTextureMap.values();
		for (Texture texture : textures)
			texture.dispose();
		mTextureMap.clear();
	}
}
