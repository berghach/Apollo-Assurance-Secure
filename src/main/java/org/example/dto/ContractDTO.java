package org.example.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ContractDTO {
    private UUID id;
    private boolean accepted;
    private BigDecimal amount;
    private LocalDate submittedOn;
    private LocalDate expiredOn;
    private ClientDTO client;
    private List<DocumentDTO> documents;

    public ContractDTO(boolean accepted, BigDecimal amount, LocalDate submittedOn, LocalDate expiredOn, ClientDTO client) {
        this.accepted = accepted;
        this.amount = amount;
        this.submittedOn = submittedOn;
        this.expiredOn = expiredOn;
        this.client = client;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(LocalDate submittedOn) {
        this.submittedOn = submittedOn;
    }

    public LocalDate getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(LocalDate expiredOn) {
        this.expiredOn = expiredOn;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }
}
