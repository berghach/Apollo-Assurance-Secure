package dto;


import java.util.UUID;

public class DocumentDTO {
    private UUID id;
    private String path;
    private ContractDTO contract;

    public DocumentDTO(UUID id, String path, ContractDTO contract) {
        this.id = id;
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

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }
}
