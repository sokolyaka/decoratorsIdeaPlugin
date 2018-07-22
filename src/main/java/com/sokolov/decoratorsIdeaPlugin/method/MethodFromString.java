package com.sokolov.decoratorsIdeaPlugin.method;

public class MethodFromString implements IMethod {
    private final String methodDeclaration;

    public MethodFromString(String methodDeclaration) {
        this.methodDeclaration = methodDeclaration;
    }

    @Override
    public String implementation() {
        return methodDeclaration;
    }
}
