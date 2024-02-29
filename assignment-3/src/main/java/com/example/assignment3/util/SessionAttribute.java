package com.example.assignment3.util;

public enum SessionAttribute {
    NICKNAME("nickname");

    private final String value;

    SessionAttribute(String envUrl) {
        this.value = envUrl;
    }

    public String getValue() {
        return value;
    }
}
