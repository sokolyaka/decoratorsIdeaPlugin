package com.sokolov.decoratorsIdeaPlugin.constructor;

import com.sokolov.decoratorsIdeaPlugin.method.parameter.IParameter;

import java.util.List;

public interface IConstructor {

    List<IParameter> params();

    String accessLevel();
}
