package com.apress.isf.spring.data;

import com.apress.isf.spring.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DocumentRepository implements DocumentDAO {
    private static final Logger log = LoggerFactory.getLogger(DocumentRepository.class);
    private List<Document> documents = null;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public List<Document> getAll() {
        log.info("Start <getAll> Params: ");
        List<Document> result = documents;
        log.info("End <getAll> Result:" + result);

        return result;
    }
}
