package Collage;

import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class CollageSquares implements ICollage {

    BufferedImage f_img;
    int width;
    int height;
    int MAX_SIZE;
    int[][] area;
    boolean cont;

    public CollageSquares(int width, int height) {
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
				System.out.format("%s, %s - %s\n", start.x, start.y, size);
				SetTakenArea(start.x, start.y, start.x + size, start.y + size);
				//g_img.setColor(c);
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
	
	private Point GetOpenArea() {
		for (int wc = 0; wc < this.width; wc++) {
			for (int hc = 0; hc < this.height; hc++) {
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
		for (int sw = x; sw < ew; sw++) {
			for (int sh = y; sh < eh; sh++) {
				this.area[sw][sh] = 1;
			}
		}
	}
	
	private void InitializeArea() {
		this.area = new int[this.width][this.height];
		for (int wc = 0; wc < this.width; wc++) {
			for (int hc = 0; hc < this.height; hc++) {
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
