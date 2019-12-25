package com.diogothecoder.arewealone.map;

import java.util.Random;

import com.diogothecoder.arewealone.Game;
import com.diogothecoder.arewealone.Player;
import com.diogothecoder.arewealone.Position;
import com.diogothecoder.arewealone.tools.Console;
import com.diogothecoder.arewealone.tools.Logger;

/**
 * This class represents the entire map
 * and is in charge of generating our world!
 */
public class Universe {
	protected static Random random;

	private String[][] theMap;
	private Galaxy[] galaxies;
	
	public Universe() {
		Logger.Debug("Creating universe...");
		random = new Random();
		generate();
	}
	
	private void generate() {
		this.galaxies = new Galaxy[Config.NUM_OF_GALAXIES];
		this.theMap = new String[Config.SIZE_OF_MAP][Config.SIZE_OF_MAP];

		for (int i = 0; i < galaxies.length; i++) {
			this.galaxies[i] = new Galaxy();
			
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
				if (row == Game.getPlayer().getUniversePos().getX()
						&& column == Game.getPlayer().getUniversePos().getY()) {
					System.out.print(Player.MAP_KEY + "  ");
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
	
	public Galaxy getGalaxy(Position universePos) {
		String index = this.theMap[universePos.getX()][universePos.getY()];				
		return this.galaxies[Integer.parseInt(index)];
	}
	
	public Galaxy getGalaxy() {
		Position currentPlayerPosition = Game.getPlayer().getUniversePos().getPosition();
		String index = this.theMap[currentPlayerPosition.getX()][currentPlayerPosition.getY()];
				
		return this.galaxies[Integer.parseInt(index)];
	}
}
