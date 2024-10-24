package org.example.dto;

import java.util.UUID;

public class ZoneDTO {
    private UUID id;
    private String name;
    private boolean dangerous;

    public ZoneDTO(UUID id, String name, boolean dangerous) {
        this.id = id;
        this.name = name;
        this.dangerous = dangerous;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDangerous() {
        return dangerous;
    }

    public void setDangerous(boolean dangerous) {
        this.dangerous = dangerous;
    }
}
