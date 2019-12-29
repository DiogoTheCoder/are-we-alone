package com.diogothecoder.arewealone;

import com.diogothecoder.arewealone.tools.Logger;

public class Position {
	int x;
	int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void display() {
		System.out.println();
		Logger.Info("The Player is at Coordinate (X, Y): (" + this.getX() + ", " + this.getY() + ")", false);
	}
}
