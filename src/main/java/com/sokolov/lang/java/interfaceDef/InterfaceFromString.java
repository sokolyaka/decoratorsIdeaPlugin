package com.sokolov.lang.java.interfaceDef;

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
                        interfaceStr.indexOf("package ") + "package ".length(),
                        interfaceStr.indexOf(';'));
    }

    @Override
    public List<String> imports() {
        if (!interfaceStr.contains("import ")) {
            return Collections.emptyList();
        }

        List<String> imports = new ArrayList<>();
        int fromIndex = 0;
        int curImportIndex = 0;
        while ((curImportIndex = interfaceStr.indexOf("import ", fromIndex)) > 0) {
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
    public String name() {
        return
                interfaceStr
                        .substring(
                                interfaceStr.indexOf("interface ") + "interface ".length(),
                                interfaceStr.indexOf('{'))
                        .trim();
    }

    @Override
    public List<String> methods() {
        String body =
                interfaceStr.substring(
                        interfaceStr.indexOf('{') + 1,
                        interfaceStr.lastIndexOf('}'));
        String[] methods = body.split(";");
        List<String> result = new ArrayList<>();
        for (int i = 0; i < methods.length - 1; i++) {
            String method = methods[i];
            result.add(method.trim() + ";");
        }
        return result;
    }

    @Override
    public String accessLevel() {
        String head = interfaceStr
                .substring(
                        0,
                        interfaceStr.indexOf("interface "))
                .trim();
        if (head.contains("public")) {
            return "public";
        } else {
            return "";
        }
    }
}
