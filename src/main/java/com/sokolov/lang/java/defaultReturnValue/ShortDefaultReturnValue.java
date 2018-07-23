package com.sokolov.lang.java.defaultReturnValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ShortDefaultReturnValue implements IDefaultReturnValue {
    private final Set<String> types;
    private final String value;

    public ShortDefaultReturnValue() {
        types = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("short", "Short")));
        value = Short.toString((short) 0);
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
