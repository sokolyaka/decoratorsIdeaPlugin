package com.sokolov.lang.java.decorator;

import com.sokolov.lang.java.constructor.ConstructorWithParams;
import com.sokolov.lang.java.constructor.FieldInitialization;
import com.sokolov.lang.java.constructor.IConstructor;
import com.sokolov.lang.java.constructor.IFieldInitialization;
import com.sokolov.lang.java.field.FieldFromString;
import com.sokolov.lang.java.field.FinalField;
import com.sokolov.lang.java.field.IField;
import com.sokolov.lang.java.field.PrivateField;
import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.method.java.async.IAsyncMethodBuilder;
import com.sokolov.lang.java.parameter.IParameter;
import com.sokolov.lang.java.parameter.Parameter;

import java.util.ArrayList;
import java.util.List;

public class AsyncDecorator implements IDecorator {
    private final IDecorator origin;
    private final IAsyncMethodBuilder asyncMethodBuilder;

    public AsyncDecorator(IDecorator origin, IAsyncMethodBuilder asyncMethodBuilder) {
        this.origin = origin;
        this.asyncMethodBuilder = asyncMethodBuilder;
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
        imports.add("import java.util.concurrent.ExecutorService;");
        return imports;
    }

    @Override
    public String className() {
        return origin.className();
    }

    @Override
    public List<IField> fields() {
        List<IField> fields = new ArrayList<>(origin.fields());
        fields.add(new PrivateField(new FinalField(new FieldFromString("ExecutorService", "executorService"))));
        return fields;
    }

    @Override
    public IConstructor constructor() {
        IConstructor oConstructor = origin.constructor();
        List<IParameter> parameters = new ArrayList<>(oConstructor.params());
        parameters.add(new Parameter("ExecutorService", "executorService"));

        List<IFieldInitialization> initializations = new ArrayList<>(oConstructor.initializations());
        initializations.add(new FieldInitialization("executorService", "executorService"));

        return new ConstructorWithParams(oConstructor.name(), parameters, oConstructor.accessLevel(), initializations);
    }

    @Override
    public List<IMethod> methods() {
        List<IMethod> result = new ArrayList<>();
        for (IMethod method : origin.methods()) {
            result.add(
                    asyncMethodBuilder
                            .setOrigin(method)
                            .build());
        }
        return result;
    }

}
