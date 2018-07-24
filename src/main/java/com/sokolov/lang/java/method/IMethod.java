package com.sokolov.lang.java.method;

import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public interface IMethod {

    String name();

    List<IParameter> parameters();

    String implementation();
}
