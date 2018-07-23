package com.sokolov.decoratorsIdeaPlugin.field;

public class PrivateField implements IField {
    private final IField origin;

    public PrivateField(IField origin) {
        this.origin = origin;
    }

    @Override
    public String asString() {
        return "private " + origin.asString();
    }
}
