package com.sokolov.decoratorsIdeaPlugin.dialog.view;

import com.intellij.ui.JBColor;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.DecoratorTypes;
import com.sokolov.decoratorsIdeaPlugin.dialog.presenter.IDecoratorWizardPresenter;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SwingDecoratorWizardView implements IDecoratorWizardView {
    private final IDecoratorWizardPresenter wizardPresenter;

    private JTextField packageField;
    private JTextField classNameField;

    public SwingDecoratorWizardView(IDecoratorWizardPresenter wizardPresenter) {
        this.wizardPresenter = wizardPresenter;
    }

    @Override
    public void show() {
        JPanel classNamePanel = new JPanel();
        classNamePanel.add(new JLabel("Class name:"));
        classNameField = new JTextField(25);
        classNameField
                .getDocument()
                .addDocumentListener(
                        new DocumentListener() {
                            public void changedUpdate(DocumentEvent e) {
                                wizardPresenter.onClassNameChanged(classNameField.getText());
                            }

                            public void removeUpdate(DocumentEvent e) {
                                wizardPresenter.onClassNameChanged(classNameField.getText());
                            }

                            public void insertUpdate(DocumentEvent e) {
                                wizardPresenter.onClassNameChanged(classNameField.getText());
                            }
                        });
        classNamePanel.add(classNameField);

        JPanel packagePanel = new JPanel();
        packagePanel.add(new JLabel("Destination package:"));
        packageField = new JTextField(25);
        packageField
                .getDocument()
                .addDocumentListener(
                        new DocumentListener() {
                            public void changedUpdate(DocumentEvent e) {
                                wizardPresenter.onPackageNameChanged(classNameField.getText());
                            }

                            public void removeUpdate(DocumentEvent e) {
                                wizardPresenter.onPackageNameChanged(classNameField.getText());
                            }

                            public void insertUpdate(DocumentEvent e) {
                                wizardPresenter.onPackageNameChanged(classNameField.getText());
                            }
                        });

        packagePanel.add(packageField);

        JPanel rbPanel = new JPanel();
        rbPanel.setLayout(new BoxLayout(rbPanel, BoxLayout.Y_AXIS));
        rbPanel.add(new JLabel("Select decorator type:"));

        ButtonGroup group = new ButtonGroup();
        JRadioButton rbOrigin = new JRadioButton("Origin", true);
        rbOrigin.addActionListener(e -> wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.ORIGIN));
        group.add(rbOrigin);
        rbPanel.add(rbOrigin);

        JRadioButton rbAsync = new JRadioButton("Async", false);
        rbAsync.addActionListener(e -> wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.ASYNC));
        group.add(rbAsync);
        rbPanel.add(rbAsync);

        JRadioButton rbInMainThread = new JRadioButton("InMainThread", false);
        rbInMainThread.addActionListener(e -> wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.IN_MAIN_THREAD));
        group.add(rbInMainThread);
        rbPanel.add(rbInMainThread);

        JRadioButton rbSync = new JRadioButton("Sync", false);
        rbSync.addActionListener(e -> wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.SYNC));
        group.add(rbSync);
        rbPanel.add(rbSync);

        JRadioButton rbAndroidLoggable = new JRadioButton("AndroidLoggable", false);
        rbAndroidLoggable.addActionListener(e -> wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.ANDROID_LOG));
        group.add(rbAndroidLoggable);
        rbPanel.add(rbAndroidLoggable);

        JRadioButton rbSafe = new JRadioButton("Safe", false);
        rbSafe.addActionListener(e -> wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.SAFE));
        group.add(rbSafe);
        rbPanel.add(rbSafe);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(classNamePanel);
        myPanel.add(packagePanel);
        myPanel.add(rbPanel);
    }

    @Override
    public void showInvalidClassNameError() {
        classNameField.setForeground(JBColor.RED);
    }

    @Override
    public void showInvalidPackageDefError() {
        packageField.setForeground(JBColor.RED);
    }

    @Override
    public void updateClassName(String className) {
        classNameField.setText(className);
    }

    @Override
    public void updatePackageDef(String packageDef) {
        packageField.setText(packageDef);
    }

    @Override
    public void setOkBtnEnable(boolean isEnable) {

    }

    @Override
    public void setUpModuleNames(String[] moduleNames, String preSelectedModule) {

    }
}
