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
import com.sokolov.lang.java.method.java.SyncMethod;

import java.util.ArrayList;
import java.util.List;

public class SyncDecorator implements IDecorator {
    private static final String SYNC_OBJECT_FIELD_NAME = "syncObject";
    private final IDecorator origin;

    public SyncDecorator(IDecorator origin) {
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
        List<IField> fields = new ArrayList<>(origin.fields());
        fields.add(new PrivateField(new FinalField(new FieldFromString("Object", SYNC_OBJECT_FIELD_NAME))));
        return fields;
    }

    @Override
    public IConstructor constructor() {
        IConstructor oConstructor = origin.constructor();
        List<IFieldInitialization> initializations = new ArrayList<>(oConstructor.initializations());
        initializations.add(new FieldInitialization(SYNC_OBJECT_FIELD_NAME, "new Object()"));
        return new ConstructorWithParams(oConstructor.name(), oConstructor.params(), oConstructor.accessLevel(), initializations);
    }

    @Override
    public List<IMethod> methods() {
        List<IMethod> result = new ArrayList<>();
        for (IMethod method : origin.methods()) {
            result.add(new SyncMethod(method, SYNC_OBJECT_FIELD_NAME));
        }
        return result;

    }
}