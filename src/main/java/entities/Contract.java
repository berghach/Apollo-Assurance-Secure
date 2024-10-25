package entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contract")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "insurance", discriminatorType = DiscriminatorType.STRING, columnDefinition = "insurance_type")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;


    @Column(name = "is_accepted", columnDefinition = "boolean default false")
    private boolean accepted = false;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "submitted_on", columnDefinition = "DATE")
    private LocalDate submittedOn;

    @Column(name = "expired_on", columnDefinition = "DATE")
    private LocalDate expiredOn;

    @ManyToOne
    @JoinColumn(
            name = "client_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_client_contract"))
    private Client client;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<Document> documents;

    public Contract() {
    }

    public Contract(boolean accepted, BigDecimal amount) {
        this.accepted = accepted;
        this.amount = amount;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}