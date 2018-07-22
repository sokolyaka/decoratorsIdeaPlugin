package com.sokolov.decoratorsIdeaPlugin.method;

import com.sokolov.decoratorsIdeaPlugin.method.parameter.IParameter;
import com.sokolov.decoratorsIdeaPlugin.method.parameter.ListOfParametersFromString;

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
        for (IParameter param : parameters) {
            sb.append(param.name()).append(',');
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
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
