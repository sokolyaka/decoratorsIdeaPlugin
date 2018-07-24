package com.sokolov.lang.java.method;

import com.sokolov.lang.java.parameter.IParameter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MethodFromStringTest {

    @Test
    public void parameters() {
        List<IParameter> parameters = new MethodFromString("Map<String, String> implementation(String s);").parameters();
        IParameter parameter0 = parameters.get(0);
        Assert.assertEquals("s", parameter0.name());
        Assert.assertEquals("String", parameter0.type());
    }

    @Test
    public void name() {
        Assert.assertEquals(
                "implementation",
                new MethodFromString(
                        "Map<String, String> implementation(String s);")
                        .name());
    }

    @Test
    public void nameWithExtraSpaces() {
        Assert.assertEquals(
                "implementation",
                new MethodFromString(
                        "Map<String, String>   implementation (String s);")
                        .name());
    }
}