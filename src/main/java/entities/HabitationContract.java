package entities;

import enums.Insurance;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("habitation")
public class HabitationContract extends Contract{
    @Column(name = "property_value", precision = 10, scale = 2)
    private BigDecimal propertyValue;

    @Column(name = "type", length = 20)
    private String type;

    @ManyToOne
    @JoinColumn(
            name = "zone_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_zone_contract"))
    private Zone zone;

    @Column(name = "adresse", columnDefinition = "text")
    private String address;

    @Column(name = "security_system", length = 20)
    private String securitySystem;

    public HabitationContract() {
    }

    public HabitationContract(boolean accepted, BigDecimal amount, BigDecimal propertyValue, String type, Zone zone, String address, String securitySystem) {
        super(accepted, amount);
        this.propertyValue = propertyValue;
        this.type = type;
        this.zone = zone;
        this.address = address;
        this.securitySystem = securitySystem;
    }

    @Override
    public Insurance getContractType(){
        return Insurance.HABITATION;
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

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
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
