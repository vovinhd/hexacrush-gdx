package io.github.vovinhd.hexacrush.simulation;

public class TileDuo {
	private Tile l; 
	private Tile r;
	
	public TileDuo(Tile l, Tile r) {
		this.l = l; 
		this.r = r; 
	}
	public Tile getL() {
		return l;
	}
	public void setL(Tile l) {
		this.l = l;
	}
	public Tile getR() {
		return r;
	}
	public void setR(Tile r) {
		this.r = r;
	} 
	
}
