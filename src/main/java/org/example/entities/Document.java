package org.example.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(
            name = "contract_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_contract_document"))
    private Contract contract;

    public Document() {
    }

    public Document(String path, Contract contract) {
        this.path = path;
        this.contract = contract;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
