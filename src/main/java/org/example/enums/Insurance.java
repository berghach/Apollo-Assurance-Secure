package org.example.enums;

import jakarta.persistence.Enumerated;

public enum Insurance {
    AUTO("automobile"),
    HABITATION("habitation"),
    HEALTH("health");

    private final String name;

    Insurance(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
