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
    public LinkedHashMap<Enum<?>, Method> getPossibleActions() {
        return this.ACTIONS;
    }
}
