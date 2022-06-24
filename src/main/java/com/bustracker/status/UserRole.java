package com.bustracker.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN("ADMIN"),
    BUS_DRIVER("BUS_DRIVER"),
    MANAGER("MANAGER");

    private String value;
}
