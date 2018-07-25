package com.sokolov.lang.java.method.android;

import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public class AndroidLoggableMethod implements IMethod {
    private final IMethod origin;
    private final String tagFieldName;

    public AndroidLoggableMethod(IMethod origin, String tagFieldName) {
        this.origin = origin;
        this.tagFieldName = tagFieldName;
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
        sb.insert(startIndex, logm());

        return sb.toString();
    }

    private String logm() {
        String prefix = "Log.d(" + tagFieldName + ", \"" + origin.name() + "() called";
        if (parameters() != null && parameters().size() > 0) {
            return prefix + " with:" + parametersDeclaration() + "\");";
        } else {
            return prefix + "\");";
        }
    }

    private String parametersDeclaration() {
        StringBuilder sb = new StringBuilder();
        for (IParameter iParameter : parameters()) {
            sb.append(" ")
                    .append(iParameter.name())
                    .append(" = [\" + ")
                    .append(iParameter.name())
                    .append(" + \"],");
        }

        return sb
                .deleteCharAt(sb.length() - 1)
                .toString();
    }
}
