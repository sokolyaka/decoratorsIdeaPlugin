package com.sokolov.lang.java.method;

import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;
import java.util.Map;

public class MethodWithEmptyBody implements IMethod {
    private final IMethod origin;
    private final Map<String, String> defaultReturnValues;

    public MethodWithEmptyBody(IMethod origin, Map<String, String> defaultReturnValues) {
        this.origin = origin;
        this.defaultReturnValues = defaultReturnValues;
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
        String methodDeclaration = origin.implementation();
        String trimmedMethod = methodDeclaration.substring(0, methodDeclaration.lastIndexOf(";"));
        if (isVoid(methodDeclaration)) {
            return trimmedMethod + "{}";
        } else {
            return trimmedMethod + "{return " + getDefaultReturnType(getReturnType(methodDeclaration)) + ";}";
        }
    }

    private String getReturnType(String methodDeclaration) {
        for (String returnType : defaultReturnValues.keySet()) {
            if (methodDeclaration.contains(returnType)) {
                return returnType;
            }
        }
        return "null";
    }

    private String getDefaultReturnType(String returnType) {
        String result = defaultReturnValues.get(returnType);
        if (result != null) {
            return result;
        }

        throw new IllegalArgumentException("returnType = " + returnType);
    }

    private static boolean isVoid(String methodDeclaration) {
        return methodDeclaration.contains("void");
    }
}
