package com.diogothecoder.arewealone.map;

import java.util.Random;

abstract class Map {
    private Random random;
    private String[][] map;

    Map() {
        this.random = new Random();
        this.map = new String[Config.SIZE_OF_MAP][Config.SIZE_OF_MAP];
        this.generateMap();
    }

    abstract protected void generateMap();
    abstract public void display();

    public String[][] getMap() {
        return this.map;
    }

    public int getRandomInt(int max, boolean inclusive) {
        if (inclusive) {
            return this.random.nextInt(max + 1);
        }

        return this.random.nextInt(max);
    }

    public int getRandomInt(int min, int max, boolean inclusive) {
        if (inclusive) {
            return this.random.nextInt((max - min) + 1) + min;
        }

        return this.random.nextInt(max - min) + min;
    }
}
