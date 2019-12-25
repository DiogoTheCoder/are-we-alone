package com.diogothecoder.arewealone;

import java.util.Random;

import com.diogothecoder.arewealone.map.Planet;
import com.diogothecoder.arewealone.map.Star;
import com.diogothecoder.arewealone.tools.Logger;

public class Player {
	public static char MAP_KEY = 'X';

	protected static Random random;
	
	private Position universePosition;
	private Position galaxyPosition;
	private Position solarSystemPosition;
	
	public Player() {
		Logger.Debug("Creating player...");
		random = new Random();
		this.universePosition = getRandomPos(Game.getUniverse().getMap());
		this.galaxyPosition = getRandomPos(Game.getUniverse().getGalaxy(this.universePosition).getMap());
		this.solarSystemPosition = getRandomPos(Game.getUniverse().getGalaxy(this.universePosition).getSolarSystem(this.galaxyPosition).getMap());
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

	/**
	 * Return an array of the possible navigation options for the Player
	 * i.e. ['N', 'E', 'S', W]
	 */
	private void getNavigationOptions() {

	}
}
