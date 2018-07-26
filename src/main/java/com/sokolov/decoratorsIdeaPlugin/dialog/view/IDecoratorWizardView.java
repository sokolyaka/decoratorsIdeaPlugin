package com.sokolov.decoratorsIdeaPlugin.dialog.view;

public interface IDecoratorWizardView {
    void showInvalidClassNameError();

    void showInvalidPackageDefError();

    void updateClassName(String className);

    void updatePackageDef(String packageDef);
}
