package io.github.vovinhd.hexacrush.simulation;

public class Simulation {
	
	private volatile GameState gameState; 
	
	public Simulation(){ 
		this.gameState = new GameState(15f); 
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
