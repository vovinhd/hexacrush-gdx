package io.github.vovinhd.hexacrush.simulation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import static io.github.vovinhd.hexacrush.simulation.TriCoords.*;

public class CoordinateGrid {
	
	private static final float cos45 = (float) Math.cos(Math.PI/4); 
	private static final float sin45 = (float) Math.sin(Math.PI/4); 
	
	private Array<TriCoords> positions; 
	private int triSideLength; 
	private Vector2 offset; 
	private GameState state; 
	 
	public CoordinateGrid(int triSideLength, Vector2 offset, GameState state) {
		this.triSideLength = triSideLength; 
		this.offset = offset; 
		this.state = state;
		this.positions = recalculatePositions(); 
		Gdx.app.log("Coordinates", positions.toString());
	}
	
	private Array<TriCoords> recalculatePositions() {
		Array<TriCoords> positions = new Array<TriCoords>(); 
		int size = this.state.getTileGrid().getSize();
		Tile[][][] grid = this.state.getTileGrid().getGrid(); 
		for (int u = 0; u < size; u++) {
			Vector2 baseLine = new Vector2(u * triSideLength * cos45, u * triSideLength * sin45); 
			for (int v = 0; v < size; v++) {
				if(grid[u][v][LEFT] != null) positions.add(new TriCoords(u * triSideLength, v * triSideLength, TriCoords.LEFT)); 
				if(grid[u][v][RIGHT] != null) positions.add(new TriCoords(u* triSideLength, v * triSideLength + triSideLength / 2,TriCoords.RIGHT)); 
			}
		}
		
		return positions; 
	}

	public Array<TriCoords> getPositions() {
		return positions;
	}

	public void setPositions(Array<TriCoords> positions) {
		this.positions = positions;
	}

	public int getTriSideLength() {
		return triSideLength;
	}

	public void setTriSideLength(int triSideLength) {
		this.triSideLength = triSideLength;
	}

	public Vector2 getOffset() {
		return offset;
	}

	public void setOffset(Vector2 offset) {
		this.offset = offset;
	}

	public int getGridSize() {
		return this.state.getTileGrid().getSize();
	}
}
