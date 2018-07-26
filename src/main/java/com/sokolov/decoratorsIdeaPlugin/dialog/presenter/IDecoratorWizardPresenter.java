package com.sokolov.decoratorsIdeaPlugin.dialog.presenter;

public interface IDecoratorWizardPresenter {

    void onCreated();

    void onClassNameChanged(String className);

    void onPackageNameChanged(String packageDef);

    void onDecoratorTypeSelected(int type);

    void onConfirm();

}
