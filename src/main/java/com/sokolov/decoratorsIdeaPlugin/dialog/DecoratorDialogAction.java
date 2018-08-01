package com.sokolov.decoratorsIdeaPlugin.dialog;

import com.intellij.codeInsight.daemon.impl.analysis.HighlightNamesUtil;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.ide.scratch.ScratchFileType;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.util.IncorrectOperationException;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.addDecoratorToProject.AddDecoratorToPsiDirectoryUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.addDecoratorToProject.InWriteActionAddDecoratorToProjectUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.createDecorator.CreateDecoratorUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyClassName.VerifyClassNameUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyPackage.PsiVerifyPackageDefUseCase;
import com.sokolov.decoratorsIdeaPlugin.dialog.presenter.DecoratorWizardPresenter;
import com.sokolov.decoratorsIdeaPlugin.dialog.presenter.IDecoratorWizardPresenter;
import com.sokolov.decoratorsIdeaPlugin.dialog.view.IDecoratorWizardView;
import com.sokolov.decoratorsIdeaPlugin.dialog.view.WizardDialog;
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

    private static boolean isSupportedLanguage(PsiClass aClass) {
        return aClass.getLanguage() == JavaLanguage.INSTANCE;
    }

    private static boolean shouldCreateInnerClass(PsiClass psiClass) {
        return psiClass.hasModifierProperty(PsiModifier.PRIVATE) && psiClass.getContainingClass() != null;
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        final CaretModel caretModel = editor.getCaretModel();
        final int position = caretModel.getOffset();
        PsiElement element = file.findElementAt(position);
        PsiClass psiClass = PsiTreeUtil.getParentOfType(element, PsiClass.class);
        if (psiClass == null || psiClass.isAnnotationType() || psiClass.isEnum() || psiClass instanceof PsiAnonymousClass ||
                psiClass.hasModifierProperty(PsiModifier.FINAL)) {
            return false;
        }
        VirtualFile virtualFile = PsiUtilCore.getVirtualFile(psiClass);
        if (virtualFile == null || virtualFile.getFileType() == ScratchFileType.INSTANCE) {
            return false;
        }
        if (!isSupportedLanguage(psiClass)) return false;
        final PsiMethod[] constructors = psiClass.getConstructors();
        if (constructors.length > 0) {
            boolean hasNonPrivateConstructor = false;
            for (PsiMethod constructor : constructors) {
                if (!constructor.hasModifierProperty(PsiModifier.PRIVATE)) {
                    hasNonPrivateConstructor = true;
                    break;
                }
            }
            if (!hasNonPrivateConstructor) return false;
        }
        PsiElement lBrace = psiClass.getLBrace();
        if (lBrace == null) return false;
        if (element.getTextOffset() >= lBrace.getTextOffset()) return false;

        TextRange declarationRange = HighlightNamesUtil.getClassDeclarationTextRange(psiClass);
        final TextRange elementTextRange = element.getTextRange();
        if (!declarationRange.contains(elementTextRange)) {
            if (!(element instanceof PsiWhiteSpace) || (declarationRange.getStartOffset() != elementTextRange.getEndOffset() &&
                    declarationRange.getEndOffset() != elementTextRange.getStartOffset())) {
                return false;
            }
        }

        if (shouldCreateInnerClass(psiClass) && !file.getManager().isInProject(file)) {
            return false;
        }

        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        IDecoratorWizardView wizardView = new WizardDialog(project);
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
                        new String[]{ModuleUtilCore.findModuleForPsiElement(file).getName()});

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
