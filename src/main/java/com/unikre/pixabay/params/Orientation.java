package com.unikre.pixabay.params;

public enum Orientation {

    DEFAULT("all"),
    ALL("all"),
    HORIZONTAL("horizontal"),
    VERTICAL("vertical");

    private final String code;

    private Orientation(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static Orientation byCode(String code) {
        for (Orientation language : values()) {
            if (language.code.equals(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Unknown Orientation code : " + code);
    }
}
