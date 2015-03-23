import java.io.*;
import java.util.*;
import Collage.*;

public class Collaginator {
	
	public static void main(String[] args) {
		Console c = System.console();
		if (c == null) { System.exit(1); }
		
		String number = String.valueOf(args.length);
		System.out.println(number);

		String x = c.readLine();
	}

	public static Hashtable<String, String> ParseArgs(String[] args) {
		Hashtable<String, String> p_args = new Hashtable<String, String>();
		
		return p_args;
	} 
}
