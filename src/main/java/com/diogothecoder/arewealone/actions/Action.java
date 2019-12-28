package com.diogothecoder.arewealone.actions;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

abstract class Action {
    private LinkedHashMap<Enum<?>, Method> ACTIONS;

    protected Action() { this.ACTIONS = this.getAll(); }
    protected Action(LinkedHashMap<Enum<?>, Method> actions) {
        this.ACTIONS = actions;
    }

    public LinkedHashMap<Enum<?>, Method> getAll() {
        return this.ACTIONS;
    }
    private LinkedHashMap<Enum<?>, Method> getPossibleActions() throws NoSuchMethodException {
        if (this.ACTIONS == null) {
            this.ACTIONS = this.getAll();
        }

        return this.ACTIONS;
    }

    public void displayPossibleActions() {
        try {
            System.out.println();
            this.getPossibleActions().forEach((key, value) -> System.out.println(key + " --> " + value));
            System.out.println();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void displayPossibleActions(LinkedHashMap<Enum<?>, Method> actions) {
        System.out.println();
        actions.forEach((key, value) -> System.out.println(key + " --> " + value));
        System.out.println();
    }
}
