package com.sokolov.decoratorsIdeaPlugin.dialog.presenter;

import com.sokolov.decoratorsIdeaPlugin.dialog.domain.addDecoratorToProject.IAddDecoratorToProjectUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.createDecorator.ICreateDecoratorUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyClassName.IVerifyClassNameUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyPackage.IVerifyPackageDefUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.view.IDecoratorWizardView;
import com.sokolov.lang.java.decorator.IDecorator;
import com.sokolov.lang.java.interfaceDef.IInterface;

import static com.sokolov.decoratorsIdeaPlugin.dialog.domain.DecoratorTypes.ORIGIN;

public class DecoratorWizardPresenter implements IDecoratorWizardPresenter {
    private final IDecoratorWizardView wizardView;
    private final IVerifyClassNameUseCase verifyClassNameUseCase;
    private final IVerifyPackageDefUseCase verifyPackageDefUseCase;
    private final ICreateDecoratorUseCase createDecoratorUseCase;
    private final IAddDecoratorToProjectUseCase addDecoratorToProjectUseCase;
    private final IInterface iInterface;
    private final String[] moduleNames;

    private String className;
    private String packageDef;
    private int decoratorType;
    private String moduleName;

    private boolean isClassNameValid;
    private boolean isPackageDefValid;

    public DecoratorWizardPresenter(
            IDecoratorWizardView wizardView,
            IVerifyClassNameUseCase verifyClassNameUseCase,
            IVerifyPackageDefUseCase verifyPackageDefUseCase,
            ICreateDecoratorUseCase createDecoratorUseCase,
            IAddDecoratorToProjectUseCase addDecoratorToProjectUseCase,
            IInterface iInterface,
            String[] moduleNames) {

        this.wizardView = wizardView;
        this.verifyClassNameUseCase = verifyClassNameUseCase;
        this.verifyPackageDefUseCase = verifyPackageDefUseCase;
        this.createDecoratorUseCase = createDecoratorUseCase;
        this.addDecoratorToProjectUseCase = addDecoratorToProjectUseCase;
        this.iInterface = iInterface;
        this.moduleNames = moduleNames;
    }

    @Override
    public void onCreated() {
        wizardView.show();

        className = "Decorator" + iInterface.name();
        wizardView.updateClassName(className);
        isClassNameValid = true;

        packageDef = iInterface.packageDef();
        wizardView.updatePackageDef(packageDef);
        isPackageDefValid = true;

        decoratorType = ORIGIN;

        moduleName = moduleNames[0];
        wizardView.setUpModuleNames(moduleNames, moduleName);
    }

    @Override
    public void onClassNameChanged(String className) {
        isClassNameValid = verifyClassNameUseCase.execute(className);

        if (isClassNameValid) {
            this.className = className;
        } else {
            wizardView.showInvalidClassNameError();
        }

        checkAndSetOkBtnEnable();
    }

    @Override
    public void onPackageNameChanged(String packageDef) {
        isPackageDefValid = verifyPackageDefUseCase.execute(packageDef);

        if (isPackageDefValid) {
            this.packageDef = packageDef;
        } else {
            wizardView.showInvalidPackageDefError();
        }

        checkAndSetOkBtnEnable();
    }

    private void checkAndSetOkBtnEnable() {
        wizardView.setOkBtnEnable(
                isClassNameValid
                        && isPackageDefValid);
    }

    @Override
    public void onDecoratorTypeSelected(int type) {
        this.decoratorType = type;
    }

    @Override
    public void onModuleSelected(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public void onConfirm() {
        IDecorator decorator =
                createDecoratorUseCase
                        .execute(
                                packageDef,
                                className,
                                decoratorType,
                                iInterface);

        addDecoratorToProjectUseCase.execute(decorator, moduleName);
    }
}
