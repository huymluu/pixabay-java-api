package com.unikre.pixabay.params;

public enum ImageType {

    DEFAULT("all"),
    ALL("all"),
    PHOTO("photo"),
    ILLUSTRATION("illustration"),
    VECTOR("vector");

    private final String code;

    private ImageType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static ImageType byCode(String code) {
        for (ImageType language : values()) {
            if (language.code.equals(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Unknown ImageType code : " + code);
    }
}
