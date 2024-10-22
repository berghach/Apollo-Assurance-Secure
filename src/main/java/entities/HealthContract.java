package entities;

import enums.Insurance;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("health")
public class HealthContract extends Contract{
    @Column(name = "insured_age", columnDefinition = "integer", nullable = false)
    private int insuredAge;

    @Column(name = "chronic_illness", length = 20)
    private String chronicIllness;

    @Column(name = "is_premium", columnDefinition = "boolean", nullable = false)
    private boolean premium;

    public HealthContract() {
    }

    public HealthContract(boolean accepted, BigDecimal amount, int insuredAge, String chronicIllness, boolean premium) {
        super(accepted, amount);
        this.insuredAge = insuredAge;
        this.chronicIllness = chronicIllness;
        this.premium = premium;
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

    public int getInsuredAge() {
        return insuredAge;
    }

    public void setInsuredAge(int insuredAge) {
        this.insuredAge = insuredAge;
    }

    public String getChronicIllness() {
        return chronicIllness;
    }

    public void setChronicIllness(String chronicIllness) {
        this.chronicIllness = chronicIllness;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
