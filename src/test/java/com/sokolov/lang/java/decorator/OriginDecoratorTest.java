package com.sokolov.lang.java.decorator;

import com.sokolov.lang.java.interfaceDef.InterfaceFromString;

import org.junit.Assert;
import org.junit.Test;

public class OriginDecoratorTest {

    @Test
    public void asString() {
        Assert.assertEquals(
                "package com.sokolov.lang.java.method.parameter;" +
                        "import java.util.List;" +
                        "public class NewName implements IListOfParameters{" +
                        "private final IListOfParameters origin;" +
                        "public NewName(IListOfParameters origin){" +
                        "this.origin = origin;" +
                        "}" +
                        "@Override public List<IParameter> asList(){return origin.asList();}" +
                        "}",
                new ToStringDecorator(
                        new OriginDecorator(
                                "com.sokolov.lang.java.method.parameter",
                                "NewName",
                                new InterfaceFromString(
                                        "package com.sokolov.lang.java.method.parameter;\n" +
                                                "\n" +
                                                "import java.util.List;\n" +
                                                "\n" +
                                                "public interface IListOfParameters {\n" +
                                                "\n" +
                                                "    List<IParameter> asList();\n" +
                                                "}\n")))
                        .asString());

    }

    @Test
    public void interfaceHasConstantAndJavaDoc() {
        Assert.assertEquals(
                "package bitcore.wallet.client.service.rate.interfaces;" +
                        "public class NewName implements IBlackcarrotRateApi{" +
                        "private final IBlackcarrotRateApi origin;" +
                        "public NewName(IBlackcarrotRateApi origin){" +
                        "this.origin = origin;}" +
                        "@Override public IRateResponse getUSDRate(){" +
                        "return origin.getUSDRate();}}",
                new ToStringDecorator(
                        new OriginDecorator(
                                "com.sokolov.lang.java.method.parameter",
                                "NewName",
                                new InterfaceFromString(
                                        "package bitcore.wallet.client.service.rate.interfaces;\n" +
                                                "\n" +
                                                "\n" +
                                                "public interface IBlackcarrotRateApi {\n" +
                                                "    String URL = \"https://rates.blackcarrot.be/rate/\";\n" +
                                                "\n" +
                                                "    /**\n" +
                                                "     * @GET(\"DASH_USD.json\")\n" +
                                                "     */\n" +
                                                "    IRateResponse getUSDRate();\n" +
                                                "}")))
                        .asString());

    }
}