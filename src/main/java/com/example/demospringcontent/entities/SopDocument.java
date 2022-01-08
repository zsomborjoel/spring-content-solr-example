package com.example.demospringcontent.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.fulltext.Highlight;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class SopDocument {
    private @Id @GeneratedValue Long id;
    private String title;
    private String author;

    // Spring Content managed attribute
    private @ContentId UUID contentId;
    private @ContentLength Long contentLen;
}