package com.sokolov.lang.java.decorator;

import com.sokolov.lang.java.constructor.ConstructorWithParams;
import com.sokolov.lang.java.constructor.FieldInitialization;
import com.sokolov.lang.java.constructor.IConstructor;
import com.sokolov.lang.java.constructor.IFieldInitialization;
import com.sokolov.lang.java.field.FieldFromString;
import com.sokolov.lang.java.field.FinalField;
import com.sokolov.lang.java.field.IField;
import com.sokolov.lang.java.field.PrivateField;
import com.sokolov.lang.java.method.android.AndroidLoggableMethod;
import com.sokolov.lang.java.method.IMethod;

import java.util.ArrayList;
import java.util.List;

public class AndroidLoggableDecorator implements IDecorator {
    private static final String TAG_FIELD_NAME = "TAG";
    private final IDecorator origin;

    public AndroidLoggableDecorator(IDecorator origin) {
        this.origin = origin;
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
        fields.add(new PrivateField(new FinalField(new FieldFromString("String", TAG_FIELD_NAME))));
        return fields;
    }

    @Override
    public IConstructor constructor() {
        IConstructor oConstructor = origin.constructor();
        List<IFieldInitialization> initializations = new ArrayList<>(oConstructor.initializations());
        initializations.add(new FieldInitialization(TAG_FIELD_NAME, className()+".class.getSimpleName()"));
        return new ConstructorWithParams(oConstructor.name(), oConstructor.params(), oConstructor.accessLevel(), initializations);

    }

    @Override
    public List<IMethod> methods() {
        List<IMethod> result = new ArrayList<>();
        for (IMethod method : origin.methods()) {
            result.add(new AndroidLoggableMethod(method, TAG_FIELD_NAME));
        }
        return result;

    }
}