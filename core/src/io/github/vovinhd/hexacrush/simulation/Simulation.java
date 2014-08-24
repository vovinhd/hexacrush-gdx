package io.github.vovinhd.hexacrush.simulation;

import io.github.vovinhd.hexacrush.graphics.TileActor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Simulation extends Stage {
	
	private volatile GameState gameState; 
	private CoordinateGrid grid; 
	private int gridSize = 8;
	private int triSideLength = 70; 
	private Vector2 offset = new Vector2(0.0f,0.0f); 
	
	
	public Simulation(Viewport viewport, SpriteBatch batch){ 
		super(viewport, batch); 
		this.gameState = new GameState(15f, gridSize); 
		this.grid = new CoordinateGrid(triSideLength, offset, gameState); 
		
		for (TriCoords coord : grid.getPositions()) {
			this.addActor(new TileActor(coord, Tile.random()));
		}
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta); 
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

}
