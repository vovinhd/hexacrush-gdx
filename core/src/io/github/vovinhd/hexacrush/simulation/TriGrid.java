package io.github.vovinhd.hexacrush.simulation;

public class TriGrid {

	
	private Tile[][][] grid; 
	private final int size; 
	
	public TriGrid(int size) {
		this.size = size; 
		grid = new Tile[size][size][2]; 
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				grid[i][j] = createGridEntry(i,j,size); 
			}
		}
	}
	

	/** 
	 * Creates the tiles. Tiles with an index of (size + 1) / 2 lay on the border of the hexagonal shape, so only one of their fields is filled.
	 * All tiles beyond that are outside of the hex-area and are completely filled with Tile.DISABLED 
	 */
	private Tile[] createGridEntry(int x, int y, int size) { 
		boolean rOut = ((x + 1) / size >= size / 2)||(y / size >= size / 2); 
		boolean lOut = (x / size >= size / 2)||((y + 1)/ size >= size / 2);
		Tile l = (lOut ? null : Tile.random()); 
		Tile r = (rOut ? null : Tile.random()); 
		return new Tile[] {l,r}; 
	}
	

	
	public Tile getTile(TriCoords c) { 
		return getTile(c.getX(), c.getY(), c.getSide()); 
	}



	public Tile getTile(int x, int y, int side) {
		return grid[x][y][side];
	}


	public int getSize() {
		return size;
	}


	public Tile[][][] getGrid() {
		return grid;
	}


	public void setGrid(Tile[][][] grid) {
		this.grid = grid;
	}
	
	
	

}
