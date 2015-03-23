import java.io.*;
import java.util.*;
import java.text.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import Collage.*;

public class Collaginator {
	
	public static void main(String[] args) {
		// Map the command line arguments
		Hashtable<String, String> p_args = ParseArgs(args);
		// Dimensions of the collage 
		int width = 0;
		int height = 0;
		// Other arguments
		Types colltype = null;
		String filename = "";
		String filepath = "";
		// The image created
		BufferedImage the_collage;
		
		// Must map something
		if (p_args.isEmpty()) { Error("Missing command line arguments"); }
		
		// Make sure all needed arguments are here: look width first
		if (p_args.containsKey("width")) {
			width = Integer.parseInt(p_args.get("width"));
		} else {
			Error("No width passed");
		}
		// Look for height
		if (p_args.containsKey("height")) {
			height = Integer.parseInt(p_args.get("height"));
		} else {
			Error("No height passed");
		}
		// Look for a filename
		if (p_args.containsKey("filename")) {
			filename = (String) p_args.get("filename");
		} else {
			filename = GetFileName() + ".png";
		}
		// Look for a file path
		if (p_args.containsKey("filepath")) {
			filepath = (String) p_args.get("filepath");
			filepath = filepath.replace("\\", "/");
			filename = filepath + "/" + filename;
		}
		// Look for the type of collage to make; more types coming
		if (p_args.containsKey("type")) {
			String type = (String) p_args.get("type");
			if (type.toLowerCase() == "square") { colltype = Types.TYPE_SQUARE; }
		} else {
			colltype = Types.TYPE_SQUARE;
		}
		
		// Create the collage based on type
		switch(colltype) {
			case TYPE_SQUARE:
			default:
				CollageSquares cs = new CollageSquares(width, height);
				cs.Create();
				the_collage = cs.GetImage();
				break;
		}
		
		// Save the file
		try {
			ImageIO.write(the_collage, "png", new File(filename));
		} catch (IOException e) {
			Error("Could not save file.");
		}
		
		System.exit(0);
	}

	public static Hashtable<String, String> ParseArgs(String[] args) {
		Hashtable<String, String> p_args = new Hashtable<String, String>();
		String last_arg = "";
		
		// Return args if no args
		if (args.length == 0) { return p_args; }
		
		// Loop through the arguments
		for (int c = 0; c < args.length; c++) {
			if (args[c].startsWith("--")) {
				String temp = args[c].substring(2);
				if (temp.contains("=") == false) {
					last_arg = temp;
				} else {
					String[] p = temp.split("=", 2);
					p_args.put((String) p[0], p[1]);
				}
			}
			else if (args[c].startsWith("-")) {
				String temp = args[c].substring(1);
				if (temp.contains("=") == false) {
					last_arg = temp;
				} else {
					String[] p = temp.split("=", 2);
					p_args.put((String) p[0], p[1]);
				}
			}
			else
			{
				if (last_arg.isEmpty() == false) {
					p_args.put((String) last_arg, args[c]);
					last_arg = "";
				}
			}
		}
		
		// Last check
		if (last_arg.isEmpty() == false) {
			p_args.put((String) last_arg, last_arg);
		}
		
		return p_args;
	}
	
	public static String GetFileName() {
		Date dn = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		return df.format(dn);
	}
	
	public static void Error(String msg) {
		if (msg.isEmpty()) { msg = "Missing message"; }
		System.out.format("ERROR: %s\n", msg);
		System.exit(1);
	}
}
