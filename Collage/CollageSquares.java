package Collage;

import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class CollageSquares implements ICollage {

    BufferedImage f_img;
    BufferedImage n_img;
    int width;
    int height;
    boolean cont;

	@Override
	public void Create(int width, int height) {
		// TODO Auto-generated method stub
		this.width = width;
		this.height = height;
		int color_loop = 0;
		
		// Set cont to true to start loop
		cont = true;
		
		// Loop while true
		while(cont) {
			Color c;
			
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
			case 3:
				c = RandomColor();
				color_loop = 0;				
				break;
			}
		}
	}

	@Override
	public BufferedImage GetImage() {
		return n_img;
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
