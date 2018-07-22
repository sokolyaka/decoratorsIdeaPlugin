package com.sokolov.decoratorsIdeaPlugin.interfaceImpl;

import com.sokolov.decoratorsIdeaPlugin.javaInterface.IInterface;
import com.sokolov.decoratorsIdeaPlugin.method.MethodFromString;
import com.sokolov.decoratorsIdeaPlugin.method.OriginMethod;
import com.sokolov.decoratorsIdeaPlugin.method.OverrideMethod;
import com.sokolov.decoratorsIdeaPlugin.method.PublicMethod;

public class OriginDecorator implements IInterfaceImpl {
    private final String packageDef;
    private final String name;
    private final IInterface iInterface;

    public OriginDecorator(String packageDef, String name, IInterface iInterface) {
        this.packageDef = packageDef;
        this.name = name;
        this.iInterface = iInterface;
    }

    @Override
    public String asString() {
        StringBuilder sb = new StringBuilder();
        sb.append("package ")
                .append(packageDef)
                .append(";\n\n");
        for (String importDef : iInterface.imports()) {
            sb.append(importDef).append("\n");
        }
        sb.append("\npublic class ")
                .append(name)
                .append(" implements ")
                .append(iInterface.name())
                .append(" {\n\n")
                .append("private final ")
                .append(iInterface.name())
                .append(" origin;\n\n")
                .append("public ")
                .append(name)
                .append("(")
                .append(iInterface.name())
                .append(" origin){\nthis.origin = origin;\n}\n\n");

        for (String method : iInterface.methods()) {
            sb.append(
                    new OriginMethod(
                            new OverrideMethod(
                                    new PublicMethod(
                                            new MethodFromString(method))))
                            .implementation())
                    .append("\n");
        }

        sb.append("}\n");

        return sb.toString();
    }
}
