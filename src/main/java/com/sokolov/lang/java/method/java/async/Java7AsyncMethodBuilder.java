package com.sokolov.lang.java.method.java.async;

import com.sokolov.lang.java.method.IMethod;

public class Java7AsyncMethodBuilder implements IAsyncMethodBuilder {
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
        return new Java7AsyncMethod(origin);
    }
}
