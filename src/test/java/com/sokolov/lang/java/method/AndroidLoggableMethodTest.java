package com.sokolov.lang.java.method;

import com.sokolov.lang.java.method.android.AndroidLoggableMethod;
import com.sokolov.lang.java.method.java.MethodFromString;
import com.sokolov.lang.java.method.java.OriginMethod;
import org.junit.Assert;
import org.junit.Test;

public class AndroidLoggableMethodTest {

    @Test
    public void implementation() {
        Assert.assertEquals(
                "public void bind(ICategoryDisplayModel displayModel, OnItemClickListener onItemClickListener){Log.d(TAG, \"bind() called with: displayModel = [\" + displayModel + \"], onItemClickListener = [\" + onItemClickListener + \"]\");origin.bind(displayModel,onItemClickListener);}",
                new AndroidLoggableMethod(
                        new OriginMethod(
                                new MethodFromString(
                                        "public void bind(ICategoryDisplayModel displayModel, OnItemClickListener onItemClickListener);")),
                        "TAG")
                        .implementation());
    }
}