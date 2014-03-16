package com.group23.towerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ArrayMap;

public class ResourceManager
{
	private static ArrayMap<String, Texture> mTextureMap = new ArrayMap<String, Texture>();
		
	private ResourceManager()
	{
		// Class is non-instantiable
	}
	
	public static Texture loadTexture(String filename)
	{
		Texture texture = mTextureMap.get(filename);
		if (texture == null)
		{
			texture = new Texture(Gdx.files.internal(filename));
			mTextureMap.put(filename, texture);
		}
		return texture;
	}
	
	public static void dispose()
	{
		ArrayMap.Values<Texture> textures = mTextureMap.values();
		for (Texture texture : textures)
			texture.dispose();
		mTextureMap.clear();
	}
}
