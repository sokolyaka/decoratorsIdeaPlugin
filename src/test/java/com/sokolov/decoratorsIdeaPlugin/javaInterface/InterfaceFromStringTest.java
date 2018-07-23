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
    public void imports2() {
        String[] actual =
                new InterfaceFromString(
                        "package com.sokolov.decoratorsIdeaPlugin.method.parameter;\n" +
                                "\n" +
                                "import java.util.List;\n" +
                                "import org.junit.Assert;\n" +
                                "\n" +
                                "public interface IListOfParameters {\n" +
                                "\n" +
                                "    List<IParameter> asList();\n" +
                                "}\n")
                        .imports()
                        .toArray(new String[0]);
        Assert.assertArrayEquals(
                new String[]{"import java.util.List;", "import org.junit.Assert;"},
                actual);
    }

    @Test
    public void importsWithImportWordInMethodDef() {
        String[] actual =
                new InterfaceFromString(
                        "package com.sokolov.decoratorsIdeaPlugin.method.parameter;\n" +
                                "\n" +
                                "import java.util.List;\n" +
                                "import org.junit.Assert;\n" +
                                "\n" +
                                "public interface IListOfParameters {\n" +
                                "\n" +
                                "    List<IParameter> importAsList();\n" +
                                "}\n")
                        .imports()
                        .toArray(new String[0]);
        Assert.assertArrayEquals(
                new String[]{"import java.util.List;", "import org.junit.Assert;"},
                actual);
    }

    @Test
    public void name() {
        String actual =
                new InterfaceFromString(
                        "package com.sokolov.decoratorsIdeaPlugin.method.parameter;\n" +
                                "\n" +
                                "import java.util.List;\n" +
                                "import org.junit.Assert;\n" +
                                "\n" +
                                "public interface IListOfParameters {\n" +
                                "\n" +
                                "    List<IParameter> asList();\n" +
                                "}\n")
                        .name();
        Assert.assertEquals(
                "IListOfParameters",
                actual);

    }

    @Test
    public void methods() {
        String[] actual =
                new InterfaceFromString(
                        "package com.sokolov.decoratorsIdeaPlugin.method.parameter;\n" +
                                "\n" +
                                "import java.util.List;\n" +
                                "import org.junit.Assert;\n" +
                                "\n" +
                                "public interface IListOfParameters {\n" +
                                "\n" +
                                "    List<IParameter> asList();\n" +
                                "}\n")
                        .methods()
                        .toArray(new String[0]);
        Assert.assertArrayEquals(
                new String[]{"List<IParameter> asList();"},
                actual);

    }

    @Test
    public void packageDef() {
        String actual =
                new InterfaceFromString(
                        "package com.sokolov.decoratorsIdeaPlugin.method.parameter;\n" +
                                "\n" +
                                "import java.util.List;\n" +
                                "import org.junit.Assert;\n" +
                                "\n" +
                                "public interface IListOfParameters {\n" +
                                "\n" +
                                "    List<IParameter> asList();\n" +
                                "}\n")
                        .packageDef();
        Assert.assertEquals(
                "com.sokolov.decoratorsIdeaPlugin.method.parameter",
                actual);
    }
}