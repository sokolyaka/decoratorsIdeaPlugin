package com.sokolov.lang.java.method.java;

import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public class PublicMethod implements IMethod {

    private final IMethod origin;

    public PublicMethod(IMethod origin) {
        this.origin = origin;
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
        return "public " + origin.implementation();
    }
}
