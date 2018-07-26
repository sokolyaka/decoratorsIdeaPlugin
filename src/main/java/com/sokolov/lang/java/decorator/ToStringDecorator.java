package com.sokolov.lang.java.decorator;

import com.sokolov.lang.java.constructor.IConstructor;
import com.sokolov.lang.java.constructor.ToStringConstructor;
import com.sokolov.lang.java.field.IField;
import com.sokolov.lang.java.method.IMethod;

import java.util.List;

public class ToStringDecorator implements IDecorator {
    private final IDecorator origin;

    public ToStringDecorator(IDecorator origin) {
        this.origin = origin;
    }

    @Override
    public String interfaceName() {
        return origin.interfaceName();
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
        return origin.methods();
    }

    public String asString() {
        StringBuilder sb = new StringBuilder();
        sb.append(packageDef());
        for (String importDef : imports()) {
            sb.append(importDef);
        }
        sb.append(accessLevel())
                .append(" class ")
                .append(className())
                .append(" implements ")
                .append(interfaceName())
                .append("{");

        for (IField field : fields()) {
            sb.append(field.asString());
        }

        sb.append(new ToStringConstructor(constructor()));

        for (IMethod method : methods()) {
            sb.append(
                    method.implementation());
        }

        sb.append("}");

        return sb.toString();
    }
}
