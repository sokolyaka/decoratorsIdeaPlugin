package com.sokolov.decoratorsIdeaPlugin.interfaceImpl;

import com.sokolov.decoratorsIdeaPlugin.javaInterface.InterfaceFromString;
import org.junit.Assert;
import org.junit.Test;

public class AsyncDecoratorTest {

    @Test
    public void asString() {
        Assert.assertEquals(
                "package com.sokolov.decoratorsIdeaPlugin.method.parameter;import java.util.concurrent.ExecutorService;public class NewName implements IListOfParameters{private final IListOfParameters origin;private final ExecutorService executorService;public NewName(IListOfParameters origin,ExecutorService executorService){this.origin = origin;this.executorService = executorService;}@Override public void asList(){executorService.execute(() -> origin.asList());}}",
                new AsyncDecorator(
                        new OriginDecorator(
                                "com.sokolov.decoratorsIdeaPlugin.method.parameter",
                                "NewName",
                                new InterfaceFromString(
                                        "package com.sokolov.decoratorsIdeaPlugin.method.parameter;\n" +
                                                "\n" +
                                                "public interface IListOfParameters {\n" +
                                                "\n" +
                                                "    void asList();\n" +
                                                "}\n")))
                        .asString());

    }
}