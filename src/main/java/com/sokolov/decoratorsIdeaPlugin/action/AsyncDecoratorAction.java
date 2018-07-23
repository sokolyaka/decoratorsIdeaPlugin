package com.sokolov.decoratorsIdeaPlugin.action;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.IncorrectOperationException;
import com.sokolov.decoratorsIdeaPlugin.interfaceImpl.AsyncDecorator;
import com.sokolov.decoratorsIdeaPlugin.interfaceImpl.IInterfaceImpl;
import com.sokolov.decoratorsIdeaPlugin.interfaceImpl.OriginDecorator;
import com.sokolov.decoratorsIdeaPlugin.javaInterface.IInterface;
import com.sokolov.decoratorsIdeaPlugin.javaInterface.InterfaceFromString;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class AsyncDecoratorAction extends BaseIntentionAction {

    @Nls
    @NotNull
    @Override
    public String getText() {
        return "Async";
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "Implement interface";
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
        String name = "Async" + interfaceStr.name().substring(1);
        IInterfaceImpl asyncDecorator =
                new AsyncDecorator(
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
                                        asyncDecorator.asString()));
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }
}