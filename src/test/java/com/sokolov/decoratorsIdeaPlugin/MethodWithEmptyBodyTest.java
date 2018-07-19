package com.sokolov.decoratorsIdeaPlugin;

import org.junit.Assert;
import org.junit.Test;

public class MethodWithEmptyBodyTest {

    @Test
    public void implementation() {
        Assert.assertEquals(
                "@Override public void implementation(){}",
                new MethodWithEmptyBody("void implementation();").implementation());
    }
}