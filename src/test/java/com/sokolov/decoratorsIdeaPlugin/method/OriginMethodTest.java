package com.sokolov.decoratorsIdeaPlugin.method;

import org.junit.Assert;
import org.junit.Test;

public class OriginMethodTest {

    @Test
    public void implementation() {
        Assert.assertEquals(
                "@Override public void implementation(){origin.implementation();}",
                new OriginMethod(
                        new OverrideMethod(
                                new PublicMethod(
                                        new FromStringMethod("void implementation();"))))
                        .implementation());
    }

    @Test
    public void implementationWithParams() {
        Assert.assertEquals(
                "@Override public void implementation(String s, int aInt){origin.implementation(s,aInt);}",
                new OriginMethod(
                        new OverrideMethod(
                                new PublicMethod(
                                        new FromStringMethod("void implementation(String s, int aInt);"))))
                        .implementation());
    }

    @Test
    public void implementationWithParam() {
        Assert.assertEquals(
                "@Override public void implementation(String s){origin.implementation(s);}",
                new OriginMethod(
                        new OverrideMethod(
                                new PublicMethod(
                                        new FromStringMethod("void implementation(String s);"))))
                        .implementation());
    }

    @Test
    public void implementationWithReturn() {
        Assert.assertEquals(
                "@Override public String implementation(){return origin.implementation();}",
                new OriginMethod(
                        new OverrideMethod(
                                new PublicMethod(
                                        new FromStringMethod("String implementation();"))))
                        .implementation());
    }
}