package com.sokolov.decoratorsIdeaPlugin.dialog.view;

import com.intellij.ui.JBColor;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.DecoratorTypes;
import com.sokolov.decoratorsIdeaPlugin.dialog.presenter.IDecoratorWizardPresenter;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SwingDecoratorWizardView implements IDecoratorWizardView {
    private IDecoratorWizardPresenter wizardPresenter;

    private JTextField packageField;
    private JTextField classNameField;
    private JButton okayBtn;
    private JPanel mainPanel;
    private JButton cancel;

    @Override
    public void setWizardPresenter(IDecoratorWizardPresenter wizardPresenter) {
        this.wizardPresenter = wizardPresenter;
    }

    @Override
    public void init() {
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
                                wizardPresenter.onPackageNameChanged(packageField.getText());
                            }

                            public void removeUpdate(DocumentEvent e) {
                                wizardPresenter.onPackageNameChanged(packageField.getText());
                            }

                            public void insertUpdate(DocumentEvent e) {
                                wizardPresenter.onPackageNameChanged(packageField.getText());
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

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(classNamePanel);
        mainPanel.add(packagePanel);
        mainPanel.add(rbPanel);

        okayBtn = new JButton("Ok");
        okayBtn.addActionListener(e -> {
            JOptionPane pane = getOptionPane((JComponent) e.getSource());
            pane.setValue(okayBtn);
        });

        okayBtn.setEnabled(true);
        cancel = new JButton("Cancel");
        cancel.addActionListener(e -> {
            JOptionPane pane = getOptionPane((JComponent) e.getSource());
            pane.setValue(cancel);
        });
    }

    @Override
    public void show() {


        int result =
                JOptionPane.showOptionDialog(
                        null,
                        mainPanel,
                        "Generate decorator",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new Object[]{okayBtn, cancel},
                        okayBtn);

        if (result == JOptionPane.OK_OPTION) {
            wizardPresenter.onConfirm();
        }
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
        okayBtn.setEnabled(isEnable);
    }

    @Override
    public void setUpModuleNames(String[] moduleNames, String preSelectedModule) {

    }

    private static JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane = null;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent) parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }
}
