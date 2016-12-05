package com.unikre.pixabay.params;

public enum Order {
    DEFAULT("popular"),
    POPULAR("popular"),
    LATEST("latest");

    private final String code;

    private Order(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static Order byCode(String code) {
        for (Order language : values()) {
            if (language.code.equals(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Unknown Order code : " + code);
    }
}
