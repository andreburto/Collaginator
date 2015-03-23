package Collaginator;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class CollageSquares implements ICollage {

    BufferedImage f_img;
    BufferedImage n_img;
    int width;
    int height;

	@Override
	public void Create(int width, int height) {
		// TODO Auto-generated method stub
		this.width = width;
		this.height = height;
	}

	@Override
	public BufferedImage GetImage() {
		return n_img;
	}
}
