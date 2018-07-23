package com.sokolov.lang.java.defaultReturnValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ObjectDefaultReturnValue implements IDefaultReturnValue {
    private final Set<String> types;

    public ObjectDefaultReturnValue() {
        types = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("Object", "String")));
    }

    @Override
    public Set<String> types() {
        return types;
    }

    @Override
    public String value() {
        return "null";
    }
}
