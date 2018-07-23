package com.sokolov.lang.java.field;

public class FieldFromString implements IField {
    private final String type;
    private final String name;

    public FieldFromString(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String asString() {
        return type + " " + name + ";";
    }
}
