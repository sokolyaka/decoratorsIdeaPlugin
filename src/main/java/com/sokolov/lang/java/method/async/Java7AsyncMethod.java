package com.sokolov.lang.java.method.async;

import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public class Java7AsyncMethod implements IMethod {
    private final IMethod origin;

    public Java7AsyncMethod(IMethod origin) {
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
        StringBuilder sb = new StringBuilder(origin.implementation());
        int startIndex = sb.indexOf("{") + 1;
        sb.insert(startIndex, "executorService.execute(new Runnable(){@Override public void run(){");

        int endIndex = sb.lastIndexOf("}");
        sb.insert(endIndex, "}});");

        return sb.toString();
    }
}
