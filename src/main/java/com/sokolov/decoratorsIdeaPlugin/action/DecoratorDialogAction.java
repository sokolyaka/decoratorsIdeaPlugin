package com.sokolov.decoratorsIdeaPlugin.action;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.IncorrectOperationException;
import com.sokolov.lang.java.decorator.AsyncDecorator;
import com.sokolov.lang.java.decorator.IDecorator;
import com.sokolov.lang.java.decorator.InMainThreadDecorator;
import com.sokolov.lang.java.decorator.OriginDecorator;
import com.sokolov.lang.java.decorator.ToStringDecorator;
import com.sokolov.lang.java.interfaceDef.IInterface;
import com.sokolov.lang.java.interfaceDef.InterfaceFromString;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        final Document document = editor.getDocument();

        IInterface interfaceStr = new InterfaceFromString(document.getText());


        JPanel classNamePanel = new JPanel();
        classNamePanel.add(new JLabel("Create class:"));
        JTextField classNameField = new JTextField(25);
        classNameField.setText(interfaceStr.name());
        classNamePanel.add(classNameField);

        JPanel packagePanel = new JPanel();
        packagePanel.add(new JLabel("Destination package:"));
        JTextField packageField = new JTextField(25);
        packageField.setText(interfaceStr.packageDef());
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

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(classNamePanel);
        myPanel.add(packagePanel);
        myPanel.add(checkBoxPanel);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Generate decorator", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            IDecorator decorator = null;
            if (cbAsync.isSelected()) {
                decorator =
                        new AsyncDecorator(
                                new OriginDecorator(
                                        packageField.getText(),
                                        classNameField.getText(),
                                        interfaceStr));
            } else if (cbInMainThread.isSelected()) {
                decorator =
                        new InMainThreadDecorator(
                                new OriginDecorator(
                                        packageField.getText(),
                                        classNameField.getText(),
                                        interfaceStr));
            } else if (cbOrigin.isSelected()) {
                decorator =
                        new OriginDecorator(
                                packageField.getText(),
                                classNameField.getText(),
                                interfaceStr);
            }
            if (decorator != null) {
                IDecorator finalDecorator = decorator;
                ApplicationManager
                        .getApplication()
                        .runWriteAction(() -> {
                            file
                                    .getContainingDirectory()
                                    .add(
                                            PsiFileFactory
                                                    .getInstance(project)
                                                    .createFileFromText(
                                                            classNameField.getText() + ".java",
                                                            JavaFileType.INSTANCE,
                                                            new ToStringDecorator(
                                                                    finalDecorator)
                                                                    .asString()));
                        });
            }
        }
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }
}
