package com.sokolov.decoratorsIdeaPlugin.method;

import com.sokolov.decoratorsIdeaPlugin.parameter.IParameter;
import com.sokolov.decoratorsIdeaPlugin.parameter.ListOfParametersFromString;
import com.sokolov.decoratorsIdeaPlugin.parameter.ParameterFromString;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ListOfParametersFromStringTest {

    @Test
    public void asList() {
        List<ParameterFromString> expected = Arrays.asList(
                new ParameterFromString("String s"),
                new ParameterFromString("String s"));

        List<IParameter> actual = new ListOfParametersFromString("String s, String s").asList();
        for (int i = 0; i < actual.size(); i++) {
            IParameter expectedParam = expected.get(i);
            IParameter actualParam = actual.get(i);
            Assert.assertEquals(expectedParam.name(), actualParam.name());
            Assert.assertEquals(expectedParam.type(), actualParam.type());
        }
    }

    @Test
    public void asListParamWithGeneric() {
        List<ParameterFromString> expected = Arrays.asList(
                new ParameterFromString("Map<String, String> map"),
                new ParameterFromString("int aInt"));

        List<IParameter> actual = new ListOfParametersFromString("Map<String, String> map, int aInt").asList();
        for (int i = 0; i < expected.size(); i++) {
            IParameter expectedParam = expected.get(i);
            IParameter actualParam = actual.get(i);
            Assert.assertEquals(expectedParam.name(), actualParam.name());
            Assert.assertEquals(expectedParam.type(), actualParam.type());
        }
    }
}