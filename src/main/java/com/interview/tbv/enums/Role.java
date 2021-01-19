package com.interview.tbv.enums;

/**
 * Created by xuantoijm on 06/02/2020
 */
public enum Role {
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE"),
    USER("USER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
