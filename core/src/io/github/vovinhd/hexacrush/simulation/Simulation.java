package io.github.vovinhd.hexacrush.simulation;

public class Simulation {
	
	private volatile GameState gameState; 
	
<<<<<<< HEAD
	public Simulation(Viewport viewport, SpriteBatch batch){ 
		super(viewport, batch); 
		this.gameState = new GameState(15f, gridSize); 
		this.grid = new CoordinateGrid(triSideLength, offset, gridSize); 
		
		for (TriCoords coord : grid.getPositions()) {
			this.addActor(new TileActor(coord, Tile.random()));
		}
		
=======
	public Simulation(){ 
		this.gameState = new GameState(15f); 
>>>>>>> parent of e2cd7e9... New Graphics stack ftw
	}
	
	public void act(SimulationEvent e) {
		
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

}
