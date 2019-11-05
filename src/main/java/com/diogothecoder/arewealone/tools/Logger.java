package com.diogothecoder.arewealone.tools;

public class Logger {
	public static void Debug(String message) {
		String method = "";
		try {
			method = Thread.currentThread().getStackTrace()[2].toString();
		} catch (Exception e) {
			// DO NOTHING, LET'S CONTINUE
		} finally {
			System.out.println("[DEBUG: " + method + "]: " + message);
		}
	}
	
	public static void Error(String message) {
		String method = "";
		try {
			method = Thread.currentThread().getStackTrace()[2].toString();
		} catch (Exception e) {
			// DO NOTHING, LET'S CONTINUE
		} finally {
			System.out.println("[ERROR: " + method + "]: " + message);
		}
	}
	
	public static void Info(String message) {
		System.out.println(message);
	}
}
