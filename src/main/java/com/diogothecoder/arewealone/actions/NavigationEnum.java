package com.diogothecoder.arewealone.actions;

public enum NavigationEnum {
    NORTH("North", "Accelerate Northwards"),
    EAST("East", "Accelerate Eastwards"),
    SOUTH("South", "Accelerate Southwards"),
    WEST("West", "Accelerate Westwards");

    private final String key;
    private final String value;

    NavigationEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}
