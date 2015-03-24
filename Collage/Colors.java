package Collage;

import java.awt.Color;

public class Colors {

	public static Color RandomColor() {
		return new Color(GetRandom(), GetRandom(), GetRandom());
	}
	
	public static Color RandomBlue() {
		return new Color(0, 0, GetRandom());
	}
	
	public static Color RandomGreen() {
		return new Color(0, GetRandom(), 0);
	}
	
	public static Color RandomRed() {
		return new Color(GetRandom(), 0, 0);
	}
	
	private static int GetRandom() {
		return (int)((Math.random()*125)*2)+5; 
	}
}
