package com.diogothecoder.arewealone.actions;

import com.diogothecoder.arewealone.Position;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

public class Navigation extends Action {
    // We use LinkedHashMap and not just regular HashMap, so we can maintain the insertion order
    private LinkedHashMap<Enum<?>, Method> ACTIONS;

    public LinkedHashMap<Enum<?>, Method> getAll() {
        if (this.ACTIONS == null) {
            LinkedHashMap<Enum<?>, Method> navigationEnumRunnableLinkedHashMap = new LinkedHashMap<>();
            try {
                navigationEnumRunnableLinkedHashMap.put(
                    NavigationEnum.NORTH,
                    Navigation.class.getDeclaredMethod("goNorth")
                );

                navigationEnumRunnableLinkedHashMap.put(
                    NavigationEnum.EAST,
                    Navigation.class.getDeclaredMethod("goEast")
                );

                navigationEnumRunnableLinkedHashMap.put(
                    NavigationEnum.SOUTH,
                    Navigation.class.getDeclaredMethod("goSouth")
                );

                navigationEnumRunnableLinkedHashMap.put(
                    NavigationEnum.WEST,
                    Navigation.class.getDeclaredMethod("goWest")
                );

                this.ACTIONS = navigationEnumRunnableLinkedHashMap;
            } catch (Exception ignored) { }
        }

        return this.ACTIONS;
    }

    public Navigation() {
        this.ACTIONS = this.getAll();
    }

    private Navigation(LinkedHashMap<Enum<?>, Method> actions) {
        super(actions);
    }

    private Position goNorth() {
        return new Position(0, 0);
    }

    private Position goEast() {
        return new Position(0, 0);
    }

    private Position goSouth() {
        return new Position(0, 0);
    }

    private Position goWest() {
        return new Position(0, 0);
    }
}

