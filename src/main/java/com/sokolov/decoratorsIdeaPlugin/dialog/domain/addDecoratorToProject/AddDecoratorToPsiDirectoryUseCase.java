package com.sokolov.decoratorsIdeaPlugin.dialog.domain.addDecoratorToProject;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.ide.util.PackageUtil;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFileFactory;
import com.sokolov.lang.java.decorator.IDecorator;
import com.sokolov.lang.java.decorator.ToStringDecorator;

public class AddDecoratorToPsiDirectoryUseCase implements IAddDecoratorToProjectUseCase {
    private final ModuleManager moduleManager;
    private final PsiFileFactory psiFileFactory;

    public AddDecoratorToPsiDirectoryUseCase(ModuleManager moduleManager, PsiFileFactory psiFileFactory) {
        this.moduleManager = moduleManager;
        this.psiFileFactory = psiFileFactory;
    }

    @Override
    public void execute(IDecorator decorator, String moduleName) {
        Module moduleForFile = null;
        for (Module module : moduleManager.getModules()) {
            if (module.getName().equals(moduleName)) {
                moduleForFile = module;
            }
        }
        if (moduleForFile == null) {
            throw new IllegalArgumentException("moduleName = " + moduleName);
        }

        PsiDirectory directory =
                PackageUtil.findOrCreateDirectoryForPackage(
                        moduleForFile,
                        decorator.packageDef(),
                        null,
                        false);
        directory.add(
                psiFileFactory
                        .createFileFromText(
                                decorator.className() + ".java",
                                JavaFileType.INSTANCE,
                                new ToStringDecorator(
                                        decorator)
                                        .asString()));
    }
}
