package com.sokolov.decoratorsIdeaPlugin.method;

import com.sokolov.decoratorsIdeaPlugin.defaultReturnValue.DefaultReturnValuesProvider;
import org.junit.Assert;
import org.junit.Test;

public class MethodWithEmptyBodyTest {

    @Test
    public void implementation() {
        Assert.assertEquals(
                "@Override public void implementation(){}",
                new MethodWithEmptyBody(
                        new OverrideMethod(
                                new PublicMethod(
                                        new MethodFromString("void implementation();"))),
                        DefaultReturnValuesProvider.get())
                        .implementation());
    }

    @Test
    public void implementationMap() {
        Assert.assertEquals(
                "@Override public Map<String, String> implementation(){return null;}",
                new MethodWithEmptyBody(
                        new OverrideMethod(
                                new PublicMethod(
                                        new MethodFromString("Map<String, String> implementation();"))),
                        DefaultReturnValuesProvider.get())
                        .implementation());
    }

    @Test
    public void implementationString() {
        Assert.assertEquals(
                "@Override public String implementation(){return null;}",
                new MethodWithEmptyBody(
                        new OverrideMethod(
                                new PublicMethod(
                                        new MethodFromString("String implementation();"))),
                        DefaultReturnValuesProvider.get())
                        .implementation());
    }

    @Test
    public void implementationInt() {
        Assert.assertEquals(
                "@Override public int implementation(){return 0;}",
                new MethodWithEmptyBody(
                        new OverrideMethod(
                                new PublicMethod(
                                        new MethodFromString("int implementation();"))),
                        DefaultReturnValuesProvider.get())
                        .implementation());
    }

    @Test
    public void implementationDouble() {
        Assert.assertEquals(
                "@Override public Double implementation(){return 0.0;}",
                new MethodWithEmptyBody(
                        new OverrideMethod(
                                new PublicMethod(
                                        new MethodFromString("Double implementation();"))),
                        DefaultReturnValuesProvider.get())
                        .implementation());
    }

    @Test
    public void implementationFloat() {
        Assert.assertEquals(
                "@Override public float implementation(){return 0.0f;}",
                new MethodWithEmptyBody(
                        new OverrideMethod(
                                new PublicMethod(
                                        new MethodFromString("float implementation();"))),
                        DefaultReturnValuesProvider.get())
                        .implementation());
    }
}