package com.sokolov.lang.java.parameter;

public class Parameter implements IParameter{
    private final String type;
    private final String name;

    public Parameter(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public String name() {
        return name;
    }
}
