package com.sokolov.lang.java.method.java.async;

import com.sokolov.lang.java.method.IMethod;

public interface IAsyncMethodBuilder {
    IAsyncMethodBuilder setOrigin(IMethod origin);

    IMethod build();
}
