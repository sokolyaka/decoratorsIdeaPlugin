package com.sokolov.lang.java.decorator;

import com.sokolov.lang.java.constructor.IConstructor;
import com.sokolov.lang.java.field.IField;
import com.sokolov.lang.java.method.IMethod;

import java.util.List;

public interface IDecorator {
    String accessLevel();

    String packageDef();

    List<String> imports();

    String className();

    List<IField> fields();

    IConstructor constructor();

    List<IMethod> methods();
}
