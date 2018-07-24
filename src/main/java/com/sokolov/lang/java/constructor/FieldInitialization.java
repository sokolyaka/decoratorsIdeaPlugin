package com.sokolov.lang.java.constructor;

public class FieldInitialization implements IFieldInitialization {
    private final String name;
    private final String value;

    public FieldInitialization(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String value() {
        return value;
    }
}
