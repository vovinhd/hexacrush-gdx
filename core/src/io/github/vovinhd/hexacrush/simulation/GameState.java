package io.github.vovinhd.hexacrush.simulation;

public class GameState {
	private TriGrid tileGrid; 
	private float timePerTurn = 15.0f; 
	private int score = 0;
	private int turns = 0; 
	private float time  = 0.0f; 
	private boolean paused = false;
	
	public GameState(float timePerTurn, int gridSize) {
		this.timePerTurn = timePerTurn; 
		this.tileGrid = new TriGrid(gridSize); 
	}
	public TriGrid getTileGrid() {
		return tileGrid;
	}
	public void setTileGrid(TriGrid tileGrid) {
		this.tileGrid = tileGrid;
	}
	public float getTimePerTurn() {
		return timePerTurn;
	}
	public void setTimePerTurn(float timePerTurn) {
		this.timePerTurn = timePerTurn;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getTurns() {
		return turns;
	}
	public void setTurns(int turns) {
		this.turns = turns;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public boolean isPaused() {
		return paused;
	}
	public void setPaused(boolean paused) {
		this.paused = paused;
	} 
	
	
	
}
