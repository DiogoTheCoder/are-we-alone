package com.diogothecoder.arewealone.map;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import com.diogothecoder.arewealone.Game;
import com.diogothecoder.arewealone.Player;
import com.diogothecoder.arewealone.Position;
import com.diogothecoder.arewealone.tools.Console;

public class SolarSystem extends Map {
	private Star theStar;
	private Planet[] planets;

	/**
	 * Generating the Planets and Star for
	 * the given Solar System.
	 */
	protected void generateMap() {
		int numOfPlanets = this.getRandomInt(Config.MIN_NUM_OF_PLANETS_PER_SOLAR_SYSTEMS, Config.MAX_NUM_OF_PLANETS_PER_SOLAR_SYSTEMS, true);
		this.planets = new Planet[numOfPlanets];

		for (String[] strings : this.getMap()) {
			Arrays.fill(strings, "");
		}

		this.theStar = new Star();
		int middle = this.getMap().length / 2;
		this.getMap()[middle][middle] = "S";

		for (int i = 0; i < planets.length; i++) {
			this.planets[i] = new Planet();

			while (true) {
				int randomLocationX = this.getRandomInt(this.getMap().length, false);
				int randomLocationY = this.getRandomInt(this.getMap()[randomLocationX].length, false);

				if (this.getMap()[randomLocationX][randomLocationY] == null || this.getMap()[randomLocationX][randomLocationY].isEmpty()) {
					this.getMap()[randomLocationX][randomLocationY] = Character.toString(Planet.MAP_KEY);
					break;
				}
			}
		}
	}

	public void display() {
		Console.clear();
		for (int row = 0; row < this.getMap().length; row++) {
			if (row <= 9) {
				System.out.print("0" + row + " ");
			} else {
				System.out.print(row + " ");
			}

			for (int column = 0; column < this.getMap()[row].length; column++) {
				if (row == Game.getPlayer().getSolarSystemPos().getY()
						&& column == Game.getPlayer().getSolarSystemPos().getX()) {
					System.out.print(Player.MAP_KEY + "  ");
				} else if (Objects.equals(this.getMap()[row][column], Character.toString(Star.MAP_KEY))) {
					System.out.print(Star.MAP_KEY + "  ");
				} else if (Objects.equals(this.getMap()[row][column], Character.toString(Planet.MAP_KEY))) {
					System.out.print(Planet.MAP_KEY + "  ");
				} else {
					System.out.print(".  ");
				}
			}

			System.out.println();
		}

		System.out.print("  ");

		for (int column = 0; column < this.getMap().length; column++) {
			if (column <= 9) {
				System.out.print(" " + column + " ");
			} else {
				System.out.print(column + " ");
			}
		}

		System.out.println();
	}

	public Star getStar() {
		return this.theStar;
	}

	public Planet[] getPlanets() {
		return this.planets;
	}

	private String getAtPos(Position position) {
		try {
			return this.getMap()[position.getX()][position.getY()];
		} catch (ArrayIndexOutOfBoundsException e) {
			// We have attempted to go outside the Solar System
			// TODO: allow this to happen
			return e.getMessage();
		}
	}

	public boolean isEmptyAt(int x, int y) {
		return this.getAtPos(new Position(x, y)).isEmpty();
	}
}
