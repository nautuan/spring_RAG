package fr.efrei.rag.web.rest;

import fr.efrei.rag.domain.Document;
import fr.efrei.rag.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/documents")
@Service
public class DocumentRessource {

    private final List<Document> documentList = new ArrayList<>();

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