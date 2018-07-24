package com.sokolov.lang.java.decorator;

import com.sokolov.lang.java.constructor.ConstructorWithParams;
import com.sokolov.lang.java.constructor.FieldInitialization;
import com.sokolov.lang.java.constructor.IConstructor;
import com.sokolov.lang.java.field.FieldFromString;
import com.sokolov.lang.java.field.FinalField;
import com.sokolov.lang.java.field.IField;
import com.sokolov.lang.java.field.PrivateField;
import com.sokolov.lang.java.interfaceDef.IInterface;
import com.sokolov.lang.java.method.*;
import com.sokolov.lang.java.method.java.MethodFromString;
import com.sokolov.lang.java.method.java.OriginMethod;
import com.sokolov.lang.java.method.java.OverrideMethod;
import com.sokolov.lang.java.method.java.PublicMethod;
import com.sokolov.lang.java.parameter.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OriginDecorator implements IDecorator {
    private final String packageDef;
    private final String name;
    private final IInterface iInterface;

    public OriginDecorator(String packageDef, String name, IInterface iInterface) {
        this.packageDef = packageDef;
        this.name = name;
        this.iInterface = iInterface;
    }

    public OriginDecorator(String name, IInterface iInterface) {
        this.packageDef = iInterface.packageDef();
        this.name = name;
        this.iInterface = iInterface;
    }

    @Override
    public String packageDef() {
        return "package " + packageDef + ";";
    }

    @Override
    public List<String> imports() {
        return iInterface.imports();
    }

    @Override
    public String className() {
        return "public class " + name + " implements " + iInterface.name();
    }

    @Override
    public List<IField> fields() {
        return Arrays.asList(new PrivateField(new FinalField(new FieldFromString(iInterface.name(), "origin"))));
    }

    @Override
    public IConstructor constructor() {
        return
                new ConstructorWithParams(
                        name,
                        Arrays.asList(
                                new Parameter(
                                        iInterface.name(),
                                        "origin")),
                        "public",
                        Arrays.asList(
                                new FieldInitialization(
                                        "origin",
                                        "origin")));
    }

    @Override
    public List<IMethod> methods() {
        List<IMethod> methods = new ArrayList<>();
        for (String method : iInterface.methods()) {
            methods.add(
                    new OriginMethod(
                            new OverrideMethod(
                                    new PublicMethod(
                                            new MethodFromString(method)))));
        }

        return methods;
    }
}
