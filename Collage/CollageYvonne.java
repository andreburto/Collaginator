package Collage;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

public class CollageYvonne implements ICollage {

    BufferedImage f_img;
    int width;
    int height;
    int[][] area;
    boolean cont;

    public CollageYvonne(int width, int height) {
    	this.width = width;
		this.height = height;
    }

	@Override
	public void Create() {
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
				Point start = GetOpenArea();
				int size = GetRandomSize();
				SetTakenArea(start.x, start.y, start.x + size, start.y + size);
				g_img.setColor(c);
				g_img.fillRect((int) start.x, (int) start.y,
						(int) start.x + size, (int) start.y + size); 
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
		// TODO Auto-generated method stub
		return this.f_img;
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
