package com.sokolov.lang.java.defaultReturnValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LongDefaultReturnValue implements IDefaultReturnValue {
    private final Set<String> types;
    private final String value;

    public LongDefaultReturnValue() {
        types = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("long", "Long")));
        value = Long.toString(0L);
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
