package com.sokolov.decoratorsIdeaPlugin.action;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.IncorrectOperationException;
import com.sokolov.lang.java.decorator.OriginDecorator;
import com.sokolov.lang.java.decorator.ToStringDecorator;
import com.sokolov.lang.java.interfaceDef.IInterface;
import com.sokolov.lang.java.interfaceDef.InterfaceFromString;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class OriginDecoratorAction extends BaseIntentionAction {

    @Nls
    @NotNull
    @Override
    public String getText() {
        return "Decorator - Origin";
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "Generate decorator";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return
                file.getFileType().equals(JavaFileType.INSTANCE);
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        //Access document, caret, and selection
        final Document document = editor.getDocument();

        IInterface interfaceStr = new InterfaceFromString(document.getText());
        String name = "Origin" + interfaceStr.name().substring(1);

        file
                .getContainingDirectory()
                .add(
                        PsiFileFactory
                                .getInstance(project)
                                .createFileFromText(
                                        name + ".java",
                                        JavaFileType.INSTANCE,
                                        new ToStringDecorator(
                                                new OriginDecorator(name, interfaceStr))
                                                .asString()));
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }
}
