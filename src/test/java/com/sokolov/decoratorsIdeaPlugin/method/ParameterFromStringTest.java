package com.sokolov.decoratorsIdeaPlugin.method;

import com.sokolov.decoratorsIdeaPlugin.method.parameter.ParameterFromString;
import org.junit.Assert;
import org.junit.Test;

public class ParameterFromStringTest {

    @Test
    public void type() {
        Assert.assertEquals(
                "String",
                new ParameterFromString("String s").type());
    }

    @Test
    public void typeGenerics() {
        Assert.assertEquals(
                "Map<String, String>",
                new ParameterFromString("Map<String, String> map").type());
    }

    @Test
    public void name() {
        Assert.assertEquals(
                "map",
                new ParameterFromString("Map<String, String> map").name());
    }
}