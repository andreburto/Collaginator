package Collage;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class CollageYvonne implements ICollage {

    private BufferedImage f_img;
    private int width;
    private int height;

    public CollageYvonne(int width, int height) {
    	this.width = width;
		this.height = height;
    }

	@Override
	public void Create() {
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
			// Draw a square
				if (ca.IsAvailable()) {
				Point start = ca.GetOpenArea();
				//ca.SetTakenArea(start.x, start.y, start.x + size, start.y + size);
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
	
	private void LoadXml() {
		
	}
}
