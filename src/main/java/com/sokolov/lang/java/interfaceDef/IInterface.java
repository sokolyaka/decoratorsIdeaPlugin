package com.sokolov.lang.java.interfaceDef;

import java.util.List;

public interface IInterface {
    String packageDef();

    List<String> imports();

    String name();

    List<String> methods();

    String accessLevel();
}
