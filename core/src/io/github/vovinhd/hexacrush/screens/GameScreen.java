package io.github.vovinhd.hexacrush.screens;

import io.github.vovinhd.hexacrush.service.AssetService;
import io.github.vovinhd.hexacrush.simulation.GameState;
import io.github.vovinhd.hexacrush.simulation.Simulation;
import io.github.vovinhd.hexacrush.simulation.Tile;
import io.github.vovinhd.hexacrush.simulation.TriCoords;
import io.github.vovinhd.hexacrush.simulation.TriGrid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class GameScreen extends ScreenAdapter {

	private Simulation simulation = new Simulation(); 
	
	private Matrix3 rotate45; 
	private int gridX; 
	private int gridY; 
	private int triSideLength; 
	float sqrt2 = (float) (1/Math.sqrt(2)); 
	Vector2 shearVecX = new Vector2(sqrt2, sqrt2); 
	Vector2 shearVexY = new Vector2(-sqrt2, sqrt2); 
	
	private SpriteBatch batch; 
	private OrthographicCamera camera; 

	private TextureAtlas atlas = AssetService.getInstance().getTextureAtlas(); 	
	private TextureRegion redTri; 
	private TextureRegion blueTri; 
	private TextureRegion yellowTri; 
	private TextureRegion purpleTri; 
	private TextureRegion greenTri; 
	private TextureRegion blackTri; 
	private TextureRegion background;
	private ImageButton menu; 
	
	@Override
	public void render(float delta) {
		Gdx.graphics.getGL20().glClearColor( 0, 0, 0, 1 );
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

		simulation.act(null); //TODO replace with logic 
		GameState state = simulation.getGameState(); 
		TriGrid triGrid = state.getTileGrid(); 
		Tile[][][] grid = triGrid.getGrid();
		
		batch.begin();
		for (int u = 0; u < triGrid.getSize(); u++) {
			for (int v = 0; v < triGrid.getSize(); v++) {
				Tile[] cell = grid[u][v]; 
				if(cell[TriCoords.LEFT] != null) { 
					renderTri(u, v, true, cell[TriCoords.LEFT]);
				}
				if(cell[TriCoords.RIGHT] != null) { 
					renderTri(u, v, false, cell[TriCoords.RIGHT]);
				}
			}
		}
		batch.end();
		
		super.render(delta);
	}

	private void renderTri(int u, int v, boolean flip, Tile t) {
		float rotation = flip ? 90 : 270; 

		Vector2 pos = new Vector2(); 
		pos.x = gridX; 
		pos.y = gridY; 
				
		pos.x += 70 * (u * shearVecX.x + shearVexY.x * v);
		pos.y += 70 * (u * shearVecX.y + shearVexY.y * v); 
		switch(t) {
		case RED: 
			batch.draw(redTri, pos.x, pos.y, 0, 0, 70, 70, 1, 1, rotation, true); 
			break; 
		case YELLOW: 
			batch.draw(yellowTri, pos.x, pos.y, 0, 0, 70, 70, 1, 1, rotation, true); 
			break; 
		case PURPLE: 
			batch.draw(purpleTri, pos.x, pos.y, 0, 0, 70, 70, 1, 1, rotation, true); 
			break; 
		case GREEN: 
			batch.draw(greenTri, pos.x, pos.y, 0, 0, 70, 70, 1, 1, rotation, true); 
			break; 
		case BLUE: 
			batch.draw(blueTri, pos.x, pos.y, 0, 0, 70, 70, 1, 1, rotation, true); 
			break; 
		case BLACK: 
			batch.draw(blackTri, pos.x, pos.y, 0, 0, 70, 70, 1, 1, rotation, true); 
			break; 
		default: 
			Gdx.app.log("WAT", "WAT");
		}
	}

	@Override
	public void show() {
		
		//init sprites and ui 
		redTri = atlas.findRegion("assets-tri-red"); 
		blueTri = atlas.findRegion("assets-tri-blue"); 
		yellowTri = atlas.findRegion("assets-tri-yellow"); 
		purpleTri = atlas.findRegion("assets-tri-purple"); 
		greenTri = atlas.findRegion("assets-tri-green"); 
		blackTri = atlas.findRegion("assets-tri-black"); 
		background = atlas.findRegion("assets-border");
		
		menu = new ImageButton (new SpriteDrawable(atlas.createSprite("assets-menu")),
				new SpriteDrawable(atlas.createSprite("assets-menu-pressed"))); 
		
		//init render context 
		batch = new SpriteBatch(); 
		camera = new OrthographicCamera(); 
		rotate45 = new Matrix3(); 
		rotate45.setToRotation(135); 
		gridX = Gdx.graphics.getWidth() / 2; 
		gridY = Gdx.graphics.getHeight() / 4; 
			
		super.show();
	}

	public TextureRegion createTri(String texname, boolean flip) {
		 TextureRegion region = atlas.findRegion(texname); 
		 region.flip(flip, false);
		 return region;
	}
	
	@Override
	public void dispose() {
		atlas.dispose();
		batch.dispose();
		super.dispose();
	}
	
	
}
