package com.diogothecoder.arewealone.actions;

import com.diogothecoder.arewealone.Game;
import com.diogothecoder.arewealone.Position;
import com.diogothecoder.arewealone.map.Config;
import com.diogothecoder.arewealone.map.Galaxy;
import com.diogothecoder.arewealone.map.Map;
import com.diogothecoder.arewealone.map.SolarSystem;
import com.diogothecoder.arewealone.tools.exceptions.NotFoundException;
import javafx.geometry.Pos;

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

    public LinkedHashMap<ActionEnum, Method> getPossibleActions() throws NoSuchMethodException, NotFoundException {
        Map currentMap = Map.getCurrentMap();
        Position currentPlayerPosition = currentMap.getPlayerPosition();

        if (currentPlayerPosition == null) {
            throw new NotFoundException("currentPlayerPosition is null!");
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

        // We are at the Northern border?
        if (currentPlayerPosition.getY() == 0) {
            navigationOptions.put(NavigationEnum.LEAVE_NORTH, this.getClass().getDeclaredMethod("leaveMapViaNorth"));
        }

        // We are at the Eastern border?
        if (currentPlayerPosition.getX() == Config.SIZE_OF_MAP - 1) {
            navigationOptions.put(NavigationEnum.LEAVE_EAST, this.getClass().getDeclaredMethod("leaveMapViaEast"));
        }

        // We are at the Southern border?
        if (currentPlayerPosition.getY() == Config.SIZE_OF_MAP - 1) {
            navigationOptions.put(NavigationEnum.LEAVE_SOUTH, this.getClass().getDeclaredMethod("leaveMapViaSouth"));
        }

        // We are at the Western border?
        if (currentPlayerPosition.getX() == 0) {
            navigationOptions.put(NavigationEnum.LEAVE_WEST, this.getClass().getDeclaredMethod("leaveMapViaWest"));
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
        } catch (NoSuchMethodException | NotFoundException e) {
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
        Map currentMap = Map.getCurrentMap();
        Position currentPosition = currentMap.getPlayerPosition();

        currentMap.setPlayerPosition(new Position(currentPosition.getX(), currentPosition.getY() - 1));
    }

    protected void goEast() {
        Map currentMap = Map.getCurrentMap();
        Position currentPosition = currentMap.getPlayerPosition();

        currentMap.setPlayerPosition(new Position(currentPosition.getX() + 1, currentPosition.getY()));
    }

    protected void goSouth() {
        Map currentMap = Map.getCurrentMap();
        Position currentPosition = currentMap.getPlayerPosition();

        currentMap.setPlayerPosition(new Position(currentPosition.getX(), currentPosition.getY() + 1));
    }

    protected void goWest() {
        Map currentMap = Map.getCurrentMap();
        Position currentPosition = currentMap.getPlayerPosition();

        currentMap.setPlayerPosition(new Position(currentPosition.getX() - 1, currentPosition.getY()));
    }

    protected void leaveMapViaNorth() {
        try {
            this.leaveMap(NavigationEnum.LEAVE_NORTH);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void leaveMapViaEast() {
        try {
            this.leaveMap(NavigationEnum.LEAVE_EAST);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void leaveMapViaSouth() {
        try {
            this.leaveMap(NavigationEnum.LEAVE_SOUTH);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void leaveMapViaWest() {
        try {
            this.leaveMap(NavigationEnum.LEAVE_WEST);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void leaveMap(NavigationEnum navigationEnum) throws NotFoundException {
        Map currentMap = Map.getCurrentMap();
        Map parentMap = null;
        Position playerParentMapPosition = null;

        // We are at the Solar System, Galaxy time!
        // TODO: allow for going to other Galaxies
        if (currentMap instanceof SolarSystem) {
            parentMap = Game.getUniverse().getGalaxy();
            playerParentMapPosition = parentMap.getPlayerPosition();
        }

        if (parentMap == null) {
            throw new NotFoundException("Unable to leave the Map, cannot determine parent map!");
        }

        if (playerParentMapPosition == null) {
            throw new NotFoundException("Unable to get Player Parent Map position!");
        }

        Position newPosition = playerParentMapPosition;
        switch (navigationEnum) {
            case LEAVE_NORTH:
                newPosition = new Position(playerParentMapPosition.getX(), playerParentMapPosition.getY() - 1);
                break;
        }

        parentMap.setPlayerPosition(newPosition);
    }
}

