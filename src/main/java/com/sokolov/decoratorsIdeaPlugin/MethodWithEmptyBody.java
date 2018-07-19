package com.sokolov.decoratorsIdeaPlugin;

public class MethodWithEmptyBody implements IMethod {
    private final String methodDeclaration;

    public MethodWithEmptyBody(String methodDeclaration) {
        this.methodDeclaration = methodDeclaration;
    }

    @Override
    public String implementation() {
        return "@Override public " + methodDeclaration.substring(0, methodDeclaration.lastIndexOf(";")) + "{}";
    }
}
