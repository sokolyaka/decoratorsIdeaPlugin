package com.sokolov.lang.java.method.android.inMainThread;

import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.method.IMethodBuilder;

public class Java7InMainThreadMethodBuilder implements IMethodBuilder {
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

        return new Java7InMainThreadMethod(origin);
    }
}
