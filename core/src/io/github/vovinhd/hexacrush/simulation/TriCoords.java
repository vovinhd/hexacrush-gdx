package io.github.vovinhd.hexacrush.simulation;

public class TriCoords {
	
	public static final int LEFT = 0; 
	public static final int RIGHT = 1; 
	
	private int x; 
	private int y; 
	private int side;
	
	public TriCoords(){ 
		
	}
	
	public TriCoords(int x, int y, int side) {
		this.x = x; 
		this.y = y; 
		this.setSide(side); 
	}
	
	public TriCoords(TriCoords c) { 
		this.x = c.getX(); 
		this.y = c.getY(); 
		this.setSide(c.getSide()); 
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	@Override
	public String toString() {
		return "TriCoords [x=" + x + ", y=" + y + ", side=" + side + "]";
	}

	
	
}
