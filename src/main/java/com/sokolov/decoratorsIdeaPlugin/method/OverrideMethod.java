package com.sokolov.decoratorsIdeaPlugin.method;

public class OverrideMethod implements IMethod {
    private final IMethod origin;

    public OverrideMethod(IMethod origin) {
        this.origin = origin;
    }

    @Override
    public String implementation() {
        return "@Override " + origin.implementation();
    }
}
