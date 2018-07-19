package com.sokolov.decoratorsIdeaPlugin.method;

import java.util.Map;

public class MethodWithEmptyBody implements IMethod {
    private final IMethod origin;
    private final Map<String, String> defaultReturnValues;

    public MethodWithEmptyBody(IMethod origin, Map<String, String> defaultReturnValues) {
        this.origin = origin;
        this.defaultReturnValues = defaultReturnValues;
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
        throw new IllegalArgumentException("return type invalid = " + methodDeclaration);
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
