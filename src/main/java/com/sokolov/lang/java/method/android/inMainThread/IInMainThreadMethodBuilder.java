package com.sokolov.lang.java.method.android.inMainThread;

import com.sokolov.lang.java.method.IMethod;

public interface IInMainThreadMethodBuilder {
    IInMainThreadMethodBuilder setOrigin(IMethod origin);

    IMethod build();
}
