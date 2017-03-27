package com.apress.isf.spring.data;

import com.apress.isf.spring.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentRepository implements DocumentDAO {
    private static final Logger log = LoggerFactory.getLogger(DocumentRepository.class);

    @Autowired
    private List<Document> documents;

    @Override
    public List<Document> getAll() {
        return documents;
    }
}
