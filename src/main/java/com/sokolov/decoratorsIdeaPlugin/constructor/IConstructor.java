package com.sokolov.decoratorsIdeaPlugin.constructor;

import com.sokolov.decoratorsIdeaPlugin.parameter.IParameter;

import java.util.List;

public interface IConstructor {

    List<IParameter> params();

    String accessLevel();
}
