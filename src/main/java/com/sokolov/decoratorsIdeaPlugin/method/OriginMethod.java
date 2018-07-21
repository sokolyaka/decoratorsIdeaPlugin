package com.sokolov.decoratorsIdeaPlugin.method;

public class OriginMethod implements IMethod {
    private final IMethod origin;

    public OriginMethod(IMethod origin) {
        this.origin = origin;
    }

    @Override
    public String implementation() {
        String implementation = origin.implementation().trim();
        if (implementation.charAt(implementation.length() - 1) != ';') {
            throw new IllegalArgumentException("method already has implementation");
        }
        String noSemicolon = implementation.substring(0, implementation.length() - 1);

        int startIndex = findStartIndex(noSemicolon);
        int endIndex = noSemicolon.length();

        if (noSemicolon.contains("void")) {
            return noSemicolon + "{origin." + noSemicolon.substring(startIndex, endIndex) + ";}";
        } else {
            return noSemicolon + "{return origin." + noSemicolon.substring(startIndex, endIndex) + ";}";
        }
    }

    private int findStartIndex(String implementation) {
        for (int i = implementation.indexOf("(") - 1; i >= 0; i--) {
            if (implementation.charAt(i) == ' ') {
                return i + 1;
            }
        }
        throw new IllegalArgumentException("invalid method declaration = " + implementation);
    }
}
