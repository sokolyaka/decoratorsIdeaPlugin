package com.sokolov.lang.java.constructor;

import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public interface IConstructor {

    List<IParameter> params();

    String accessLevel();
}
