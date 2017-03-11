package com.apress.isf.spring.service;

import com.apress.isf.spring.data.DocumentDAO;
import com.apress.isf.spring.model.Document;
import com.apress.isf.spring.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class SearchEngineService implements SearchEngine {
    private static final Logger log = LoggerFactory.getLogger(SearchEngineService.class);
    @Autowired
    private DocumentDAO documentDAO;

    public void setDocumentDAO(DocumentDAO documentDAO) {
        log.info("Document DAO set: " + documentDAO);
        this.documentDAO = documentDAO;
    }

    @Override
    public List<Document> findByType(Type type) {
        return listAll().stream().filter(document -> document.getType().getName().equals(type.getName())).collect(Collectors.toList());
    }

    @Override
    public List<Document> listAll() {
        return documentDAO.getAll();
    }
}
