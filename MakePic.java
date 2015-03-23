import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class MakePic {
    
    static BufferedImage f_img;
    static BufferedImage n_img;
    static String filename;
    static int width;
    static int height;
    
    public static void main(String[] args) {
        
        try {
            filename = args[0];
            f_img = ImageIO.read(new File(filename));
        } catch (IOException e) {
            Error("Could not open file");
        }
        
        width = 200;
        height = 200;
               
        n_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g_img = n_img.getGraphics();
        
        int xp[] = {0, 0, width-1};
        int yp[] = {0, height-1, height-1};
        
       g_img.setColor(Color.black);
       g_img.drawImage(f_img, 0, 0, width, height, Color.black, null);
       g_img.setColor(Color.blue);
       g_img.fillPolygon(xp, yp, 3); 
       //g_img.drawLine(0, 0, width, height);
       
       try {
    	   ImageIO.write(n_img, "png", new File("test.png"));
       } catch (IOException e) {
    	   Error("Could not save file.");
       }
    }
    
    public static void Error(String msg) {
        System.out.println(msg);
        System.exit(0);
    }
}