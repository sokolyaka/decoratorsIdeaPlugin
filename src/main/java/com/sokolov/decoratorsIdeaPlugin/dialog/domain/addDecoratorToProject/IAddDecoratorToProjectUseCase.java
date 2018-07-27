package com.sokolov.decoratorsIdeaPlugin.dialog.domain.addDecoratorToProject;

import com.sokolov.lang.java.decorator.IDecorator;

public interface IAddDecoratorToProjectUseCase {

    void execute(IDecorator decorator, String moduleName);
}
