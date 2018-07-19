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
                                new PublicMethod("void implementation();")),
                        DefaultReturnValuesProvider.get())
                        .implementation());
    }

    @Test
    public void implementationString() {
        Assert.assertEquals(
                "@Override public String implementation(){return null;}",
                new MethodWithEmptyBody(
                        new OverrideMethod(
                                new PublicMethod("String implementation();")),
                        DefaultReturnValuesProvider.get())
                        .implementation());
    }

    @Test
    public void implementationInt() {
        Assert.assertEquals(
                "@Override public int implementation(){return 0;}",
                new MethodWithEmptyBody(
                        new OverrideMethod(
                                new PublicMethod("int implementation();")),
                        DefaultReturnValuesProvider.get())
                        .implementation());
    }

    @Test
    public void implementationDouble() {
        Assert.assertEquals(
                "@Override public Double implementation(){return 0.0;}",
                new MethodWithEmptyBody(
                        new OverrideMethod(
                                new PublicMethod("Double implementation();")),
                        DefaultReturnValuesProvider.get())
                        .implementation());
    }

    @Test
    public void implementationFloat() {
        Assert.assertEquals(
                "@Override public float implementation(){return 0.0f;}",
                new MethodWithEmptyBody(
                        new OverrideMethod(
                                new PublicMethod("float implementation();")),
                        DefaultReturnValuesProvider.get())
                        .implementation());
    }
}