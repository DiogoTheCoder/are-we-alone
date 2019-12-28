package com.diogothecoder.arewealone.actions;

import com.diogothecoder.arewealone.Position;
import com.diogothecoder.arewealone.map.Map;
import com.diogothecoder.arewealone.tools.exceptions.MapNotFound;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

public class Navigation extends Action {
    // We use LinkedHashMap and not just regular HashMap, so we can maintain the insertion order
    private LinkedHashMap<Enum<?>, Method> ACTIONS;

    public Navigation() {
        this.ACTIONS = this.getAll();
    }

    private Navigation(LinkedHashMap<Enum<?>, Method> actions) {
        super(actions);
    }

    public LinkedHashMap<Enum<?>, Method> getPossibleActions(Map map, Position currentPosition) throws MapNotFound {
        String[][] theMap = map.getMap();
        if (theMap == null) {
            throw new MapNotFound();
        }

        LinkedHashMap<Enum<?>, Method> navigationOptions = new LinkedHashMap<>();

//        // Is there anything North of us?
//        if (theMap. this.currentSolarSystem.isEmptyAt(this.solarSystemPosition.getX(), this.solarSystemPosition.getY() - 1)) {
//            navigationOptions.put('N', "Accelerate Northwards");
//        }
//
//        // What about East?
//        if (this.currentSolarSystem.isEmptyAt(this.solarSystemPosition.getX() + 1, this.solarSystemPosition.getY())) {
//            navigationOptions.put('E', "Accelerate Eastwards");
//        }
//
//        // And how about South?
//        if (this.currentSolarSystem.isEmptyAt(this.solarSystemPosition.getX(), this.solarSystemPosition.getY() + 1)) {
//            navigationOptions.put('S', "Accelerate Southwards");
//        }
//
//        // Finally, what about West?
//        if (this.currentSolarSystem.isEmptyAt(this.solarSystemPosition.getX() - 1, this.solarSystemPosition.getY())) {
//            navigationOptions.put('W', "Accelerate Westwards");
//        }

        return navigationOptions;
    }

    public LinkedHashMap<Enum<?>, Method> getAll() {
        if (this.ACTIONS == null) {
            LinkedHashMap<Enum<?>, Method> navigationEnumRunnableLinkedHashMap = new LinkedHashMap<>();
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

    private Position goNorth(Position currentPosition) {
        return new Position(currentPosition.getX(), currentPosition.getY() - 1);
    }

    private Position goEast(Position currentPosition) {
        return new Position(currentPosition.getX() + 1, currentPosition.getY());
    }

    private Position goSouth(Position currentPosition) {
        return new Position(currentPosition.getX(), currentPosition.getY() + 1);
    }

    private Position goWest(Position currentPosition) {
        return new Position(currentPosition.getX() - 1, currentPosition.getY());
    }
}

