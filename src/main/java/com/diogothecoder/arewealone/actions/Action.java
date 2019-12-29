package com.diogothecoder.arewealone.actions;

import com.diogothecoder.arewealone.tools.Console;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;

abstract public class Action {
    private LinkedHashMap<ActionEnum, Method> ACTIONS;

    protected Action() { this.ACTIONS = this.getAll(); }
    protected Action(LinkedHashMap<ActionEnum, Method> actions) {
        this.ACTIONS = actions;
    }

    public LinkedHashMap<ActionEnum, Method> getAll() {
        return this.ACTIONS;
    }
    private LinkedHashMap<ActionEnum, Method> getPossibleActions() throws NoSuchMethodException {
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

    public static void displayPossibleActions(LinkedHashMap<ActionEnum, Method> actions) {
        System.out.println();
        actions.forEach((key, value) -> System.out.println(key + " --> " + value));
        System.out.println();
    }

    public static void executeFromInput(ArrayList<LinkedHashMap<ActionEnum, Method>> actions) {
        String action = Console.getUserInput();

        boolean actionFound = false;
        for (LinkedHashMap<ActionEnum, Method> actionList : actions) {
            actionList.forEach((key, value) -> {
                System.out.println("This is a test");
            });

            if (actionFound) {
                break;
            }
        }
    }
}
