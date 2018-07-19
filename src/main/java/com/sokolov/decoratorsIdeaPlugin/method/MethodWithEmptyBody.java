package com.sokolov.decoratorsIdeaPlugin.method;

import java.util.Map;

public class MethodWithEmptyBody implements IMethod {
    private final String methodDeclaration;
    private final Map<String, String> defaultReturnValues;

    public MethodWithEmptyBody(String methodDeclaration, Map<String, String> defaultReturnValues) {
        this.methodDeclaration = methodDeclaration;
        this.defaultReturnValues = defaultReturnValues;
    }

    @Override
    public String implementation() {
        String overridePublicMethod = "@Override public " + methodDeclaration.substring(0, methodDeclaration.lastIndexOf(";"));
        if (isVoid()) {
            return overridePublicMethod + "{}";
        } else {
            return overridePublicMethod + "{return " + getDefaultReturnType(getReturnType()) + ";}";
        }
    }

    private String getReturnType() {
        return methodDeclaration.substring(0, methodDeclaration.indexOf(" "));
    }

    private String getDefaultReturnType(String returnType) {
        String result = defaultReturnValues.get(returnType);
        if (result != null) {
            return result;
        }

        throw new IllegalArgumentException("returnType = " + returnType);
    }

    private boolean isVoid() {
        return methodDeclaration.contains("void");
    }
}
