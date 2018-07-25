package com.sokolov.decoratorsIdeaPlugin.dialog.presenter;

import com.sokolov.decoratorsIdeaPlugin.dialog.domain.ICreateDecoratorUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyClassName.IVerifyClassNameUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.IVerifyPackageDefUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.view.IDecoratorWizardView;

public class DecoratorWizardPresenter implements IDecoratorWizardPresenter {
    private final IDecoratorWizardView wizardView;
    private final IVerifyClassNameUseCase verifyClassNameUseCase;
    private final IVerifyPackageDefUseCase verifyPackageDefUseCase;
    private final ICreateDecoratorUseCase createDecoratorUseCase;

    private String className;
    private String packageDef;
    private int decoratorType;

    public DecoratorWizardPresenter(
            IDecoratorWizardView wizardView,
            IVerifyClassNameUseCase verifyClassNameUseCase,
            IVerifyPackageDefUseCase verifyPackageDefUseCase,
            ICreateDecoratorUseCase createDecoratorUseCase) {

        this.wizardView = wizardView;
        this.verifyClassNameUseCase = verifyClassNameUseCase;
        this.verifyPackageDefUseCase = verifyPackageDefUseCase;
        this.createDecoratorUseCase = createDecoratorUseCase;
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
        createDecoratorUseCase.execute(packageDef, className, decoratorType);
    }
}
