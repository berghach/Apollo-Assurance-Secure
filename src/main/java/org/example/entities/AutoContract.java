package org.example.entities;

import org.example.enums.Insurance;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("automobile")
public class AutoContract extends Contract{
    @Column(name = "driver_age", columnDefinition = "integer", nullable = false)
    private int driverAge;

    @Column(name = "is_luxurious", columnDefinition = "boolean", nullable = false)
    private boolean luxurious;

    @Column(name = "is_professional", columnDefinition = "boolean", nullable = false)
    private boolean professional;

    @Column(name = "been_damaged", columnDefinition = "boolean", nullable = false)
    private boolean damaged;

    @Column(name = "damage_history", columnDefinition = "text")
    private String damageHistory;

    public AutoContract() {
    }

    public AutoContract(boolean accepted, BigDecimal amount, int driverAge, boolean luxurious, boolean professional, boolean damaged, String damageHistory) {
        super(accepted, amount);
        this.driverAge = driverAge;
        this.luxurious = luxurious;
        this.professional = professional;
        this.damaged = damaged;
        this.damageHistory = damageHistory;
    }

    public Insurance getContractType(){
        return Insurance.AUTO;
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
    public Client getClient() {
        return super.getClient();
    }

    @Override
    public void setClient(Client client) {
        super.setClient(client);
    }

    @Override
    public List<Document> getDocuments() {
        return super.getDocuments();
    }

    @Override
    public void setDocuments(List<Document> documents) {
        super.setDocuments(documents);
    }

    public int getAge() {
        return driverAge;
    }

    public void setAge(int driverAge) {
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
