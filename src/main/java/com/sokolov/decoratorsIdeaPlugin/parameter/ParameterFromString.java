package com.sokolov.decoratorsIdeaPlugin.parameter;

public class ParameterFromString implements IParameter {
    private final String parameter;

    public ParameterFromString(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String type() {
        String trim = parameter.trim();
        return trim.substring(0, trim.lastIndexOf(' '));
    }

    @Override
    public String name() {
        String trim = parameter.trim();
        return trim.substring(trim.lastIndexOf(' ') + 1);
    }
}
