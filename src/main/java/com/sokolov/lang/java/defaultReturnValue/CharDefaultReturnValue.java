package com.sokolov.lang.java.defaultReturnValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CharDefaultReturnValue implements IDefaultReturnValue {
    private final Set<String> types;
    private final String value;

    public CharDefaultReturnValue() {
        types = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("char", "Character")));
        value = Character.toString('\u0000');
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
