package io.github.vovinhd.hexacrush.simulation;

import java.util.Random;

public class TriGrid {
	
	private TileDuo[][] grid; 
	private final int size; 
	private Random random;
	
	public TriGrid(int size) {
		this.size = size; 
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				
			}
		}
	}
	

	private TileDuo createGridEntry() { 
		Tile l = Tile.random(); 
		Tile r = Tile.random(); 
		return new TileDuo(l,r); 
	}
	
	private Tile tileAt(TriCoords c) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Tile getTile(TriCoords c) { 
		return tileAt(c); 
	}


	public TileDuo[][] getGrid() {
		return grid;
	}

	public void setGrid(TileDuo[][] grid) {
		this.grid = grid;
	}

	public int getSize() {
		return size;
	}
	
	
	

}
