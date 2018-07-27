package com.sokolov.decoratorsIdeaPlugin.dialog.view;

import com.sokolov.decoratorsIdeaPlugin.dialog.presenter.IDecoratorWizardPresenter;

public interface IDecoratorWizardView {
    void init();

    void show();

    void showInvalidClassNameError();

    void showInvalidPackageDefError();

    void updateClassName(String className);

    void updatePackageDef(String packageDef);

    void setOkBtnEnable(boolean isEnable);

    void setWizardPresenter(IDecoratorWizardPresenter wizardPresenter);

    void setUpModuleNames(String[] moduleNames, String preSelectedModule);
}
