package com.unikre.pixabay.params;

public enum ResponseGroup {

    DEFAULT("image_details"),
    IMAGE_DETAILS("image_details"),
    HIGH_RESOLUTION("high_resolution");

    private final String code;

    private ResponseGroup(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static ResponseGroup byCode(String code) {
        for (ResponseGroup language : values()) {
            if (language.code.equals(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Unknown ResponseGroup code : " + code);
    }
}
