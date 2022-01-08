package com.example.demospringcontent.controllers;

import com.example.demospringcontent.entities.SopDocument;
import com.example.demospringcontent.services.SopDocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static com.example.demospringcontent.utils.FileUtil.copyInputStreamToFile;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommandLineRunnerTest implements CommandLineRunner {

    private static final String BASE_DIR = "D:\\GIT\\demo-spring-content\\demo-spring-content\\src\\main\\resources\\";

    private final SopDocumentService sopDocumentService;

    public void demoFileMoveWithSearch() throws IOException {
        String sourcePath = BASE_DIR + "sample.pdf";
        String destinationPath = BASE_DIR + "demoFileMoveWithSearch.pdf";

        SopDocument sopDocument = sopDocumentService.saveDocument(sourcePath);
        log.info("Doc saved");

        System.out.println("Contains: " + sopDocument.getTitle());
        System.out.println("Content Id: " + sopDocument.getContentId());
        System.out.println("Content Len: " + sopDocument.getContentLen());

        InputStream inputStream = sopDocumentService.getContent(sopDocument);
        copyInputStreamToFile(inputStream, new File(destinationPath));
        log.info("Done file move");

        Iterable<UUID> iterable = sopDocumentService.searchForText("text");
        System.out.println("HasNext: " + iterable.iterator().hasNext());
        System.out.println("Estimated size: " + iterable.spliterator().estimateSize());
        System.out.println("Exact size: " + iterable.spliterator().getExactSizeIfKnown());

        System.out.println("Found data: ");
        StreamSupport.stream(iterable.spliterator(), false)
                .forEach(System.out::println);
    }

    public void demoFileSearch() throws IOException {
        SopDocument sopDocument = sopDocumentService.saveDocument(new ByteArrayInputStream("data by hand".getBytes()));
        log.info("Doc saved");

        System.out.println("Contains: " + sopDocument.getTitle());
        System.out.println("Content Id: " + sopDocument.getContentId());
        System.out.println("Content Len: " + sopDocument.getContentLen());

        InputStream inputStream = sopDocumentService.getContent(sopDocument);
        copyInputStreamToFile(inputStream, new File(BASE_DIR + "demoFileSearch.txt"));

        Iterable<UUID> iterable = sopDocumentService.searchForText("data*");
        System.out.println("HasNext: " + iterable.iterator().hasNext());
        System.out.println("Estimated size: " + iterable.spliterator().estimateSize());
        System.out.println("Exact size: " + iterable.spliterator().getExactSizeIfKnown());

        System.out.println("Found data: ");
        StreamSupport.stream(iterable.spliterator(), false)
                .forEach(System.out::println);
    }

    @Override
    public void run(String... args) throws Exception {
        demoFileSearch();
    }
}
