package org.example.ApolloAssurance.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "zone")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "name", length = 30, nullable = false, unique = true)
    private String name;

    @Column(name = "is_danger", columnDefinition = "boolean")
    private boolean dangerous;

    public Zone() {
    }

    public Zone(String name, boolean dangerous) {
        this.name = name;
        this.dangerous = dangerous;
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
