package com.sokolov.decoratorsIdeaPlugin.javaInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InterfaceFromString implements IInterface {
    private final String interfaceStr;


    public InterfaceFromString(String interfaceStr) {
        this.interfaceStr = interfaceStr;
    }

    @Override
    public String packageDef() {
        return
                interfaceStr.substring(
                        interfaceStr.indexOf("package"),
                        interfaceStr.indexOf(';') + 1);
    }

    @Override
    public List<String> imports() {
        if (!interfaceStr.contains("import")) {
            return Collections.emptyList();
        }

        List<String> imports = new ArrayList<>();
        int fromIndex = 0;
        int curImportIndex = 0;
        while ((curImportIndex = interfaceStr.indexOf("import", fromIndex)) > 0) {
            int endIndex = interfaceStr.indexOf(';', curImportIndex) + 1;
            imports.add(
                    interfaceStr.substring(
                            curImportIndex,
                            endIndex));
            fromIndex = endIndex;
        }

        return imports;
    }

    @Override
    public String head() {
        return null;
    }

    @Override
    public List<String> methods() {
        return null;
    }
}
