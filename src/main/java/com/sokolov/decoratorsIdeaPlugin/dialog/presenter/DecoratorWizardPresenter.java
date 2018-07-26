package com.sokolov.decoratorsIdeaPlugin.dialog.presenter;

import com.sokolov.decoratorsIdeaPlugin.dialog.domain.createDecorator.ICreateDecoratorUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyClassName.IVerifyClassNameUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyPackage.IVerifyPackageDefUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.view.IDecoratorWizardView;
import com.sokolov.lang.java.interfaceDef.IInterface;

import static com.sokolov.decoratorsIdeaPlugin.dialog.domain.DecoratorTypes.ORIGIN;

public class DecoratorWizardPresenter implements IDecoratorWizardPresenter {
    private final IDecoratorWizardView wizardView;
    private final IVerifyClassNameUseCase verifyClassNameUseCase;
    private final IVerifyPackageDefUseCase verifyPackageDefUseCase;
    private final ICreateDecoratorUseCase createDecoratorUseCase;
    private final IInterface iInterface;

    private String className;
    private String packageDef;
    private int decoratorType;

    private boolean isClassNameValid;
    private boolean isPackageDefValid;

    public DecoratorWizardPresenter(
            IDecoratorWizardView wizardView,
            IVerifyClassNameUseCase verifyClassNameUseCase,
            IVerifyPackageDefUseCase verifyPackageDefUseCase,
            ICreateDecoratorUseCase createDecoratorUseCase,
            IInterface iInterface) {

        this.wizardView = wizardView;
        this.verifyClassNameUseCase = verifyClassNameUseCase;
        this.verifyPackageDefUseCase = verifyPackageDefUseCase;
        this.createDecoratorUseCase = createDecoratorUseCase;
        this.iInterface = iInterface;
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
    public void onConfirm() {
        createDecoratorUseCase
                .execute(
                        packageDef,
                        className,
                        decoratorType,
                        iInterface);
    }
}
