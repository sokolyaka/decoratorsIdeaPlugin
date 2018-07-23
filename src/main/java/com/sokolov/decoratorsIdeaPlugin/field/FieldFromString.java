package com.sokolov.decoratorsIdeaPlugin.field;

public class FieldFromString implements IField {
    private final String fieldStr;

    public FieldFromString(String fieldStr) {
        this.fieldStr = fieldStr;
    }

    @Override
    public String asString() {
        return fieldStr;
    }
}
