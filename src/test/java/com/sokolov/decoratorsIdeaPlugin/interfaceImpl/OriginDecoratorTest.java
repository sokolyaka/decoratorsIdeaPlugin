package com.sokolov.decoratorsIdeaPlugin.interfaceImpl;

import com.sokolov.decoratorsIdeaPlugin.javaInterface.InterfaceFromString;
import org.junit.Assert;
import org.junit.Test;

public class OriginDecoratorTest {

    @Test
    public void asString() {
        Assert.assertEquals(
                "package com.sokolov.decoratorsIdeaPlugin.method.parameter;\n" +
                        "\n" +
                        "import java.util.List;\n" +
                        "\n" +
                        "public class NewName implements IListOfParameters {\n\n" +
                        "private final IListOfParameters origin;\n" +
                        "\n" +
                        "public NewName(IListOfParameters origin){\n" +
                        "this.origin = origin;\n" +
                        "}\n" +
                        "\n" +
                        "@Override public List<IParameter> asList(){return origin.asList();}\n" +
                        "}\n",
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