package com.sokolov.lang.java.field;

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
