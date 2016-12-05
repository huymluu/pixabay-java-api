package com.unikre.pixabay.params;

public enum VideoType {

    DEFAULT("all"),
    ALL("all"),
    FILM("film"),
    ANIMATION("animation");

    private final String code;

    private VideoType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static VideoType byCode(String code) {
        for (VideoType language : values()) {
            if (language.code.equals(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Unknown VideoType code : " + code);
    }
}
