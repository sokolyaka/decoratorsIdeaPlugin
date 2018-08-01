package com.sokolov.lang.java.decorator;

import com.sokolov.lang.java.constructor.ConstructorWithParams;
import com.sokolov.lang.java.constructor.IConstructor;
import com.sokolov.lang.java.constructor.IFieldInitialization;
import com.sokolov.lang.java.field.IField;
import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.method.IMethodBuilder;

import java.util.ArrayList;
import java.util.List;

public class CustomDecorator implements IDecorator {
    private final IDecorator origin;
    private final IMethodBuilder methodBuilder;
    private final List<IFieldInitialization> fieldInitializations;
    private final List<IField> customFields;
    private final List<String> customImports;

    public CustomDecorator(IDecorator origin, IMethodBuilder methodBuilder, List<IFieldInitialization> fieldInitializations, List<IField> customFields, List<String> customImports) {
        this.origin = origin;
        this.methodBuilder = methodBuilder;
        this.fieldInitializations = fieldInitializations;
        this.customFields = customFields;
        this.customImports = customImports;
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
        ArrayList<String> imports = new ArrayList<>(origin.imports());
        imports.addAll(customImports);
        return imports;
    }

    @Override
    public String className() {
        return origin.className();
    }

    @Override
    public List<IField> fields() {
        List<IField> fields = new ArrayList<>(origin.fields());
        fields.addAll(customFields);
        return fields;
    }

    @Override
    public IConstructor constructor() {
        IConstructor oConstructor = origin.constructor();

        List<IFieldInitialization> initializations = new ArrayList<>(oConstructor.initializations());
        initializations.addAll(fieldInitializations);

        return new ConstructorWithParams(oConstructor.name(), oConstructor.params(), oConstructor.accessLevel(), initializations);
    }

    @Override
    public List<IMethod> methods() {
        List<IMethod> result = new ArrayList<>();
        for (IMethod origin : origin.methods()) {
            result.add(
                    methodBuilder
                            .setOrigin(origin)
                            .build());
        }

        return result;
    }
}
