package com.unikre.pixabay.params;

public enum Language {

    DEFAULT("en"),
    CZECH("cs"),
    DANISH("da"),
    GERMAN("de"),
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr"),
    INDONESIAN("id"),
    ITALIAN("it"),
    HUNGARIAN("hu"),
    DUTCH("nl"),
    NORWEGIAN("no"),
    POLISH("pl"),
    PORTUGUESE("pt"),
    ROMANIAN("ro"),
    SLOVAKIAN("sk"),
    FINNISH("fi"),
    SWEDISH("sv"),
    TURKISH("tr"),
    VIETNAMESE("vi"),
    THAI("th"),
    BULGARIAN("bg"),
    RUSSIAN("ru"),
    GREEK("el"),
    JAPANESE("ja"),
    KOREAN("ko"),
    CHINESE("zh");

    private final String code;

    private Language(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static Language byCode(String code) {
        for (Language language : values()) {
            if (language.code.equals(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Unknown language code : " + code);
    }
}
