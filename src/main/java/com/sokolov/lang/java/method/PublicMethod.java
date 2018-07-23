package com.sokolov.lang.java.method;

public class PublicMethod implements IMethod {

    private final IMethod origin;

    public PublicMethod(IMethod origin) {
        this.origin = origin;
    }

    @Override
    public String implementation() {
        return "public " + origin.implementation();
    }
}
