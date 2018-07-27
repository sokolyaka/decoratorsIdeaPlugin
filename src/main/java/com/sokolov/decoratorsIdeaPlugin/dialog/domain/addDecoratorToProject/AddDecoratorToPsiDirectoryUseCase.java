package com.sokolov.decoratorsIdeaPlugin.dialog.domain.addDecoratorToProject;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.ide.util.PackageUtil;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFileFactory;
import com.sokolov.lang.java.decorator.IDecorator;
import com.sokolov.lang.java.decorator.ToStringDecorator;

public class AddDecoratorToPsiDirectoryUseCase implements IAddDecoratorToProjectUseCase {
    private final Module moduleForFile;
    private final PsiFileFactory psiFileFactory;

    public AddDecoratorToPsiDirectoryUseCase(Module moduleForFile, PsiFileFactory psiFileFactory) {
        this.moduleForFile = moduleForFile;
        this.psiFileFactory = psiFileFactory;
    }

    @Override
    public void execute(IDecorator decorator) {

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
