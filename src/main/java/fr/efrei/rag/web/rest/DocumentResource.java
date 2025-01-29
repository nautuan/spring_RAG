package fr.efrei.rag.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.efrei.rag.domain.Document;
import fr.efrei.rag.repository.DocumentRepository;

import fr.efrei.rag.domain.Document;
import fr.efrei.rag.service.DocumentService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/documents")
@Service
public class DocumentResource {
    private final List<Document> documentList = new ArrayList<>();

    private final DocumentRepository documentRepository;
    // test
    public DocumentResource(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @GetMapping("/documents")
    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }


    @GetMapping("/{id}")
    public Document getDocument(@PathVariable Long id) {
        return documentList.stream()
                .filter(doc -> doc.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/create")
    public Document createDocument(@RequestBody Document document) {
        documentList.add(document);
        return document;
    }

    @PutMapping("/update/{id}")
    public Document updateDocument(@PathVariable Long id, @RequestBody Document updatedDocument) {
        for (int i = 0; i < documentList.size(); i++) {
            if (documentList.get(i).getId().equals(id)) {
                documentList.set(i, updatedDocument);
                return updatedDocument;
            }
        }
        return null;
    }
}
