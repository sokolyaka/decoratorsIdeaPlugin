package com.sokolov.lang.java.method;

public interface IMethodBuilder {
    IMethodBuilder setOrigin(IMethod origin);

    IMethod build();
}
