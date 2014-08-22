package io.github.vovinhd.hexacrush.simulation;

public class TriCoords {
	private int x; 
	private int y; 
	private Side s;
	
	public enum Side{
		LEFT,
		RIGHT
	}
	
	public TriCoords(){ 
		
	}
	
	public TriCoords(int x, int y, Side s) {
		this.x = x; 
		this.y = y; 
		this.s = s; 
	}
	
	public TriCoords(TriCoords c) { 
		this.x = c.getX(); 
		this.y = c.getY(); 
		this.s = c.getS(); 
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

	public Side getS() {
		return s;
	}

	public void setS(Side s) {
		this.s = s;
	}

	
}
