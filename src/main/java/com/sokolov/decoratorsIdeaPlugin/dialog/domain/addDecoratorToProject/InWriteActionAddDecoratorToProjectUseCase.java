package com.sokolov.decoratorsIdeaPlugin.dialog.domain.addDecoratorToProject;

import com.intellij.openapi.application.Application;
import com.sokolov.lang.java.decorator.IDecorator;

public class InWriteActionAddDecoratorToProjectUseCase implements IAddDecoratorToProjectUseCase {
    private final IAddDecoratorToProjectUseCase origin;
    private final Application application;

    public InWriteActionAddDecoratorToProjectUseCase(IAddDecoratorToProjectUseCase origin, Application application) {
        this.origin = origin;
        this.application = application;
    }

    @Override
    public void execute(IDecorator decorator, String moduleName) {
        application.runWriteAction(() -> origin.execute(decorator, moduleName));
    }
}
