package com.sokolov.decoratorsIdeaPlugin.method;

public class FromStringMethod implements IMethod {
    private final String methodDeclaration;

    public FromStringMethod(String methodDeclaration) {
        this.methodDeclaration = methodDeclaration;
    }

    @Override
    public String implementation() {
        return methodDeclaration;
    }
}
