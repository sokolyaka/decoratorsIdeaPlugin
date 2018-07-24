package com.sokolov.lang.java.constructor;

import com.sokolov.lang.java.parameter.IParameter;

import java.util.List;

public class ToStringConstructor implements IConstructor {
    private final IConstructor origin;

    public ToStringConstructor(IConstructor origin) {
        this.origin = origin;
    }

    @Override
    public String name() {
        return origin.name();
    }

    @Override
    public List<IParameter> params() {
        return origin.params();
    }

    @Override
    public String accessLevel() {
        return origin.accessLevel();
    }

    @Override
    public List<IFieldInitialization> initializations() {
        return origin.initializations();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(accessLevel())
                .append(" ")
                .append(name())
                .append("(");

        List<IParameter> params = params();
        for (int i = 0; i < params.size(); i++) {
            IParameter parameter = params.get(i);
            sb.append(parameter.type())
                    .append(" ")
                    .append(parameter.name());
            if (i < params.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("){");
        for (IFieldInitialization fieldInitialization : initializations()) {
            sb
                    .append("this.")
                    .append(fieldInitialization.name())
                    .append(" = ")
                    .append(fieldInitialization.value())
                    .append(";");
        }

        sb.append("}");
        return sb.toString();
    }
}
