package com.example.demospringcontent.repositories;

import com.example.demospringcontent.entities.SopDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SopDocumentRepository extends JpaRepository<SopDocument, Long> {
}