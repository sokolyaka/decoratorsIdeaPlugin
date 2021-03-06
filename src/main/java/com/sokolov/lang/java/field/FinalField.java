package com.sokolov.lang.java.field;

public class FinalField implements IField {
    private final IField origin;

    public FinalField(IField origin) {
        this.origin = origin;
    }

    @Override
    public String asString() {
        return "final " + origin.asString();
    }
}
