package Collage;

import java.awt.*;
import java.awt.image.*;

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
		BufferedImage n_img = new BufferedImage(this.width, this.height,
				BufferedImage.TYPE_INT_ARGB);
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
				c = RandomRed();
				color_loop++;
				break;
			case 1:
				c = RandomGreen();
				color_loop++;
				break;
			case 2:
				c = RandomBlue();
				color_loop++;
				break;
			default:
				c = RandomColor();
				color_loop = 0;				
				break;
			}
			
			// Draw a square
			if (IsAvailable()) {
				Point start = GetOpenArea();
				int size = GetRandomSize();
				int x = (int) start.x;
				int y = (int) start.y;
				int x2 = start.x + size;
				int y2 = start.y + size;
				while (IsAvailable(x, y) == false) {
					System.out.format("1: %s, %s\n", x, y);
					x += 1;
					y += 1;
				}
				while (IsAvailable(x2, y2) == false) {
					System.out.format("2: %s, %s\n", x2, y2);
					x2 -= 1;
					y2 -= 1;
					if (x2 == x && y2 == y) {
						x2 = x + 1;
						y2 = y + 1;
						break;
					}
				}
				while (IsAvailable(x, y2) == false) {
					System.out.format("3: %s, %s\n", x2, y2);
					x += 1;
					y2 -= 1;
				}
				while (IsAvailable(x2, y) == false) {
					System.out.format("4: %s, %s\n", x2, y2);
					x2 -= 1;
					y += 1;
				}
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
	
	private boolean IsAvailable(int x, int y) {
		if (x <= 0 || x == this.width - 1) { return true; }
		if (y <= 0 || y == this.height - 1) { return true; }
		if (this.area[x][y] == 1) {
			return false;
		} else {
			return true;
		}
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
	
	private Color RandomColor() {
		return new Color(GetRandom(), GetRandom(), GetRandom());
	}
	
	private Color RandomBlue() {
		return new Color(0, 0, GetRandom());
	}
	
	private Color RandomGreen() {
		return new Color(0, GetRandom(), 0);
	}
	
	private Color RandomRed() {
		return new Color(GetRandom(), 0, 0);
	}
	
	private int GetRandom() {
		return (int)((Math.random()*125)*2)+5; 
	}
}
