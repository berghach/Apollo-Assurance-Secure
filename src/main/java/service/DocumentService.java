package service;

import dto.DocumentDTO;
import entities.Document;
import mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DocumentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    private final Mapper<Document, DocumentDTO> documentMapper;

    public DocumentService(Mapper<Document, DocumentDTO> documentMapper) {
        this.documentMapper = documentMapper;
    }

    public List<DocumentDTO> findAll(){
        return documentMapper.toDTOList(documentRepository.findAll());
    }
    public Optional<DocumentDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<Document> documentOptional = documentRepository.findById(uuid);

        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            DocumentDTO documentDTO = documentMapper.toDTO(document);
            return Optional.of(documentDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(DocumentDTO documentDTO){
        Document document = documentMapper.fromDTO(documentDTO);
        documentRepository.save(document);
    }
    @Transactional
    public boolean update(UUID uuid, DocumentDTO documentUpdateDTO) throws IllegalArgumentException{
        Document existingDocument = documentRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No document corresponding to this identity."));

        Document oldDocument = existingDocument;

        existingDocument = documentMapper.fromDTO(documentUpdateDTO);

        Document newDocument = documentRepository.save(existingDocument);

        return !oldDocument.equals(newDocument);
    }
    @Transactional
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        Document existingDocument = documentRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No document corresponding to this identity."));

        documentRepository.delete(existingDocument);

        return !documentRepository.existsById(uuid);
    }
}
