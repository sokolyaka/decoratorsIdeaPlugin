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
import com.sokolov.lang.java.method.android.inMainThread.IInMainThreadMethodBuilder;

import java.util.ArrayList;
import java.util.List;

public class InMainThreadDecorator implements IDecorator {
    private final IDecorator origin;
    private final IInMainThreadMethodBuilder inMainThreadMethodBuilder;

    public InMainThreadDecorator(IDecorator origin, IInMainThreadMethodBuilder inMainThreadMethodBuilder) {
        this.origin = origin;
        this.inMainThreadMethodBuilder = inMainThreadMethodBuilder;
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
        imports.add("import android.os.Handler;");
        imports.add("import android.os.Looper;");
        return imports;
    }

    @Override
    public String className() {
        return origin.className();
    }

    @Override
    public List<IField> fields() {
        List<IField> fields = new ArrayList<>(origin.fields());
        fields.add(new PrivateField(new FinalField(new FieldFromString("Handler", "handler"))));
        return fields;
    }

    @Override
    public IConstructor constructor() {
        IConstructor oConstructor = origin.constructor();

        List<IFieldInitialization> initializations = new ArrayList<>(oConstructor.initializations());
        initializations.add(new FieldInitialization("handler", "new Handler(Looper.getMainLooper())"));

        return new ConstructorWithParams(oConstructor.name(), oConstructor.params(), oConstructor.accessLevel(), initializations);
    }

    @Override
    public List<IMethod> methods() {
        List<IMethod> result = new ArrayList<>();
        for (IMethod method : origin.methods()) {
            result.add(
                    inMainThreadMethodBuilder
                            .setOrigin(method)
                            .build());
        }
        return result;
    }
}
