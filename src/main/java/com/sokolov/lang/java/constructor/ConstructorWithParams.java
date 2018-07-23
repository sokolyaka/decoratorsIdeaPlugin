package com.sokolov.lang.java.constructor;

import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public class ConstructorWithParams implements IConstructor {
    private final List<IParameter> params;
    private final String accessLevel;

    public ConstructorWithParams(List<IParameter> params, String accessLevel) {
        this.params = params;
        this.accessLevel = accessLevel;
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
