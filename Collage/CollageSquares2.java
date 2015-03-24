package Collage;

import java.awt.*;
import java.awt.image.*;
import Collage.Colors;

public class CollageSquares2 implements ICollage {

    BufferedImage f_img;
    int width;
    int height;
    int MAX_SIZE;
    int[][] area;
    boolean cont;

    public CollageSquares2(int width, int height) {
    	this.width = width;
		this.height = height;
		this.MAX_SIZE = (int)Math.floor(height / 10);
    }
    
	@Override
	public void Create() {
		int color_loop = 0;
		
		// Initialize the area array to keep up with used space
		InitializeArea();
		
		// Initialize the image
		BufferedImage n_img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		Graphics g_img = n_img.getGraphics();
		g_img.setColor(Color.black);
		
		// Set cont to true to start loop
		cont = true;
		
		// Loop while true
		while(cont) {
			Color c = null;
			
			// Choose a color
			switch(color_loop) {
			case 0:
				c = Colors.RandomRed();
				color_loop++;
				break;
			case 1:
				c = Colors.RandomGreen();
				color_loop++;
				break;
			case 2:
				c = Colors.RandomBlue();
				color_loop++;
				break;
			default:
				c = Colors.RandomColor();
				color_loop = 0;				
				break;
			}
			
			// Draw a square
			if (IsAvailable()) {
				// Get the top coordinates
				Point start = GetOpenArea();
				int x = (int) start.x;
				int y = (int) start.y;
				// Get the initial size
				int size = GetRandomSize();
				// Get the bottom coordinates
				Point bottom = GetBottomCoords(x, y, size);
				int x2 = (int) bottom.x;
				int y2 = (int) bottom.y;
				// Draw the square
				System.out.format("%s, %s - %s, %s\n", x, y, x2, y2);
				SetTakenArea(x, y, x2, y2);
				g_img.setColor(c);
				g_img.fillRect(x, y, x2, y2); 
			} else {
				cont = false;
			}
		}
		
		// Copy the local image to the finished one
		g_img.finalize();
		this.f_img = n_img;
	}

	@Override
	public BufferedImage GetImage() {
		return this.f_img;
	}
	
	private int GetRandomSize() {
		return (int)(Math.random()*this.MAX_SIZE)+1;
	}
	
	private boolean IsAvailable() {
		boolean yesno = true;
		Point isfree = GetOpenArea();
		if (isfree == null) { yesno = false; }
		return yesno;
	}
	
	private boolean IsAvailable(int x, int y, int x2, int y2) {
		if (this.area[x][y] == 1) { return false; }
		if (this.area[x2][y] == 1) { return false; }
		if (this.area[x][y2] == 1) { return false; }
		if (this.area[x2][y2] == 1) { return false; }
		return true;
	}
	
	private Point GetBottomCoords(int x, int y, int size) {
		Point bottom = new Point();
		int x2 = x;
		int y2 = y;
		for (int sc = 0; sc < size; sc++) {
			x2 = x + sc;
			y2 = y + sc;
			if (x2 < this.width && y2 < this.height) {
				bottom.x = x2;
				bottom.y = y2;
			}
			if (this.area[x2][y2] == 1) { break; }
		}
		return bottom;
	}
	
	private Point GetOpenArea() {
		for (int hc = 0; hc < this.height; hc++) {
			for (int wc = 0; wc < this.width; wc++) {
				if (this.area[wc][hc] == 0) {
					return new Point(wc, hc);
				}
			}
		}
		return null;
	}
	
	private void SetTakenArea(int x, int y, int w, int h) {
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
