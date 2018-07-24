package com.sokolov.lang.java.method.async;

import com.sokolov.lang.java.method.IMethod;

public interface IAsyncMethodBuilder {
    IAsyncMethodBuilder setOrigin(IMethod origin);

    IMethod build();
}
