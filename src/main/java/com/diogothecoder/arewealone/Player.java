package com.diogothecoder.arewealone;

import java.util.Random;

import com.diogothecoder.arewealone.actions.Navigation;
import com.diogothecoder.arewealone.map.*;
import com.diogothecoder.arewealone.tools.Logger;
import com.diogothecoder.arewealone.tools.exceptions.MapNotFound;

public class Player {
	private static Player instance;

	public static char MAP_KEY = 'X';

	protected static Random random;

	private Navigation navigation;

	public static Player getInstance() {
		if (instance == null) {
			try {
				instance = new Player();
			} catch (MapNotFound mapNotFound) {
				mapNotFound.printStackTrace();
			}
		}

		return instance;
	}
	
	private Player() throws MapNotFound {
		Logger.Debug("Creating player...");
		random = new Random();

		Universe currentUniverse = Game.getUniverse();
		if (currentUniverse == null) {
			throw new MapNotFound();
		}

		Position universePosition = getRandomPos(currentUniverse.getMap());
		Game.getUniverse().setPlayerPosition(universePosition);

		currentUniverse = Game.getUniverse();

		Galaxy currentGalaxy = currentUniverse.getGalaxy(universePosition);
		if (currentGalaxy == null) {
			throw new MapNotFound();
		}

		Position galaxyPosition = getRandomPos(currentGalaxy.getMap());
		Game.getUniverse().getGalaxy(universePosition).setPlayerPosition(galaxyPosition);

		currentGalaxy = Game.getUniverse().getGalaxy(universePosition);

		SolarSystem currentSolarSystem = currentGalaxy.getSolarSystem(galaxyPosition);
		if (currentSolarSystem == null) {
			throw new MapNotFound();
		}

		Position solarSystemPosition = getRandomPos(currentSolarSystem.getMap());
		Game.getUniverse().getGalaxy(universePosition).getSolarSystem(galaxyPosition).setPlayerPosition(solarSystemPosition);

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

	public Navigation getNavigation() {
		return this.navigation;
	}
}
