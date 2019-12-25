package com.diogothecoder.arewealone;

import java.util.Random;

import com.diogothecoder.arewealone.tools.Logger;

public class Player {
	public static char MAP_KEY = 'P';

	protected static Random random;
	
	private Position universePosition;
	private Position galaxyPosition;
	
	public Player() {
		Logger.Debug("Creating player...");
		random = new Random();
		this.universePosition = getRandomPos(Game.getUniverse().getMap());
		this.galaxyPosition = getRandomPos(Game.getUniverse().getGalaxy(this.universePosition).getMap());
	}
	
	private Position getRandomPos(String[][] map) {
		while (true) {
			int randomPosX = random.nextInt(map.length);
			int randomPosY = random.nextInt(map.length);
			
			if (map[randomPosX][randomPosY] != null && map[randomPosX][randomPosY] != "B" && map[randomPosX][randomPosY] != "S") {
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
	
	public void displayPos(Position pos) {
		Logger.Info("The Player is at Coordinate (X, Y): (" + pos.getX() + ", " + pos.getY() + ")");
	}
}
