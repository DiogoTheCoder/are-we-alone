package com.diogothecoder.arewealone;

import java.util.LinkedHashMap;
import java.util.Random;

import com.diogothecoder.arewealone.actions.Navigation;
import com.diogothecoder.arewealone.map.*;
import com.diogothecoder.arewealone.tools.Logger;

public class Player {
	public static char MAP_KEY = 'X';

	protected static Random random;
	
	private Position universePosition;
	private Position galaxyPosition;
	private Position solarSystemPosition;

	private Universe currentUniverse;
	private Galaxy currentGalaxy;
	private SolarSystem currentSolarSystem;

	private Navigation navigation;
	
	public Player() {
		Logger.Debug("Creating player...");
		random = new Random();

		this.currentUniverse = Game.getUniverse();
		if (this.currentUniverse == null) {
			Logger.Error("Cannot find universe!");
			return;
		}

		this.universePosition = getRandomPos(this.currentUniverse.getMap());
		this.currentGalaxy = this.currentUniverse.getGalaxy(this.universePosition);
		if (this.currentUniverse == null) {
			Logger.Error("Cannot find galaxy!");
			return;
		}

		this.galaxyPosition = getRandomPos(this.currentGalaxy.getMap());
		this.currentSolarSystem = this.currentGalaxy.getSolarSystem(this.galaxyPosition);
		if (this.currentUniverse == null) {
			Logger.Error("Cannot find solar system!");
			return;
		}

		this.solarSystemPosition = getRandomPos(this.currentSolarSystem.getMap());

		this.navigation = new Navigation();
	}
	
	private Position getRandomPos(String[][] map) {
		while (true) {
			int randomPosX = random.nextInt(map.length);
			int randomPosY = random.nextInt(map.length);
			
			if (map[randomPosX][randomPosY] != null
					&& !map[randomPosX][randomPosY].equals("B")
					&& !map[randomPosX][randomPosY].equals(Character.toString(Star.MAP_KEY))
					&& !map[randomPosX][randomPosY].equals(Character.toString(Planet.MAP_KEY))
			) {
				return new Position(randomPosX, randomPosY);
			}
		}
	}

	public Position getUniversePos() {
		return this.universePosition;
	}
	
	public Position getGalaxyPos() {
		return this.galaxyPosition;
	}

	public Position getSolarSystemPos() {
		return this.solarSystemPosition;
	}
	
	public void displayPos(Position pos) {
		Logger.Info("The Player is at Coordinate (X, Y): (" + pos.getX() + ", " + pos.getY() + ")");
	}

	protected Navigation getNavigation() {
		return this.navigation;
//		LinkedHashMap<Character, String> navigationOptions = new LinkedHashMap<>();
//
//		// Is there anything North of us?
//		if (this.currentSolarSystem.isEmptyAt(this.solarSystemPosition.getX(), this.solarSystemPosition.getY() - 1)) {
//			navigationOptions.put('N', "Accelerate Northwards");
//		}
//
//		// What about East?
//		if (this.currentSolarSystem.isEmptyAt(this.solarSystemPosition.getX() + 1, this.solarSystemPosition.getY())) {
//			navigationOptions.put('E', "Accelerate Eastwards");
//		}
//
//		// And how about South?
//		if (this.currentSolarSystem.isEmptyAt(this.solarSystemPosition.getX(), this.solarSystemPosition.getY() + 1)) {
//			navigationOptions.put('S', "Accelerate Southwards");
//		}
//
//		// Finally, what about West?
//		if (this.currentSolarSystem.isEmptyAt(this.solarSystemPosition.getX() - 1, this.solarSystemPosition.getY())) {
//			navigationOptions.put('W', "Accelerate Westwards");
//		}
//
//		return navigationOptions;
	}
}
