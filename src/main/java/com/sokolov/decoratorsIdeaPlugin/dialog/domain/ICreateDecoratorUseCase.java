package com.sokolov.decoratorsIdeaPlugin.dialog.domain;

import com.sokolov.lang.java.interfaceDef.IInterface;

public interface ICreateDecoratorUseCase {
    void execute(String packageDef, String className, int decoratorType, IInterface iInterface);
}
