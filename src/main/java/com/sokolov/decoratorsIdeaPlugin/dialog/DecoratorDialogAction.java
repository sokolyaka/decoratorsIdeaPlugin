package com.sokolov.decoratorsIdeaPlugin.dialog;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;
import com.intellij.util.IncorrectOperationException;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.addDecoratorToProject.InWriteActionAddDecoratorToProjectUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.addDecoratorToProject.AddDecoratorToPsiDirectoryUseCase;
import com.sokolov.lang.java.decorator.*;
import com.sokolov.lang.java.interfaceDef.InterfaceFromString;
import com.sokolov.lang.java.method.android.inMainThread.Java8InMainThreadMethodBuilder;
import com.sokolov.lang.java.method.java.async.Java8AsyncMethodBuilder;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DecoratorDialogAction extends BaseIntentionAction {

    @Nls
    @NotNull
    @Override
    public String getText() {
        return "Generate decorator";
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
        PsiJavaFile javaFile = (PsiJavaFile) file;

        JPanel classNamePanel = new JPanel();
        classNamePanel.add(new JLabel("Class name:"));
        JTextField classNameField = new JTextField(25);
        classNameField.setText(javaFile.getName().replace(".java", ""));
        classNamePanel.add(classNameField);

        JPanel packagePanel = new JPanel();
        packagePanel.add(new JLabel("Destination package:"));
        JTextField packageField = new JTextField(25);
        packageField.setText(javaFile.getPackageName());
        packagePanel.add(packageField);


        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        checkBoxPanel.add(new JLabel("Select decorator type:"));
        JCheckBox cbOrigin = new JCheckBox("Origin");
        checkBoxPanel.add(cbOrigin);
        JCheckBox cbAsync = new JCheckBox("Async");
        checkBoxPanel.add(cbAsync);
        JCheckBox cbInMainThread = new JCheckBox("InMainThread");
        checkBoxPanel.add(cbInMainThread);
        JCheckBox cbSync = new JCheckBox("Sync");
        checkBoxPanel.add(cbSync);
        JCheckBox cbAndroidLoggable = new JCheckBox("AndroidLoggable");
        checkBoxPanel.add(cbAndroidLoggable);
        JCheckBox cbSafe = new JCheckBox("Safe");
        checkBoxPanel.add(cbSafe);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(classNamePanel);
        myPanel.add(packagePanel);
        myPanel.add(checkBoxPanel);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Generate decorator", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            IDecorator decorator =
                    new OriginDecorator(
                            packageField.getText(),
                            classNameField.getText(),
                            new InterfaceFromString(
                                    editor.getDocument().getText()));
            if (cbAsync.isSelected()) {
                decorator =
                        new AsyncDecorator(
                                decorator,
                                new Java8AsyncMethodBuilder());
            } else if (cbInMainThread.isSelected()) {
                decorator =
                        new InMainThreadDecorator(
                                decorator,
                                new Java8InMainThreadMethodBuilder());
            } else if (cbSync.isSelected()) {
                decorator =
                        new SyncDecorator(
                                decorator);
            } else if (cbAndroidLoggable.isSelected()) {
                decorator =
                        new AndroidLoggableDecorator(
                                decorator);
            } else if (cbSafe.isSelected()) {
                decorator =
                        new SafeDecorator(decorator);
            }
            Module moduleForFile = ModuleUtil.findModuleForFile(file);
            if (moduleForFile == null) {
                moduleForFile = ModuleManager.getInstance(project).getModules()[0];
            }
            new InWriteActionAddDecoratorToProjectUseCase(
                    new AddDecoratorToPsiDirectoryUseCase(
                            ModuleManager.getInstance(project),
                            PsiFileFactory.getInstance(project)),
                    ApplicationManager.getApplication())
                    .execute(decorator, moduleForFile.getName());
        }
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }
}
