package com.sokolov.lang.java.method.android.inMainThread;

import com.sokolov.lang.java.method.IMethod;

public class Java7InMainThreadMethodBuilder implements IInMainThreadMethodBuilder {
    private IMethod origin;

    @Override
    public IInMainThreadMethodBuilder setOrigin(IMethod origin) {
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
