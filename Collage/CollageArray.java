package Collage;

import java.awt.Point;

public class CollageArray {
    private int width;
    private int height;
    private int[][] area;

    public CollageArray(int width, int height) {
    	// Operational dimensions
    	this.width = width;
		this.height = height;
		// Initialize the area array
		InitializeArea();
    }
    
	public boolean IsAvailable() {
		boolean yesno = true;
		Point isfree = GetOpenArea();
		if (isfree == null) { yesno = false; }
		return yesno;
	}
	
	public Point GetOpenArea() {
		for (int hc = 0; hc < this.height; hc++) {
			for (int wc = 0; wc < this.width; wc++) {
				if (this.area[wc][hc] == 0) {
					return new Point(wc, hc);
				}
			}
		}
		return null;
	}
	
	public void SetTakenArea(int x, int y, int w, int h) {
		int ew = x + w;
		int eh = y + h;
		// Check height and width
		if (ew > this.width) { ew = this.width; }
		if (eh > this.height) { eh = this.height; }  
		// Loop
		for (int sh = y; sh < eh; sh++) {
			for (int sw = x; sw < ew; sw++) {
				this.area[sw][sh] = 1;
			}
		}
	}
	
	private void InitializeArea() {
		this.area = new int[this.width][this.height];
		for (int hc = 0; hc < this.height; hc++) {
			for (int wc = 0; wc < this.width; wc++) {
				this.area[wc][hc] = 0;
			}
		}
	}
}
