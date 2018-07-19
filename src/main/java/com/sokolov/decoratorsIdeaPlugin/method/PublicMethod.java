package com.sokolov.decoratorsIdeaPlugin.method;

public class PublicMethod implements IMethod {

    private final String methodDeclaration;

    public PublicMethod(String methodDeclaration) {
        this.methodDeclaration = methodDeclaration;
    }

    @Override
    public String implementation() {
        return "public " + methodDeclaration;
    }
}
