package com.devlog.core.common.enumulation;

public enum UserRole {

    ADMIN("ADMIN", "관리자"),
    USER("USER", "사용자");

    private final String value;
    private final String desc;

    UserRole(String v, String d) {
        value = v;
        desc = d;
    }

    public static UserRole fromValue(String v) {
        for (UserRole c : UserRole.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value() {
        return value;
    }
}
