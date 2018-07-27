package com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyClassName;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class VerifyClassNameUseCaseTest {

    @Test
    public void testExecute() {
        Assert.assertTrue(
                new VerifyClassNameUseCase().execute("RegExpVerifyFieldUseCase"));
    }

    @Ignore
    @Test
    public void testExecute1() {
        Assert.assertTrue(
                new VerifyClassNameUseCase().execute("$RegExpVerifyFieldUseCase"));
    }

    @Ignore
    @Test
    public void testExecute2() {
        Assert.assertTrue(
                new VerifyClassNameUseCase().execute("RegExpVerify$FieldUseCase"));
    }

    @Test
    public void testExecute3() {
        Assert.assertFalse(
                new VerifyClassNameUseCase().execute("RegExpVerify^FieldUseCase"));
    }
}