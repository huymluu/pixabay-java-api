package com.unikre.pixabay.params;

public enum Category {
    FASHION("fashion"),
    NATURE("nature"),
    BACKGROUNDS("backgrounds"),
    SCIENCE("science"),
    EDUCATION("education"),
    PEOPLE("people"),
    FEELINGS("feelings"),
    RELIGION("religion"),
    HEALTH("health"),
    PLACES("places"),
    ANIMALS("animals"),
    INDUSTRY("industry"),
    FOOD("food"),
    COMPUTER("computer"),
    SPORTS("sports"),
    TRANSPORTATION("transportation"),
    TRAVEL("travel"),
    BUILDINGS("buildings"),
    BUSINESS("business"),
    MUSIC("music");

    private final String code;

    private Category(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static Category byCode(String code) {
        for (Category language : values()) {
            if (language.code.equals(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Unknown Category code : " + code);
    }
}
