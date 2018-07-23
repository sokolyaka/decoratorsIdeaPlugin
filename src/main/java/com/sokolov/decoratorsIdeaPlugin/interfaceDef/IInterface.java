package com.sokolov.decoratorsIdeaPlugin.interfaceDef;

import java.util.List;

public interface IInterface {
    String packageDef();

    List<String> imports();

    String name();

    List<String> methods();
}
