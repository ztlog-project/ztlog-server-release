package com.devlog.core.common.enumulation;

public enum UserRole {

    ADMIN("ADMIN", "관리자"),
    USER("USER", "사용자");

    private String value;
    private String desc;

    UserRole(String v, String d) {
        value = v;
        desc = d;
    }

    public String value() {
        return value;
    }

    public static UserRole fromValue(String v) {
        for (UserRole c : UserRole.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
