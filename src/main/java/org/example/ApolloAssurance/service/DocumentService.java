package org.example.ApolloAssurance.service;

import org.example.ApolloAssurance.dto.DocumentDTO;
import org.example.ApolloAssurance.entities.Document;
import org.example.ApolloAssurance.mappers.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.ApolloAssurance.repository.DocumentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private GenericMapper genericMapper;

    
    public List<DocumentDTO> findAll(){
        return genericMapper.toDTOList(documentRepository.findAll(), DocumentDTO.class);
    }
    public Optional<DocumentDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<Document> documentOptional = documentRepository.findById(uuid);

        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            DocumentDTO documentDTO = genericMapper.toDTO(document, DocumentDTO.class);
            return Optional.of(documentDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(DocumentDTO documentDTO){
        Document document = genericMapper.fromDTO(documentDTO, Document.class);
        documentRepository.save(document);
    }
    @Transactional
    public boolean update(UUID uuid, DocumentDTO documentUpdateDTO) throws IllegalArgumentException{
        Document existingDocument = documentRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No document corresponding to this identity."));

        Document oldDocument = existingDocument;

        existingDocument = genericMapper.fromDTO(documentUpdateDTO, Document.class);

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
