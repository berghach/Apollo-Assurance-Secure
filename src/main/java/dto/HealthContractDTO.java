package dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class HealthContractDTO extends ContractDTO{
    private int insuredAge;
    private String chronicIllness;
    private boolean premium;

    public HealthContractDTO(boolean accepted, BigDecimal amount, LocalDate submittedOn, LocalDate expiredOn, ClientDTO client, int insuredAge, String chronicIllness, boolean premium) {
        super(accepted, amount, submittedOn, expiredOn, client);
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
