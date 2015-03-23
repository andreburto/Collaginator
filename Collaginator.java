import java.io.*;
import java.util.*;

import Collage.*;

public class Collaginator {
	
	public static void main(String[] args) {
		
		// Map the command line arguments
		Hashtable<String, String> p_args = ParseArgs(args);
		
		// Must map something
		if (p_args.isEmpty()) { System.exit(1); }
		
		Object[] keys = p_args.keySet().toArray();
		
		for (int c = 0; c < p_args.size(); c++) {
			System.out.format("%s: %s\n", (String) keys[c], p_args.get(keys[c]));
		}
	}

	public static Hashtable<String, String> ParseArgs(String[] args) {
		Hashtable<String, String> p_args = new Hashtable<String, String>();
		String last_arg = "";
		
		if (args.length == 0) { return p_args; }
		
		// Loop through the arguments
		for (int c = 0; c < args.length; c++) {
			if (args[c].startsWith("--")) {
				String temp = args[c].substring(2);
				if (temp.contains("=") == false) {
					last_arg = temp;
				} else {
					String[] p = temp.split("=", 2);
					p_args.put(p[0], p[1]);
				}
			}
			else if (args[c].startsWith("-")) {
				String temp = args[c].substring(1);
				if (temp.contains("=") == false) {
					last_arg = temp;
				} else {
					String[] p = temp.split("=", 2);
					p_args.put(p[0], p[1]);
				}
			}
			else
			{
				if (last_arg.isEmpty() == false) {
					p_args.put(last_arg, args[c]);
				}
			}
		}
		
		// Last check
		if (last_arg.isEmpty() == false) {
			p_args.put(last_arg, last_arg);
		}
		
		return p_args;
	} 
}
