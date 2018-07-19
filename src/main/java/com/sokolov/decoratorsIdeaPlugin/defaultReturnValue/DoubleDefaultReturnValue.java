package com.sokolov.decoratorsIdeaPlugin.defaultReturnValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DoubleDefaultReturnValue implements IDefaultReturnValue {
    private final Set<String> types;
    private final String value;

    public DoubleDefaultReturnValue() {
        types = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("double", "Double")));
        value = Double.toString(.0D);
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
