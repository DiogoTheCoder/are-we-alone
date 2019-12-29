package com.diogothecoder.arewealone.map;

import com.diogothecoder.arewealone.Game;
import com.diogothecoder.arewealone.Player;
import com.diogothecoder.arewealone.Position;
import com.diogothecoder.arewealone.tools.Console;
import com.diogothecoder.arewealone.tools.Logger;

import java.util.Arrays;

/**
 * This class represents the entire map
 * and is in charge of generating our world!
 */
public class Universe extends Map {
	private static Universe instance;

	private Galaxy[] galaxies;

	public static Universe getInstance() {
		if (instance == null) {
			instance = new Universe();
		}

		return instance;
	}
	
	private Universe() {
		Logger.Debug("Creating universe...");
	}
	
	protected void generateMap() {
		for (String[] strings : this.getMap()) {
			Arrays.fill(strings, "");
		}

		this.galaxies = new Galaxy[Config.NUM_OF_GALAXIES];
		for (int i = 0; i < galaxies.length; i++) {
			this.galaxies[i] = new Galaxy();
			
			while (true) {
				int randomLocationX = this.getRandomInt(1, this.getMap().length - 1, false);
				int randomLocationY = this.getRandomInt(1, this.getMap()[randomLocationX].length - 1, false);

				if (this.getMap()[randomLocationX][randomLocationY] == null || this.getMap()[randomLocationX][randomLocationY].isEmpty()) {
					this.getMap()[randomLocationX][randomLocationY] = Integer.toString(i);
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
				if (row == Game.getUniverse().getPlayerPosition().getY()
						&& column == Game.getUniverse().getPlayerPosition().getX()) {
					System.out.print(Player.MAP_KEY + "  ");
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

	public Galaxy getGalaxy(Position universePos) {
		String index = this.getMap()[universePos.getX()][universePos.getY()];
		return this.galaxies[Integer.parseInt(index)];
	}
	
	public Galaxy getGalaxy() {
		Position currentPlayerPosition = Game.getUniverse().getPlayerPosition();
		String index = this.getMap()[currentPlayerPosition.getX()][currentPlayerPosition.getY()];
				
		return this.galaxies[Integer.parseInt(index)];
	}
}
