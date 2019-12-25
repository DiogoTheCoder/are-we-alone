package com.diogothecoder.arewealone.tools;

import java.util.Scanner;

public class Console {

	static Scanner scanner;

	public static void clear() {  
	    try {
	        final String os = System.getProperty("os.name");
	        if (os.contains("Windows")) {
	        	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        } else {
	            Runtime.getRuntime().exec("clear");
	        }
	    } catch (Exception e) {
			// DO NOTHING, LET'S CONTINUE
	    } finally {
			System.out.println();
		}
	}

	public static String getUserInput() {
		System.out.println("What would you like to do?");

		scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
}
