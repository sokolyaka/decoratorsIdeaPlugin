package com.sokolov.lang.java.method.java;

import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public class MethodWithCustomCode implements IMethod {
    private final IMethod origin;
    private final String customCode;

    public MethodWithCustomCode(IMethod origin, String customCode) {
        this.origin = origin;
        this.customCode = customCode;
    }

    @Override
    public String name() {
        return origin.name();
    }

    @Override
    public List<IParameter> parameters() {
        return origin.parameters();
    }

    @Override
    public String implementation() {
        StringBuilder sb = new StringBuilder(origin.implementation());
        int startIndex = sb.indexOf("{") + 1;
        sb.insert(startIndex, customCode);

        return sb.toString();
    }
}
