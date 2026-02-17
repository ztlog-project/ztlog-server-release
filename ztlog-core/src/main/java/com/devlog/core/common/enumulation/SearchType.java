package com.devlog.core.common.enumulation;

public enum SearchType {

    TITLE("TITLE","제목"),
    CONTENT("CONTENT", "내용"),
    TITLE_CONTENT("TCONTENT", "제목+내용"),
    TAG("TAG", "태그");

    private final String value;
    private final String desc;

    SearchType(String v, String d) {
        value = v;
        desc = d;
    }

    public String value() {
        return value;
    }
}
