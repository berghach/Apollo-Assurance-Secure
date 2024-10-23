package dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class HabitationContractDTO extends ContractDTO{
    private BigDecimal propertyValue;
    private String type;
    private ZoneDTO zone;
    private String address;
    private String securitySystem;

    public HabitationContractDTO(UUID id, boolean accepted, BigDecimal amount, LocalDate submittedOn, LocalDate expiredOn, ClientDTO client, BigDecimal propertyValue, String type, ZoneDTO zone, String address, String securitySystem) {
        super(id, accepted, amount, submittedOn, expiredOn, client);
        this.propertyValue = propertyValue;
        this.type = type;
        this.zone = zone;
        this.address = address;
        this.securitySystem = securitySystem;
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

    public BigDecimal getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(BigDecimal propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ZoneDTO getZone() {
        return zone;
    }

    public void setZone(ZoneDTO zone) {
        this.zone = zone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSecuritySystem() {
        return securitySystem;
    }

    public void setSecuritySystem(String securitySystem) {
        this.securitySystem = securitySystem;
    }
}
