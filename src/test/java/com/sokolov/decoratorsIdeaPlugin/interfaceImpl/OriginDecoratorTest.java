package com.sokolov.decoratorsIdeaPlugin.interfaceImpl;

import com.sokolov.decoratorsIdeaPlugin.javaInterface.InterfaceFromString;
import org.junit.Assert;
import org.junit.Test;

public class OriginDecoratorTest {

    @Test
    public void asString() {
        Assert.assertEquals(
                "package com.sokolov.decoratorsIdeaPlugin.method.parameter;" +
                        "import java.util.List;" +
                        "public class NewName implements IListOfParameters{" +
                        "private final IListOfParameters origin;" +
                        "public NewName(IListOfParameters origin){" +
                        "this.origin = origin;" +
                        "}" +
                        "@Override public List<IParameter> asList(){return origin.asList();}" +
                        "}",
                new OriginDecorator(
                        "com.sokolov.decoratorsIdeaPlugin.method.parameter",
                        "NewName",
                        new InterfaceFromString(
                                "package com.sokolov.decoratorsIdeaPlugin.method.parameter;\n" +
                                        "\n" +
                                        "import java.util.List;\n" +
                                        "\n" +
                                        "public interface IListOfParameters {\n" +
                                        "\n" +
                                        "    List<IParameter> asList();\n" +
                                        "}\n"))
                        .asString());

    }
}