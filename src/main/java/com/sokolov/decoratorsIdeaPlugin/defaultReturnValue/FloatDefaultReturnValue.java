package com.sokolov.decoratorsIdeaPlugin.defaultReturnValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FloatDefaultReturnValue implements IDefaultReturnValue {
    private final Set<String> types;
    private final String value;

    public FloatDefaultReturnValue() {
        types = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("float", "Float")));
        value = Float.toString(.0f) + "f";
    }

    @Override
    public Set<String> types() {
        return types;
    }

    @Override
    public String value() {
        return value;
    }
}
