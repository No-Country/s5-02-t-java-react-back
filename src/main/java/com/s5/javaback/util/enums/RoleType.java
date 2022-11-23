package com.s5.javaback.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public enum RoleType {
    USER("USER"),
    ADMIN("ADMIN");
    private String ROLE_PREFIX ="ROLE_";
    private final String name ;
    RoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFullRoleName() {return ROLE_PREFIX + name;}
}
