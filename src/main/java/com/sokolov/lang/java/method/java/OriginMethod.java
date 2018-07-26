package com.sokolov.lang.java.method.java;

import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.parameter.IParameter;
import com.sokolov.lang.java.parameter.ListOfParametersFromString;

import java.util.List;

public class OriginMethod implements IMethod {
    private final IMethod origin;

    public OriginMethod(IMethod origin) {
        this.origin = origin;
    }

    private static String getMethodName(String methodNameWithParams) {
        return methodNameWithParams.substring(0, methodNameWithParams.indexOf('('));
    }

    private static String getParamNames(String methodNameWithParams) {
        List<IParameter> parameters =
                new ListOfParametersFromString(
                        methodNameWithParams
                                .substring(
                                        methodNameWithParams.indexOf('(') + 1,
                                        methodNameWithParams.indexOf(')')))
                        .asList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.size(); i++) {
            IParameter param = parameters.get(i);
            sb.append(param.name());
            if (i < parameters.size() - 1) {
                sb.append(',');
            }
        }
        return sb.toString();
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
        String implementation = origin.implementation().trim();
        if (implementation.charAt(implementation.length() - 1) != ';') {
            throw new IllegalArgumentException("method already has implementation");
        }
        String noSemicolon = implementation.substring(0, implementation.length() - 1);

        int startIndex = findStartIndex(noSemicolon);
        int endIndex = noSemicolon.length();

        String methodNameWithParams = noSemicolon.substring(startIndex, endIndex);
        String parameterNames = getParamNames(methodNameWithParams);
        String originMethodCall =
                "origin." + getMethodName(methodNameWithParams) + "(" + parameterNames + ");";
        if (noSemicolon.contains("void")) {
            return noSemicolon + "{" + originMethodCall + "}";
        } else {
            return noSemicolon + "{return " + originMethodCall + "}";
        }
    }

    private int findStartIndex(String implementation) {
        for (int i = implementation.indexOf("(") - 1; i >= 0; i--) {
            if (implementation.charAt(i) == ' ') {
                return i + 1;
            }
        }
        throw new IllegalArgumentException("invalid method declaration = " + implementation);
    }
}
