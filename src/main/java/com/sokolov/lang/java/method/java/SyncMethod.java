package com.sokolov.lang.java.method.java;

import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public class SyncMethod implements IMethod {

    private final IMethod origin;
    private final String syncObjectFieldName;

    public SyncMethod(IMethod origin, String syncObjectFieldName) {
        this.origin = origin;
        this.syncObjectFieldName = syncObjectFieldName;
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
        sb.insert(startIndex, "synchronized (" + syncObjectFieldName + "){");
        int endIndex = sb.lastIndexOf("}");
        sb.insert(endIndex, "}");

        return sb.toString();

    }
}
