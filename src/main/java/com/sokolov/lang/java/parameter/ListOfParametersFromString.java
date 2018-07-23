package com.sokolov.lang.java.parameter;

import java.util.ArrayList;
import java.util.List;

public class ListOfParametersFromString implements IListOfParameters {
    private final String listOfParams;

    public ListOfParametersFromString(String listOfParams) {
        this.listOfParams = listOfParams;
    }

    @Override
    public List<IParameter> asList() {
        String[] params;
        if (listOfParams.contains("<")) {
            params = getParamsWithGenerics();
        } else {
            params = listOfParams.split(",");
        }
        List<IParameter> result = new ArrayList<>();
        for (String param : params) {
            result.add(new ParameterFromString(param));
        }

        return result;
    }

    private String[] getParamsWithGenerics() {
        char[] chars = listOfParams.toCharArray();
        List<String> result = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        int rubinCount = 0;
        for (char c : chars) {
            if (rubinCount == 0 && c == ',') {
                result.add(sb.toString());
                sb = new StringBuilder();
            } else if (c == '<') {
                rubinCount++;
                sb.append(c);
            } else if (c == '>') {
                rubinCount--;
                sb.append(c);
            } else {
                sb.append(c);
            }
        }
        result.add(sb.toString());
        return result.toArray(new String[0]);
    }
}
