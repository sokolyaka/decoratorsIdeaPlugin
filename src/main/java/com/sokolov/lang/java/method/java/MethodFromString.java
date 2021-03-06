package com.sokolov.lang.java.method.java;

import com.sokolov.lang.java.method.IMethod;
import com.sokolov.lang.java.parameter.IParameter;
import com.sokolov.lang.java.parameter.ListOfParametersFromString;

import java.util.List;

public class MethodFromString implements IMethod {
    private final String methodDeclaration;

    public MethodFromString(String methodDeclaration) {
        this.methodDeclaration = methodDeclaration;
    }

    @Override
    public String name() {
        String methodDefWithoutParams =
                methodDeclaration.substring(
                        0,
                        methodDeclaration.indexOf('('))
                        .trim();
        return
                methodDefWithoutParams.substring(
                        methodDefWithoutParams.lastIndexOf(" ") + 1);
    }

    @Override
    public List<IParameter> parameters() {
        String params = methodDeclaration.substring(
                methodDeclaration.indexOf('(') + 1,
                methodDeclaration.indexOf(')'));
        return new ListOfParametersFromString(params).asList();
    }

    @Override
    public String implementation() {
        return methodDeclaration;
    }
}
