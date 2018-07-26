package com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyClassName;

public class VerifyClassNameUseCase implements IVerifyClassNameUseCase {
    @Override
    public boolean execute(String className) {
        return className.matches("[A-Z_]($[A-Z_]|[\\w_])*");
    }
}
