package Collage;

import java.awt.*;
import java.awt.image.*;
import Collage.Colors;
import Collage.CollageArray;

public class CollageSquares implements ICollage {

    private BufferedImage f_img;
    private int width;
    private int height;
    private int MAX_SIZE;

    public CollageSquares(int width, int height) {
    	this.width = width;
		this.height = height;
		this.MAX_SIZE = (int)Math.floor(height / 10);
    }
    
	@Override
	public void Create() {
		int color_loop = 0;
		
		// Initialize the area array to keep up with used space
		CollageArray ca = new CollageArray(this.width, this.height);
		
		// Initialize the image
		BufferedImage n_img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		Graphics g_img = n_img.getGraphics();
		g_img.setColor(Color.black);
		
		// Set cont to true to start loop
		boolean cont = true;
		
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
				if (ca.IsAvailable()) {
				Point start = ca.GetOpenArea();
				int size = GetRandomSize();
				ca.SetTakenArea(start.x, start.y, start.x + size, start.y + size);
				g_img.setColor(c);
				g_img.fillRect((int) start.x, (int) start.y, (int) start.x + size, (int) start.y + size); 
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
	
}
