package com.sokolov.decoratorsIdeaPlugin.dialog.view;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.DecoratorTypes;
import com.sokolov.decoratorsIdeaPlugin.dialog.presenter.IDecoratorWizardPresenter;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WizardDialog extends DialogWrapper implements IDecoratorWizardView {

    private final Queue<ValidationInfo> validateQ;

    private IDecoratorWizardPresenter wizardPresenter;

    private JTextField packageField;
    private JTextField classNameField;
    private JPanel mainPanel;

    public WizardDialog(@Nullable Project project) {
        super(project);
        validateQ = new ConcurrentLinkedQueue<>();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return mainPanel;
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        wizardPresenter.onClassNameChanged(classNameField.getText());
        wizardPresenter.onPackageNameChanged(packageField.getText());

        return validateQ.poll();
    }

    @Override
    public void init() {
        setTitle("Decorator Wizard");
        JPanel classNamePanel = new JPanel();
        classNamePanel.add(new JLabel("Class name:"));
        classNameField = new JTextField(25);
        classNamePanel.add(classNameField);

        JPanel packagePanel = new JPanel();
        packagePanel.add(new JLabel("Destination package:"));
        packageField = new JTextField(25);
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

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(classNamePanel);
        mainPanel.add(packagePanel);
        mainPanel.add(rbPanel);

        super.init();
    }

    @Override
    public void showInvalidClassNameError() {
        validateQ.add(new ValidationInfo("Invalid class name", classNameField));
    }

    @Override
    public void showInvalidPackageDefError() {
        validateQ.add(new ValidationInfo("Invalid package", packageField));
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
    public void setWizardPresenter(IDecoratorWizardPresenter wizardPresenter) {
        this.wizardPresenter = wizardPresenter;
    }

    @Override
    public void setUpModuleNames(String[] moduleNames, String preSelectedModule) {

    }
}
