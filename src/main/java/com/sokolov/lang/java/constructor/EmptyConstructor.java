package com.sokolov.lang.java.constructor;

import com.sokolov.lang.java.parameter.IParameter;

import java.util.Collections;
import java.util.List;

public class EmptyConstructor implements IConstructor {
    private final String accessLevel;

    public EmptyConstructor(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public List<IParameter> params() {
        return Collections.emptyList();
    }

    @Override
    public String accessLevel() {
        return accessLevel;
    }
}
