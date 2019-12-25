package com.diogothecoder.arewealone.map;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import com.diogothecoder.arewealone.Game;
import com.diogothecoder.arewealone.Player;
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

	/**
	 * Generating the Planets and Star for
	 * the given Solar System.
	 */
	private void generate() {
		int numOfPlanets = Universe.random.nextInt(Math.abs(Config.MAX_NUM_OF_PLANETS_PER_SOLAR_SYSTEMS - Config.MIN_NUM_OF_PLANETS_PER_SOLAR_SYSTEMS)) + Config.MIN_NUM_OF_PLANETS_PER_SOLAR_SYSTEMS;

		this.theMap = new String[Config.SIZE_OF_MAP][Config.SIZE_OF_MAP];
		this.planets = new Planet[numOfPlanets];

		for (String[] strings : this.theMap) {
			Arrays.fill(strings, "");
		}

		this.theStar = new Star();
		int middle = this.theMap.length / 2;
		theMap[middle][middle] = "S";

		for (int i = 0; i < planets.length; i++) {
			this.planets[i] = new Planet();

			while (true) {
				int randomLocationX = random.nextInt(this.theMap.length);
				int randomLocationY = random.nextInt(this.theMap[randomLocationX].length);

				if (this.theMap[randomLocationX][randomLocationY] == null || this.theMap[randomLocationX][randomLocationY].isEmpty()) {
					this.theMap[randomLocationX][randomLocationY] = Character.toString(Planet.MAP_KEY);
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
				if (row == Game.getPlayer().getSolarSystemPos().getX()
						&& column == Game.getPlayer().getSolarSystemPos().getY()) {
					System.out.print(Player.MAP_KEY + "  ");
				} else if (Objects.equals(theMap[row][column], Character.toString(Star.MAP_KEY))) {
					System.out.print(Star.MAP_KEY + "  ");
				} else if (Objects.equals(theMap[row][column], Character.toString(Planet.MAP_KEY))) {
					System.out.print(Planet.MAP_KEY + "  ");
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

	public String[][] getMap() {
		return this.theMap;
	}

	boolean isStarAt(int x, int y) {
		return Objects.equals(this.theMap[x][y], Character.toString(Star.MAP_KEY));
	}

	boolean isPlanetAt(int x, int y) {
		return Objects.equals(this.theMap[x][y], Character.toString(Planet.MAP_KEY));
	}
}
