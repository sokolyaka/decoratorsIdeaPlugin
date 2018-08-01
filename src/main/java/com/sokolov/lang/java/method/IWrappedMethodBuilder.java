package com.sokolov.lang.java.method;

public interface IWrappedMethodBuilder extends IMethodBuilder {

    @Override
    IWrappedMethodBuilder setOrigin(IMethod origin);

    IWrappedMethodBuilder setStartOfWrapper(String setStartOfWrapper);

    IWrappedMethodBuilder setEndOfWrapper(String setEndOfWrapper);

    @Override
    IMethod build();
}
