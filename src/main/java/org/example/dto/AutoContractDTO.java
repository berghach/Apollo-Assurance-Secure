package org.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AutoContractDTO extends ContractDTO{
    private int driverAge;
    private boolean luxurious;
    private boolean professional;
    private boolean damaged;
    private String damageHistory;

    public AutoContractDTO(UUID id, boolean accepted, BigDecimal amount, LocalDate submittedOn, LocalDate expiredOn, ClientDTO client, int driverAge, boolean luxurious, boolean professional, boolean damaged, String damageHistory) {
        super(id, accepted, amount, submittedOn, expiredOn, client);
        this.driverAge = driverAge;
        this.luxurious = luxurious;
        this.professional = professional;
        this.damaged = damaged;
        this.damageHistory = damageHistory;
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    public void setId(UUID id) {
        super.setId(id);
    }

    @Override
    public boolean isAccepted() {
        return super.isAccepted();
    }

    @Override
    public void setAccepted(boolean accepted) {
        super.setAccepted(accepted);
    }

    @Override
    public BigDecimal getAmount() {
        return super.getAmount();
    }

    @Override
    public void setAmount(BigDecimal amount) {
        super.setAmount(amount);
    }

    @Override
    public LocalDate getSubmittedOn() {
        return super.getSubmittedOn();
    }

    @Override
    public void setSubmittedOn(LocalDate submittedOn) {
        super.setSubmittedOn(submittedOn);
    }

    @Override
    public LocalDate getExpiredOn() {
        return super.getExpiredOn();
    }

    @Override
    public void setExpiredOn(LocalDate expiredOn) {
        super.setExpiredOn(expiredOn);
    }

    @Override
    public ClientDTO getClient() {
        return super.getClient();
    }

    @Override
    public void setClient(ClientDTO client) {
        super.setClient(client);
    }

    @Override
    public List<DocumentDTO> getDocuments() {
        return super.getDocuments();
    }

    @Override
    public void setDocuments(List<DocumentDTO> documents) {
        super.setDocuments(documents);
    }

    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public boolean isLuxurious() {
        return luxurious;
    }

    public void setLuxurious(boolean luxurious) {
        this.luxurious = luxurious;
    }

    public boolean isProfessional() {
        return professional;
    }

    public void setProfessional(boolean professional) {
        this.professional = professional;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public String getDamageHistory() {
        return damageHistory;
    }

    public void setDamageHistory(String damageHistory) {
        this.damageHistory = damageHistory;
    }
}
