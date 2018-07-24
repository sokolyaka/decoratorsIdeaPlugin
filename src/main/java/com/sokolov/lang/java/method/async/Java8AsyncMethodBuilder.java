package com.sokolov.lang.java.method.async;

import com.sokolov.lang.java.method.IMethod;

public class Java8AsyncMethodBuilder implements IAsyncMethodBuilder {
    private IMethod origin;

    @Override
    public IAsyncMethodBuilder setOrigin(IMethod origin) {
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
