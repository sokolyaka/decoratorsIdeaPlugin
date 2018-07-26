package com.sokolov.decoratorsIdeaPlugin.dialog.presenter;

import com.sokolov.decoratorsIdeaPlugin.dialog.domain.ICreateDecoratorUseCase;
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
        className = "Decorator" + iInterface.name();
        wizardView.updateClassName(className);
        packageDef = iInterface.packageDef();
        wizardView.updatePackageDef(packageDef);
        decoratorType = ORIGIN;
    }

    @Override
    public void onClassNameChanged(String className) {
        if (verifyClassNameUseCase.execute(className)) {
            this.className = className;
        } else {
            wizardView.showInvalidClassNameError();
        }
    }

    @Override
    public void onPackageNameChanged(String packageDef) {
        if (verifyPackageDefUseCase.execute(packageDef)) {
            this.packageDef = packageDef;
        } else {
            wizardView.showInvalidPackageDefError();
        }
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
