package com.diogothecoder.arewealone.map;

import java.util.Random;

import com.diogothecoder.arewealone.Game;
import com.diogothecoder.arewealone.tools.Console;

public class SolarSystem {
	protected static Random random;

	private String[][] theMap;
	private Star theStar;
	private Planet[] planets;
	
	public SolarSystem() {
		random = new Random();
		generate();
	}
	
	private void generate() {
		int numOfPlanets = Universe.random.nextInt(Math.abs(Config.MAX_NUM_OF_PLANETS_PER_SOLAR_SYSTEMS - Config.MIN_NUM_OF_PLANETS_PER_SOLAR_SYSTEMS)) + Config.MIN_NUM_OF_PLANETS_PER_SOLAR_SYSTEMS;

		this.theMap = new String[Config.SIZE_OF_MAP][Config.SIZE_OF_MAP];
		this.planets = new Planet[numOfPlanets];
		
		this.theStar = new Star();
		int middle = Math.round(0 + (this.theMap.length - 0) / 2);
		theMap[middle][middle] = "S";
		
		for (int i = 0; i < planets.length; i++) {
			this.planets[i] = new Planet();
			
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
					System.out.print("X  ");
				} else if (theMap[row][column] == "S") {
					System.out.print("S  ");
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
}
