package com.sokolov.decoratorsIdeaPlugin.dialog.domain;

import com.sokolov.lang.java.decorator.IDecorator;
import com.sokolov.lang.java.interfaceDef.IInterface;

public interface ICreateDecoratorUseCase {
    IDecorator execute(String packageDef, String className, int decoratorType, IInterface iInterface);
}
