package io.github.vovinhd.hexacrush.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetService {

	private TextureAtlas textureAtlas; 
	
	private static AssetService instance; 
	
	private AssetService() {
		textureAtlas = new TextureAtlas(Gdx.files.internal("assets.atlas")); 
		for(Texture t : textureAtlas.getTextures()){ 
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	} 
	
	private static AssetService getInstance() {
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
	
	public static Sprite getRedTri() {
		return new Sprite(getInstance().textureAtlas.findRegion("assets-tri-red"));
	}
	
	public static Sprite getYellowTri() {
		return new Sprite(getInstance().textureAtlas.findRegion("assets-tri-yellow"));
	}
	
	public static Sprite getBlueTri() {
		return new Sprite(getInstance().textureAtlas.findRegion("assets-tri-blue"));
	}
	
	public static Sprite getBlackTri() {
		return new Sprite(getInstance().textureAtlas.findRegion("assets-tri-black"));
	}
	
	public static Sprite getGreenTri() {
		return new Sprite(getInstance().textureAtlas.findRegion("assets-tri-green"));
	}	
	
	public static Sprite getPurpleTri() {
		return new Sprite(getInstance().textureAtlas.findRegion("assets-tri-purple"));
	}
}
