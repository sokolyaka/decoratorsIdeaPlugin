package com.sokolov.decoratorsIdeaPlugin.action;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.IncorrectOperationException;
import com.sokolov.lang.java.decorator.IDecorator;
import com.sokolov.lang.java.decorator.InMainThreadDecorator;
import com.sokolov.lang.java.decorator.OriginDecorator;
import com.sokolov.lang.java.decorator.ToStringDecorator;
import com.sokolov.lang.java.interfaceDef.IInterface;
import com.sokolov.lang.java.interfaceDef.InterfaceFromString;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class InMainThreadDecoratorAction extends BaseIntentionAction {

    @Nls
    @NotNull
    @Override
    public String getText() {
        return "Decorator - InMainThread";
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
        String name = "InMainThread" + interfaceStr.name().substring(1);
        IDecorator asyncDecorator =
                new InMainThreadDecorator(
                        new OriginDecorator(
                                name,
                                interfaceStr));

        file
                .getContainingDirectory()
                .add(
                        PsiFileFactory
                                .getInstance(project)
                                .createFileFromText(
                                        name + ".java",
                                        JavaFileType.INSTANCE,
                                        new ToStringDecorator(asyncDecorator).asString()));
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }
}