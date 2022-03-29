package com.construo.ff4j.enums;

import static com.construo.ff4j.constants.FF4JKeys.ENABLE_DIVISION_CALC;

public enum FF4JIdentifier {

    DIVISION_CALC_ID(ENABLE_DIVISION_CALC, false);

    private final String name;
    private final boolean enable;

    FF4JIdentifier(String name, boolean enable) {
        this.name = name;
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public boolean isEnable() {
        return enable;
    }
}
