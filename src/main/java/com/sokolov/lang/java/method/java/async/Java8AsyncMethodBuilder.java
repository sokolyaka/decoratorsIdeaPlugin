package com.sokolov.lang.java.method.java.async;

import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.method.IMethodBuilder;

public class Java8AsyncMethodBuilder implements IMethodBuilder {
    private IMethod origin;

    @Override
    public IMethodBuilder setOrigin(IMethod origin) {
        this.origin = origin;

        return this;
    }

    @Override
    public IMethod build() {
        if (origin == null) {
            throw new IllegalStateException("setOrigin() is required");
        }
        return new Java8AsyncMethod(origin);
    }
}
