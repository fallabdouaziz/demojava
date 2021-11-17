package com.example.demo.model;

import lombok.Getter;

@Getter
public enum PrivilegeName {

    USER_READ("user:read"),
    USER_ADD("user:add"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete");

    private final String privilege;

    PrivilegeName(String privilege) { this.privilege = privilege;}
}
