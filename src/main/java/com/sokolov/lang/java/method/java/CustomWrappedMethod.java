package com.sokolov.lang.java.method.java;

import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public class CustomWrappedMethod implements IMethod {

    private final IMethod origin;
    private final String startOfWrapper;
    private final String endOfWrapper;

    public CustomWrappedMethod(IMethod origin, String startOfWrapper, String endOfWrapper) {
        this.origin = origin;
        this.startOfWrapper = startOfWrapper;
        this.endOfWrapper = endOfWrapper;
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
        sb.insert(startIndex, startOfWrapper);
        int endIndex = sb.lastIndexOf("}");
        sb.insert(endIndex, endOfWrapper);

        return sb.toString();

    }
}
