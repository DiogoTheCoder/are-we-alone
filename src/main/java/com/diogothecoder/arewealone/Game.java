package com.diogothecoder.arewealone;

import com.diogothecoder.arewealone.map.Universe;

public class Game {
	private static Universe theUniverse;
	private static Player thePlayer;

	public static void main(String[] args) {
		theUniverse = new Universe();		
		thePlayer = new Player();
		
		getUniverse().getGalaxy().getSolarSystem().displayMap();	
	}
	
	public static Universe getUniverse() {
		return theUniverse;
	}
	
	public static Player getPlayer() {
		return thePlayer;
	}
	
}
