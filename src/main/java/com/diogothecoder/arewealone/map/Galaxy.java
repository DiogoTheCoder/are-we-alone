package com.diogothecoder.arewealone.map;

import java.util.Objects;
import java.util.Random;

import com.diogothecoder.arewealone.Game;
import com.diogothecoder.arewealone.Player;
import com.diogothecoder.arewealone.Position;
import com.diogothecoder.arewealone.tools.Console;

public class Galaxy {
	protected static Random random;

	private String[][] theMap;
	private SolarSystem[] solarSystems;
	
	public Galaxy() {
		random = new Random();
		generate();
	}
	
	private void generate() {
		int numOfSolarSystems = Universe.random.nextInt(Math.abs(Config.MAX_NUM_OF_SOLAR_SYSTEMS_PER_GALAXY - Config.MIN_NUM_OF_SOLAR_SYSTEMS_PER_GALAXY)) + Config.MIN_NUM_OF_SOLAR_SYSTEMS_PER_GALAXY;
		this.theMap = new String[Config.SIZE_OF_MAP][Config.SIZE_OF_MAP];

		int middle = this.theMap.length / 2;
		theMap[middle][middle] = "B";
				
		this.solarSystems = new SolarSystem[numOfSolarSystems];
		for (int i = 0; i < solarSystems.length; i++) {
			this.solarSystems[i] = new SolarSystem();
			
			while (true) {
				int randomLocationX = random.nextInt(this.theMap.length);
				int randomLocationY = random.nextInt(this.theMap[randomLocationX].length);
				
				if (this.theMap[randomLocationX][randomLocationY] == null) {
					this.theMap[randomLocationX][randomLocationY] = Integer.toString(i);
					break;
				}
			}
		}
	}
	
	public void displayMap() {
		Console.clear();
		for (int row = 0; row < theMap.length; row++) {
			if (row <= 9) {
				System.out.print("0" + row + " ");
			} else {
				System.out.print(row + " ");				
			}
			
			for (int column = 0; column < theMap[row].length; column++) {
				if (row == Game.getPlayer().getGalaxyPos().getX()
						&& column == Game.getPlayer().getGalaxyPos().getY()) {
					System.out.print(Player.MAP_KEY + "  ");
				} else if (Objects.equals(theMap[row][column], "B")) {
					System.out.print("B  ");
				} else {
					System.out.print(".  ");
				}
			}
			
			System.out.println();
		}
		
		System.out.print("  ");
		
		for (int column = 0; column < theMap.length; column++) {
			if (column <= 9) {
				System.out.print(" " + column + " ");
			} else {
				System.out.print(column + " ");				
			}
		}
		
		System.out.println();
	}
	
	public SolarSystem getSolarSystem(Position galaxyPosition) {
		String index = this.theMap[galaxyPosition.getX()][galaxyPosition.getY()];				
		return this.solarSystems[Integer.parseInt(index)];
	}
	
	public SolarSystem getSolarSystem() {
		Position currentPlayerPosition = Game.getPlayer().getGalaxyPos().getPosition();
		String index = this.theMap[currentPlayerPosition.getX()][currentPlayerPosition.getY()];
		
		return this.solarSystems[Integer.parseInt(index)];
	}
	
	public String[][] getMap() {
		return this.theMap;
	}
	
}
