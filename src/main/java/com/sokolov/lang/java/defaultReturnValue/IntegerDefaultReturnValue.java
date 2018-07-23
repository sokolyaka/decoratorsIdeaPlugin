package com.sokolov.lang.java.defaultReturnValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class IntegerDefaultReturnValue implements IDefaultReturnValue {

    private final Set<String> types;
    private final String value;

    public IntegerDefaultReturnValue() {
        types = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("int", "Integer")));
        value = Integer.toString(0);
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
