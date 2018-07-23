package com.sokolov.lang.java.importDef;

public class ImportFromString implements IImport {
    private final String fullClassPath;

    public ImportFromString(String fullClassPath) {
        this.fullClassPath = fullClassPath;
    }

    @Override
    public String asString() {
        return fullClassPath;
    }

    @Override
    public String asFullDef() {
        return "import " + fullClassPath + ";";
    }
}
