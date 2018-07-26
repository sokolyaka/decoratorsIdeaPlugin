package com.sokolov.decoratorsIdeaPlugin.dialog.domain.verifyPackage;

import com.intellij.psi.impl.file.PsiDirectoryFactory;

public class PsiVerifyPackageDefUseCase implements IVerifyPackageDefUseCase {
    private final PsiDirectoryFactory psiDirectoryFactory;

    public PsiVerifyPackageDefUseCase(PsiDirectoryFactory psiDirectoryFactory) {
        this.psiDirectoryFactory = psiDirectoryFactory;
    }

    @Override
    public boolean execute(String packageDef) {
        return psiDirectoryFactory.isValidPackageName(packageDef);
    }
}
