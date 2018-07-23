package com.sokolov.decoratorsIdeaPlugin.constructor;

import com.sokolov.decoratorsIdeaPlugin.method.parameter.IParameter;

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
