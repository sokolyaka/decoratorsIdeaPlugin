package com.sokolov.decoratorsIdeaPlugin.dialog.view;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.components.panels.HorizontalLayout;
import com.intellij.ui.components.panels.VerticalLayout;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.DecoratorTypes;
import com.sokolov.decoratorsIdeaPlugin.dialog.presenter.IDecoratorWizardPresenter;

import org.jetbrains.annotations.Nullable;

import java.awt.event.ItemEvent;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JComponent;
import javax.swing.JPanel;

import static javax.swing.SwingConstants.LEFT;

public class WizardDialog extends DialogWrapper implements IDecoratorWizardView {

    private final Queue<ValidationInfo> validateQ;

    private IDecoratorWizardPresenter wizardPresenter;

    private JBTextField packageField;
    private JBTextField classNameField;
    private JPanel mainPanel;

    public WizardDialog(@Nullable Project project) {
        super(project);
        validateQ = new ArrayBlockingQueue<>(1);

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
        JPanel classNamePanel = new JPanel(new HorizontalLayout(55));
        JBLabel comp = new JBLabel("Class name:");
        classNamePanel.add(comp);
        classNameField = new JBTextField(25);
        classNamePanel.add(classNameField);

        JPanel packagePanel = new JPanel(new HorizontalLayout(8));
        packagePanel.add(new JBLabel("Destination package:"));
        packageField = new JBTextField(25);
        packagePanel.add(packageField);

        ComboBox<String> comboBox = new ComboBox<>(new String[]{"Origin", "Async", "InMainThread", "Sync", "AndroidLoggable", "Safe"});
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String item = (String) e.getItem();
                switch (item) {
                    case "Origin": {
                        wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.ORIGIN);
                        break;
                    }
                    case "Async": {
                        wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.ASYNC);
                        break;
                    }
                    case "InMainThread": {
                        wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.IN_MAIN_THREAD);
                        break;
                    }
                    case "Sync": {
                        wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.SYNC);
                        break;
                    }
                    case "AndroidLoggable": {
                        wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.ANDROID_LOG);
                        break;
                    }
                    case "Safe": {
                        wizardPresenter.onDecoratorTypeSelected(DecoratorTypes.SAFE);
                        break;
                    }
                }
            }
        });

        JPanel rbPanel = new JPanel(new HorizontalLayout(4));
        rbPanel.add(new JBLabel("Select decorator type:"));
        rbPanel.add(comboBox);

        mainPanel = new JPanel();
        mainPanel.setLayout(new VerticalLayout(5, LEFT));
        mainPanel.add(classNamePanel);
        mainPanel.add(packagePanel);
        mainPanel.add(rbPanel);

        super.init();
    }

    @Override
    public void showInvalidClassNameError() {
        validateQ.offer(new ValidationInfo("Invalid class name", classNameField));
    }

    @Override
    public void showInvalidPackageDefError() {
        validateQ.offer(new ValidationInfo("Invalid package", packageField));
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
        // TODO: 31.07.2018  ModulesComboBox.java
    }

    @Override
    public void show() {
        super.show();
        int exitCode = getExitCode();
        if (exitCode == OK_EXIT_CODE) {
            wizardPresenter.onConfirm();
        }
    }
}
