package com.sokolov.decoratorsIdeaPlugin.dialog.domain;

public interface ICreateDecoratorUseCase {
    void execute(String packageDef, String className, int decoratorType);
}
