package com.sokolov.lang.java.method;

import com.sokolov.lang.java.method.java.CustomWrappedMethod;

public class WrappedMethodBuilder implements IWrappedMethodBuilder {

    private IMethod origin = null;
    private String startOfWrapper = null;
    private String endOfWrapper = null;

    @Override
    public IWrappedMethodBuilder setOrigin(IMethod origin) {
        this.origin = origin;
        return this;
    }

    @Override
    public IWrappedMethodBuilder setStartOfWrapper(String startOfWrapper) {
        this.startOfWrapper = startOfWrapper;
        return this;
    }

    @Override
    public IWrappedMethodBuilder setEndOfWrapper(String endOfWrapper) {
        this.endOfWrapper = endOfWrapper;
        return this;
    }

    @Override
    public IMethod build() {
        assert origin != null;
        assert startOfWrapper != null;
        assert endOfWrapper != null;
        return
                new CustomWrappedMethod(
                        origin,
                        startOfWrapper,
                        endOfWrapper);
    }
}
