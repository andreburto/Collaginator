import java.io.*;
import java.util.*;

import Collage.*;

public class Collaginator {
	
	public static void main(String[] args) {
		// Must have arguments of some kind
		if (args.length == 0) { System.exit(1); }
		
		// Must have an even number of arguments
		if (args.length % 2 > 0) { System.exit(1); } 
		
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
		
		// Loop through the arguments
		for (int c = 0; c < args.length; c++) {
			if (args[c].startsWith("--")) {
				last_arg = args[c].substring(2);
			}
			else if (args[c].startsWith("-")) {
				last_arg = args[c].substring(1);
			}
			else
			{
				if (last_arg.isEmpty() == false) {
					p_args.put(last_arg, args[c]);
					last_arg = "";
				}
			}
		}
		
		return p_args;
	} 
}
