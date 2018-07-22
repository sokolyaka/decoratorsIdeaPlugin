package com.sokolov.decoratorsIdeaPlugin.interfaceImpl;

public class AsyncDecorator implements IInterfaceImpl {
    private final OriginDecorator origin;

    public AsyncDecorator(OriginDecorator origin) {
        this.origin = origin;
    }

    @Override
    public String asString() {
        StringBuilder sb = new StringBuilder(origin.asString());
        sb
                .insert(
                        sb.indexOf("public"),
                        "import java.util.concurrent.ExecutorService;")
                .insert(
                        sb.indexOf("origin;") + "origin;".length(),
                        "private final ExecutorService executorService;")
                .insert(
                        sb.indexOf(")"),
                        ",ExecutorService executorService")
                .insert(
                        sb.indexOf("}"),
                        "this.executorService = executorService;");

        int fromIndex = 0;
        int curOverrideIndex = 0;
        while ((curOverrideIndex = sb.indexOf("@Override ", fromIndex)) > 0) {
            int startIndex = sb.indexOf("{", curOverrideIndex) + 1;
            sb.insert(startIndex, "executorService.execute(() -> ");

            int endIndex = sb.indexOf(";", curOverrideIndex);
            sb.insert(endIndex, ")");

            fromIndex = endIndex;
        }

        return sb.toString();
    }
}
