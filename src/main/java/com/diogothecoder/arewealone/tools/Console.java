package com.diogothecoder.arewealone.tools;

public class Console {
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
}
