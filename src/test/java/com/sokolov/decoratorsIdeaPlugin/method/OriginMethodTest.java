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
}