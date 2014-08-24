package io.github.vovinhd.hexacrush.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetService {

	private TextureAtlas textureAtlas; 
	
	private static AssetService instance; 
	
	private AssetService() {
		textureAtlas = new TextureAtlas(Gdx.files.internal("assets.atlas")); 
	} 
	
	public static AssetService getInstance() {
		if (instance == null) {
			instance = new AssetService(); 
			return instance; 
		} else {
			return instance; 
		}
	}
	
	public TextureAtlas getTextureAtlas() {
		return textureAtlas;  
	}
	
}
