package com.sokolov.lang.java.decorator;

import com.sokolov.lang.java.constructor.IConstructor;
import com.sokolov.lang.java.field.IField;
import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.method.java.SafeMethod;

import java.util.ArrayList;
import java.util.List;

public class SafeDecorator implements IDecorator {
    private final IDecorator origin;

    public SafeDecorator(IDecorator origin) {
        this.origin = origin;
    }

    @Override
    public String accessLevel() {
        return origin.accessLevel();
    }

    @Override
    public String packageDef() {
        return origin.packageDef();
    }

    @Override
    public List<String> imports() {
        return origin.imports();
    }

    @Override
    public String className() {
        return origin.className();
    }

    @Override
    public List<IField> fields() {
        return origin.fields();
    }

    @Override
    public IConstructor constructor() {
        return origin.constructor();
    }

    @Override
    public List<IMethod> methods() {
        List<IMethod> result = new ArrayList<>();
        for (IMethod method : origin.methods()) {
            result.add(new SafeMethod(method));
        }
        return result;
    }
}
