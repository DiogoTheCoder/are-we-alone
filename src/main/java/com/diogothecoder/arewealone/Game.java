package com.diogothecoder.arewealone;

import com.diogothecoder.arewealone.map.Universe;
import com.diogothecoder.arewealone.tools.Console;

public class Game {
	private static Universe theUniverse;
	private static Player thePlayer;

	public static void main(String[] args) {
		theUniverse = new Universe();		
		thePlayer = new Player();

		while (true) {
			getUniverse().getGalaxy().getSolarSystem().display();

			displayPossibleActions();
			// executeAction(Console.getUserInput());
		}
	}
	
	public static Universe getUniverse() {
		return theUniverse;
	}
	
	public static Player getPlayer() {
		return thePlayer;
	}

	private static void displayPossibleActions() {
		thePlayer.getNavigation().getAll();
	}

//	private static void displayNavigationOptions() {
//		System.out.println();
//		thePlayer.getNavigationOptions().forEach((key, value) -> System.out.println(key + " --> " + value));
//		System.out.println();
//	}

//	private static void executeAction(String action) {
//		System.out.println();
//		switch (action.toUpperCase()) {
//			case "N":
//				System.out.println("Northwards we go!");
//				thePlayer.goNorth();
//				break;
//			case "E":
//				System.out.println("Eastwards we go!");
//				thePlayer.goEast();
//				break;
//			case "S":
//				System.out.println("Southwards we go!");
//				thePlayer.goSouth();
//				break;
//			case "W":
//				System.out.println("Westwards we go!");
//				thePlayer.goWest();
//				break;
//		}
//	}
	
}
