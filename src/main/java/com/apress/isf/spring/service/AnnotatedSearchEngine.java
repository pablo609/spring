package com.apress.isf.spring.service;

import com.apress.isf.spring.data.DocumentDAO;
import com.apress.isf.spring.model.Document;
import com.apress.isf.spring.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("engine")
public class AnnotatedSearchEngine implements SearchEngine {

    @Autowired
    private DocumentDAO documentDAO;

    @Override
    public List<Document> findByType(Type type) {
        return listAll().stream().filter(document -> document.getType().getName().equals(type.getName())).collect(Collectors.toList());
    }

    @Override
    public List<Document> listAll() {
        return documentDAO.getAll();
    }
}
