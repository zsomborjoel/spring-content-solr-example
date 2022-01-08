package com.example.demospringcontent.services;

import com.example.demospringcontent.entities.SopDocument;
import com.example.demospringcontent.repositories.SopDocumentContentStore;
import com.example.demospringcontent.repositories.SopDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SopDocumentService {

    private final SopDocumentRepository documentRepository;
    private final SopDocumentContentStore contentStore;

    public Resource getResourceById(UUID id){
        return contentStore.getResource(id);
    }

    public Iterable<UUID> searchForText(String text) {
        return contentStore.search(text);
    }

    public InputStream getContent(SopDocument sopDocument) {
        return contentStore.getContent(sopDocument);
    }

    public List<SopDocument> getDocuments() {
        return documentRepository.findAll();
    }

    public SopDocument getDocument(Long id) {
        return documentRepository.getById(id);
    }

    public SopDocument saveDocument(String sourcePath) throws FileNotFoundException {
        SopDocument doc = new SopDocument();
        doc.setTitle("saveDocument");
        doc.setAuthor("test_author");
        contentStore.setContent(doc, new FileInputStream(sourcePath));
        documentRepository.save(doc);
        return doc;
    }

    public SopDocument saveDocument(InputStream inputStream) {
        SopDocument doc = new SopDocument();
        doc.setTitle("saveInputStream");
        doc.setAuthor("test_author");
        contentStore.setContent(doc, inputStream);
        return documentRepository.save(doc);
    }

}
