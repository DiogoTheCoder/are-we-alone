package com.diogothecoder.arewealone.actions;

import java.util.EnumMap;
import java.util.LinkedHashMap;

public class Navigation extends Action {
    Navigation(EnumMap<TypeEnum, LinkedHashMap<Enum<?>, Runnable>> actions) {
        super(actions);
    }
}
