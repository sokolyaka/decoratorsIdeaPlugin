package com.sokolov.lang.java.method;

import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public interface IMethod {

    List<IParameter> parameters();

    String implementation();
}
