package com.sokolov.decoratorsIdeaPlugin.dialog.presenter;

public interface IDecoratorWizardPresenter {

    void onClassNameChanged(String className);

    void onPackageNameChanged(String packageDef);

    void onDecoratorTypeSelected(int type);

    void onConfirm();

}
