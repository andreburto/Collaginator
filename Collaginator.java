import java.io.*;
import java.util.*;

import Collage.*;

public class Collaginator {
	
	static CollageSquares cs;
	
	public static void main(String[] args) {
		// Map the command line arguments
		Hashtable<String, String> p_args = ParseArgs(args);
		int width = 0;
		int height = 0;
		String type = "";
		
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
		
		cs = new CollageSquares(width, height);
		cs.Create();
		
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
	
	public static void Error(String msg) {
		if (msg.isEmpty()) { msg = "Missing message"; }
		System.out.format("ERROR: %s\n", msg);
		System.exit(1);
	}
}
