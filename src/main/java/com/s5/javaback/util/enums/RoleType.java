package com.s5.javaback.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

    ROLE_USER(1, "USER"),
    ROLE_ADMIN(2, "ADMIN");

    private final long id;
    private final String name;

}
