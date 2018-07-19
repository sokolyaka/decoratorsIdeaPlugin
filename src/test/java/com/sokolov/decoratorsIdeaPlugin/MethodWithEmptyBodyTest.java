package com.sokolov.decoratorsIdeaPlugin;

import com.sokolov.decoratorsIdeaPlugin.defaultReturnValue.DefaultReturnValuesProvider;
import org.junit.Assert;
import org.junit.Test;

public class MethodWithEmptyBodyTest {

    @Test
    public void implementation() {
        Assert.assertEquals(
                "@Override public void implementation(){}",
                new MethodWithEmptyBody("void implementation();", DefaultReturnValuesProvider.get()).implementation());
    }

    @Test
    public void implementationString() {
        Assert.assertEquals(
                "@Override public String implementation(){return null;}",
                new MethodWithEmptyBody("String implementation();", DefaultReturnValuesProvider.get()).implementation());
    }

    @Test
    public void implementationInt() {
        Assert.assertEquals(
                "@Override public int implementation(){return 0;}",
                new MethodWithEmptyBody("int implementation();", DefaultReturnValuesProvider.get()).implementation());
    }

    @Test
    public void implementationDouble() {
        Assert.assertEquals(
                "@Override public Double implementation(){return 0.0;}",
                new MethodWithEmptyBody("Double implementation();", DefaultReturnValuesProvider.get()).implementation());
    }

    @Test
    public void implementationFloat() {
        Assert.assertEquals(
                "@Override public float implementation(){return 0.0f;}",
                new MethodWithEmptyBody("float implementation();", DefaultReturnValuesProvider.get()).implementation());
    }
}