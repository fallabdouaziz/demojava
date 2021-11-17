package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleName {
    ADMIN("Admin"),
    STUDENT("Student"),
    TEACHER("Teacher"),
    TUTOR("Tutor");

    @JsonValue
    private final String value;

    RoleName(String value) {
        this.value = value;
    }
}
