package com.sokolov.lang.java.constructor;

import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public class ConstructorWithParams implements IConstructor {
    private final String name;
    private final List<IParameter> params;
    private final String accessLevel;

    public ConstructorWithParams(String name, List<IParameter> params, String accessLevel) {
        this.name = name;
        this.params = params;
        this.accessLevel = accessLevel;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<IParameter> params() {
        return params;
    }

    @Override
    public String accessLevel() {
        return accessLevel;
    }
}
