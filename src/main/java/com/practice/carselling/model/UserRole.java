package com.practice.carselling.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    ROLE_USER,
    ROLE_ADMIN;

    @JsonCreator //JSON Creator for checking userRole value in POST/PUT requests. If role not exists return null
    public static UserRole forName(String name) {
        for (UserRole role : values()) {
            if (role.name().equals(name)) {
                return role;
            }
        }
        return null;
    }
}
