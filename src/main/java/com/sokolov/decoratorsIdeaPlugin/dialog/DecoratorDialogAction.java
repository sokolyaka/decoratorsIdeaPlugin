package com.sokolov.decoratorsIdeaPlugin.dialog;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.file.PsiDirectoryFactory;
import com.intellij.util.IncorrectOperationException;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.addDecoratorToProject.AddDecoratorToPsiDirectoryUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.addDecoratorToProject.InWriteActionAddDecoratorToProjectUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.createDecorator.CreateDecoratorUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyClassName.VerifyClassNameUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyPackage.PsiVerifyPackageDefUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.presenter.DecoratorWizardPresenter;
import com.sokolov.decoratorsIdeaPlugin.dialog.presenter.IDecoratorWizardPresenter;
import com.sokolov.decoratorsIdeaPlugin.dialog.view.IDecoratorWizardView;
import com.sokolov.decoratorsIdeaPlugin.dialog.view.SwingDecoratorWizardView;
import com.sokolov.lang.java.interfaceDef.InterfaceFromString;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class DecoratorDialogAction extends BaseIntentionAction {

    @Nls
    @NotNull
    @Override
    public String getText() {
        return "Generate decorator";
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "Generate decorator";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return
                file.getFileType().equals(JavaFileType.INSTANCE);
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        IDecoratorWizardView wizardView = new SwingDecoratorWizardView();
        IDecoratorWizardPresenter wizardPresenter =
                new DecoratorWizardPresenter(
                        wizardView,
                        new VerifyClassNameUseCase(),
                        new PsiVerifyPackageDefUseCase(
                                PsiDirectoryFactory.getInstance(project)),
                        new CreateDecoratorUseCase(7),
                        new InWriteActionAddDecoratorToProjectUseCase(
                                new AddDecoratorToPsiDirectoryUseCase(
                                        ModuleManager.getInstance(project),
                                        PsiFileFactory.getInstance(project)),
                                ApplicationManager.getApplication()),
                        new InterfaceFromString(
                                editor
                                        .getDocument()
                                        .getText()),
                        getModulesNames(
                                ModuleManager
                                        .getInstance(project)
                                        .getModules()));

        wizardView.setWizardPresenter(wizardPresenter);
        wizardPresenter.onCreated();
    }

    private static String[] getModulesNames(Module[] modules) {
        String[] result = new String[modules.length];
        for (int i = 0; i < modules.length; i++) {
            result[i] = modules[i].getName();

        }
        return result;
    }

    @Override
    public boolean startInWriteAction() {
        return false;
    }
}
