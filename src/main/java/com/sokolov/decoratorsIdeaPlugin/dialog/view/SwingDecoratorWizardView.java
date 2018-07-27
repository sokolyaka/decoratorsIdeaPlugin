package com.sokolov.decoratorsIdeaPlugin.dialog.view;

import com.sokolov.decoratorsIdeaPlugin.dialog.domain.DecoratorTypes;
import com.sokolov.decoratorsIdeaPlugin.dialog.presenter.IDecoratorWizardPresenter;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SwingDecoratorWizardView implements IDecoratorWizardView {
    private final IDecoratorWizardPresenter wizardPresenter;

    private JTextField packageField;
    private JTextField classNameField;
    private JRadioButton rbOrigin;
    private JRadioButton rbAsync;
    private JRadioButton rbInMainThread;
    private JRadioButton rbSync;
    private JRadioButton rbAndroidLoggable;
    private JRadioButton rbSafe;

    public SwingDecoratorWizardView(IDecoratorWizardPresenter wizardPresenter) {
        this.wizardPresenter = wizardPresenter;
    }

    @Override
    public void show() {
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
        rbOrigin = new JRadioButton("Origin", true);
        rbOrigin.addActionListener(e -> wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.ORIGIN));
        group.add(rbOrigin);
        rbPanel.add(rbOrigin);

        rbAsync = new JRadioButton("Async", false);
        rbAsync.addActionListener(e -> wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.ASYNC));
        group.add(rbAsync);
        rbPanel.add(rbAsync);

        rbInMainThread = new JRadioButton("InMainThread", false);
        rbInMainThread.addActionListener(e -> wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.IN_MAIN_THREAD));
        group.add(rbInMainThread);
        rbPanel.add(rbInMainThread);

        rbSync = new JRadioButton("Sync", false);
        rbSync.addActionListener(e -> wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.SYNC));
        group.add(rbSync);
        rbPanel.add(rbSync);

        rbAndroidLoggable = new JRadioButton("AndroidLoggable", false);
        rbAndroidLoggable.addActionListener(e -> wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.ANDROID_LOG));
        group.add(rbAndroidLoggable);
        rbPanel.add(rbAndroidLoggable);

        rbSafe = new JRadioButton("Safe", false);
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

    }

    @Override
    public void showInvalidPackageDefError() {

    }

    @Override
    public void updateClassName(String className) {

    }

    @Override
    public void updatePackageDef(String packageDef) {

    }

    @Override
    public void setOkBtnEnable(boolean isEnable) {

    }

    @Override
    public void setUpModuleNames(String[] moduleNames, String preSelectedModule) {

    }
}
