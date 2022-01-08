package com.example.demospringcontent.repositories;

import com.example.demospringcontent.entities.SopDocument;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.commons.search.Searchable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SopDocumentContentStore extends ContentStore<SopDocument, UUID>, Searchable<UUID> {
}
