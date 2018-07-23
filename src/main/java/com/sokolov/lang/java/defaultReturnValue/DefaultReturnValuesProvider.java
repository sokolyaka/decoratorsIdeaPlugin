package com.sokolov.lang.java.defaultReturnValue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DefaultReturnValuesProvider {
    private static Map<String, String> map;

    private DefaultReturnValuesProvider() {
    }

    public static Map<String, String> get() {
        if (map == null) {
            synchronized (DefaultReturnValuesProvider.class) {
                if (map == null) {
                    map = new HashMap<>();
                    IDefaultReturnValue defaultReturnValue = new IntegerDefaultReturnValue();
                    for (String type : defaultReturnValue.types()) {
                        map.put(type, defaultReturnValue.value());
                    }

                    defaultReturnValue = new LongDefaultReturnValue();
                    for (String type : defaultReturnValue.types()) {
                        map.put(type, defaultReturnValue.value());
                    }

                    defaultReturnValue = new ByteDefaultReturnValue();
                    for (String type : defaultReturnValue.types()) {
                        map.put(type, defaultReturnValue.value());
                    }

                    defaultReturnValue = new FloatDefaultReturnValue();
                    for (String type : defaultReturnValue.types()) {
                        map.put(type, defaultReturnValue.value());
                    }

                    defaultReturnValue = new DoubleDefaultReturnValue();
                    for (String type : defaultReturnValue.types()) {
                        map.put(type, defaultReturnValue.value());
                    }

                    defaultReturnValue = new CharDefaultReturnValue();
                    for (String type : defaultReturnValue.types()) {
                        map.put(type, defaultReturnValue.value());
                    }

                    defaultReturnValue = new ShortDefaultReturnValue();
                    for (String type : defaultReturnValue.types()) {
                        map.put(type, defaultReturnValue.value());
                    }

                    defaultReturnValue = new ObjectDefaultReturnValue();
                    for (String type : defaultReturnValue.types()) {
                        map.put(type, defaultReturnValue.value());
                    }
                    map = Collections.unmodifiableMap(map);
                }
            }
        }
        return map;
    }
}
