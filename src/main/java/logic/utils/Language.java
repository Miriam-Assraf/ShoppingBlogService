package logic.utils;

public enum Language {
    EN("en"),
    HE("he"),
    RU("ru"),
    ES("es"),
    FR("fr"),
    AR("ar");

    private final String language;

    Language(final String language){
        this.language=language;
    }

    @Override
    public String toString() {
        return language;
    }
}
