package com.example.demospringcontent.configs;

import com.example.demospringcontent.entities.SopDocument;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.solr.AttributeProvider;
import org.springframework.content.solr.EnableFullTextSolrIndexing;
import org.springframework.content.solr.FilterQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableFullTextSolrIndexing
@EnableFilesystemStores
public class ApplicationConfig {

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder("http://localhost:8983/solr/solrtext").build();
    }

    @Bean
    public AttributeProvider<SopDocument> attributeProvider() {
        return sopDocument -> {
            Map<String, String> attributes = new HashMap<>();
            attributes.put("title", sopDocument.getTitle());
            attributes.put("author", sopDocument.getAuthor()); // wont work until its mapped in schema.xml solr config
            return attributes;
        };
    }

}
