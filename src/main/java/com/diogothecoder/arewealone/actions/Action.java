package com.diogothecoder.arewealone.actions;

import com.diogothecoder.arewealone.Game;
import com.diogothecoder.arewealone.tools.Console;
import com.diogothecoder.arewealone.tools.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

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
            this.getPossibleActions().forEach((key, value) -> Logger.Info(key.getKey() + " --> " + key.getValue(), false));
            System.out.println();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void displayPossibleActions(LinkedHashMap<ActionEnum, Method> actions) {
        System.out.println();
        actions.forEach((key, value) -> Logger.Info(key.getKey() + " --> " + key.getValue(), false));
        System.out.println();
    }

    public static void executeFromInput(ArrayList<LinkedHashMap<ActionEnum, Method>> actions) {
        String action = Console.getUserInput().toUpperCase();

        AtomicBoolean actionFound = new AtomicBoolean(false);
        for (LinkedHashMap<ActionEnum, Method> actionList : actions) {
            actionList.forEach((key, value) -> {
                if (actionFound.get()) {
                    return;
                }

                String actionKey = key.getKey().toUpperCase();
                if (actionKey.equals(action)) {
                    actionFound.set(true);

                    try {
                        switch (key.getType()) {
                            case NAVIGATION:
                                actionList.get(key).invoke(Game.getPlayer().getNavigation());
                                break;
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });

            if (actionFound.get()) {
                break;
            }
        }

        if (!actionFound.get()) {
            Logger.Info("Sorry, but that seems to be an invalid action, please try again.", false);
        }
    }
}
