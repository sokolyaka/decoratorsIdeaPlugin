package com.sokolov.decoratorsIdeaPlugin.javaInterface;

import org.junit.Assert;
import org.junit.Test;

public class InterfaceFromStringTest {

    @Test
    public void imports() {
        String[] actual =
                new InterfaceFromString(
                        "package com.sokolov.decoratorsIdeaPlugin.method.parameter;\n" +
                                "\n" +
                                "import java.util.List;\n" +
                                "\n" +
                                "public interface IListOfParameters {\n" +
                                "\n" +
                                "    List<IParameter> asList();\n" +
                                "}\n")
                        .imports()
                        .toArray(new String[0]);
        Assert.assertArrayEquals(
                new String[]{"import java.util.List;"},
                actual);
    }

    @Test
    public void head() {
    }

    @Test
    public void methods() {
    }

    @Test
    public void packageDef() {
    }
}