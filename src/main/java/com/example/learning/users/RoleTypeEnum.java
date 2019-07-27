package com.example.learning.users;

import lombok.Getter;

@Getter
public enum  RoleTypeEnum {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    STOREKEEPER("ROLE_STOREKEEPER");

    private String roleName;

    RoleTypeEnum(String roleName) {
        this.roleName = roleName;
    }
}
