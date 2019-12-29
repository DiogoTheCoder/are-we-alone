package com.diogothecoder.arewealone.actions;

import com.diogothecoder.arewealone.Game;
import com.diogothecoder.arewealone.Position;
import com.diogothecoder.arewealone.map.Galaxy;
import com.diogothecoder.arewealone.map.Map;
import com.diogothecoder.arewealone.map.SolarSystem;
import com.diogothecoder.arewealone.map.Universe;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

public class Navigation extends Action {
    // We use LinkedHashMap and not just regular HashMap, so we can maintain the insertion order
    private LinkedHashMap<ActionEnum, Method> ACTIONS;

    public Navigation() {
        this.ACTIONS = this.getAll();
    }

    private Navigation(LinkedHashMap<ActionEnum, Method> actions) {
        super(actions);
    }

    public LinkedHashMap<ActionEnum, Method> getPossibleActions() throws NoSuchMethodException {
        Position currentPlayerPosition = null;

        // Where are we? In a Solar System or between Solar Systems, or between Galaxies?
        Map currentMap = Game.getUniverse().getGalaxy().getSolarSystem();
        if (currentMap != null) {
            currentPlayerPosition = currentMap.getPlayerPosition();
        }

        if (currentPlayerPosition == null) {
            throw new NullPointerException("currentPlayerPosition is null!");
        }

        LinkedHashMap<ActionEnum, Method> navigationOptions = new LinkedHashMap<>();

        // Is there anything North of us?
        if (currentMap.isEmptyAt(currentPlayerPosition.getX(), currentPlayerPosition.getY() - 1)) {
            navigationOptions.put(NavigationEnum.NORTH, this.getClass().getDeclaredMethod("goNorth"));
        }

        // What about East?
        if (currentMap.isEmptyAt(currentPlayerPosition.getX() + 1, currentPlayerPosition.getY())) {
            navigationOptions.put(NavigationEnum.EAST, this.getClass().getDeclaredMethod("goEast"));
        }

        // And how about South?
        if (currentMap.isEmptyAt(currentPlayerPosition.getX(), currentPlayerPosition.getY() + 1)) {
            navigationOptions.put(NavigationEnum.SOUTH, this.getClass().getDeclaredMethod("goSouth"));
        }

        // Finally, what about West?
        if (currentMap.isEmptyAt(currentPlayerPosition.getX() - 1, currentPlayerPosition.getY())) {
            navigationOptions.put(NavigationEnum.WEST, this.getClass().getDeclaredMethod("goWest"));
        }

        this.ACTIONS = navigationOptions;

        return this.ACTIONS;
    }

    public void displayPossibleActions() {
        try {
            System.out.println();
            for (ActionEnum navigationEnum : this.getPossibleActions().keySet()) {
                System.out.println(navigationEnum.getKey() + " --> " + navigationEnum.getValue());
            }
            System.out.println();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public LinkedHashMap<ActionEnum, Method> getAll() {
        if (this.ACTIONS == null) {
            LinkedHashMap<ActionEnum, Method> navigationEnumRunnableLinkedHashMap = new LinkedHashMap<>();
            try {
                navigationEnumRunnableLinkedHashMap.put(
                        NavigationEnum.NORTH,
                        Navigation.class.getDeclaredMethod("goNorth", Position.class)
                );

                navigationEnumRunnableLinkedHashMap.put(
                        NavigationEnum.EAST,
                        Navigation.class.getDeclaredMethod("goEast", Position.class)
                );

                navigationEnumRunnableLinkedHashMap.put(
                        NavigationEnum.SOUTH,
                        Navigation.class.getDeclaredMethod("goSouth", Position.class)
                );

                navigationEnumRunnableLinkedHashMap.put(
                        NavigationEnum.WEST,
                        Navigation.class.getDeclaredMethod("goWest", Position.class)
                );

                this.ACTIONS = navigationEnumRunnableLinkedHashMap;
            } catch (Exception ignored) { }
        }

        return this.ACTIONS;
    }

    protected void goNorth() {
        Map currentMap = getCurrentMap();
        Position currentPosition = currentMap.getPlayerPosition();

        currentMap.setPlayerPosition(new Position(currentPosition.getX(), currentPosition.getY() - 1));
    }

    protected void goEast() {
        Map currentMap = getCurrentMap();
        Position currentPosition = currentMap.getPlayerPosition();

        currentMap.setPlayerPosition(new Position(currentPosition.getX() + 1, currentPosition.getY()));
    }

    protected void goSouth() {
        Map currentMap = getCurrentMap();
        Position currentPosition = currentMap.getPlayerPosition();

        currentMap.setPlayerPosition(new Position(currentPosition.getX(), currentPosition.getY() + 1));
    }

    protected void goWest() {
        Map currentMap = getCurrentMap();
        Position currentPosition = currentMap.getPlayerPosition();

        currentMap.setPlayerPosition(new Position(currentPosition.getX() - 1, currentPosition.getY()));
    }

    private Map getCurrentMap() {
        Universe universe = Game.getUniverse();

        Galaxy galaxy = universe.getGalaxy();
        if (galaxy == null) {
            return universe;
        }

        SolarSystem solarSystem = galaxy.getSolarSystem();
        if (solarSystem == null) {
            return galaxy;
        }

        return solarSystem;
    }
}

