package com.sokolov.lang.java.method;

public class AsyncMethod implements IMethod {
    private final IMethod origin;

    public AsyncMethod(IMethod origin) {
        this.origin = origin;
    }

    @Override
    public String implementation() {
        StringBuilder sb = new StringBuilder(origin.implementation());
        int startIndex = sb.indexOf("{") + 1;
        sb.insert(startIndex, "executorService.execute(() -> {");

        int endIndex = sb.lastIndexOf("}");
        sb.insert(endIndex, "});");

        return sb.toString();
    }
}
