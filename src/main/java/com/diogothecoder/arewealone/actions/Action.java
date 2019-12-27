package com.diogothecoder.arewealone.actions;

import java.util.EnumMap;
import java.util.LinkedHashMap;

abstract class Action {
    EnumMap<TypeEnum, LinkedHashMap<Enum<?>, Runnable>> ACTIONS;

    Action(EnumMap<TypeEnum, LinkedHashMap<Enum<?>, Runnable>> actions) {
        this.ACTIONS = actions;
    }
}
