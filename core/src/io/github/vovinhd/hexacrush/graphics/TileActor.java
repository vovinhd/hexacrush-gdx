package io.github.vovinhd.hexacrush.graphics;

import io.github.vovinhd.hexacrush.service.AssetService;
import io.github.vovinhd.hexacrush.simulation.Tile;
import io.github.vovinhd.hexacrush.simulation.TriCoords;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TileActor extends Actor {
	private Sprite tileSprite; 
	private TriCoords coords; 
	private Tile t; 
	
	public TileActor(TriCoords triCoords, Tile t) { 
		this.coords = triCoords;
		this.t = t;
		this.tileSprite = getSpriteForTile();
		this.setPosition(coords);
	}
	

	private Sprite getSpriteForTile() {
		Sprite s = null; 
		switch (t) {
		case RED:
			s = AssetService.getRedTri(); break; 
		case YELLOW:
			s = AssetService.getYellowTri(); break; 
		case BLUE:
			s = AssetService.getBlueTri(); break; 
		case BLACK:
			s = AssetService.getBlackTri(); break;
		case GREEN:
			s = AssetService.getGreenTri(); break;
		case PURPLE:
			s = AssetService.getPurpleTri(); break;
		}
		
		if (coords.getSide() == TriCoords.RIGHT) {
			s.flip(true, false);
		}
		
		return s;
	}


	@Override
	public void draw(Batch batch, float parentAlpha) {
		tileSprite.draw(batch);
	}

	
	public void setPosition(TriCoords coords) {
		tileSprite.setPosition(coords.getX(), coords.getY());
		if (this.coords.getSide() != coords.getSide()) {
			tileSprite.flip(true, false);
		}
		this.coords = coords; 
	}

}
