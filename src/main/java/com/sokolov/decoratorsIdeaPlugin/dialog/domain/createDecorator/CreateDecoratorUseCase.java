package com.sokolov.decoratorsIdeaPlugin.dialog.domain.createDecorator;

import com.sokolov.decoratorsIdeaPlugin.dialog.domain.DecoratorTypes;
import com.sokolov.decoratorsIdeaPlugin.dialog.domain.ICreateDecoratorUseCase;
import com.sokolov.lang.java.decorator.*;
import com.sokolov.lang.java.interfaceDef.IInterface;
import com.sokolov.lang.java.method.android.inMainThread.Java7InMainThreadMethodBuilder;
import com.sokolov.lang.java.method.android.inMainThread.Java8InMainThreadMethodBuilder;
import com.sokolov.lang.java.method.java.async.Java7AsyncMethodBuilder;
import com.sokolov.lang.java.method.java.async.Java8AsyncMethodBuilder;

public class CreateDecoratorUseCase implements ICreateDecoratorUseCase {
    private final int javaVersion;

    public CreateDecoratorUseCase(int javaVersion) {
        this.javaVersion = javaVersion;
    }

    @Override
    public IDecorator execute(String packageDef, String className, int decoratorType, IInterface iInterface) {
        IDecorator decorator =
                new OriginDecorator(
                        packageDef,
                        className,
                        iInterface);

        switch (decoratorType) {
            case DecoratorTypes.ORIGIN:
                return decorator;

            case DecoratorTypes.ANDROID_LOG:
                return
                        new AndroidLoggableDecorator(
                                decorator);

            case DecoratorTypes.ASYNC:
                return
                        new AsyncDecorator(
                                decorator,
                                javaVersion >= 8
                                        ? new Java8AsyncMethodBuilder()
                                        : new Java7AsyncMethodBuilder());

            case DecoratorTypes.IN_MAIN_THREAD:
                return
                        new InMainThreadDecorator(
                                decorator,
                                javaVersion >= 8
                                        ? new Java8InMainThreadMethodBuilder()
                                        : new Java7InMainThreadMethodBuilder());

            case DecoratorTypes.SAFE:
                return
                        new SafeDecorator(decorator);

            case DecoratorTypes.SYNC:
                return
                        new SyncDecorator(
                                decorator);

            default:
                throw new IllegalArgumentException("decoratorType = " + decoratorType);
        }
    }
}
