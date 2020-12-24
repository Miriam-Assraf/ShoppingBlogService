package acs.logic.utils;

public enum FilterType {
    BY_LANGUAGE("byLanguage"),
    BY_CREATION("byCreation"),
    BY_PRODUCT("byProduct"),
    BY_COUNT("byCount");

    private final String filter;

    FilterType(final String filter){
        this.filter=filter;
    }

    @Override
    public String toString() {
        return filter;
    }

}
