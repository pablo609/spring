package com.apress.isf.java.service;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;

import java.util.List;
import java.util.stream.Collectors;

public class SearchEngineService implements SearchEngine {
    @Override
    public List<Document> findByType(Type type) {
        return listAll().stream().filter(document -> document.getType().getName().equals(type.getName())).collect(Collectors.toList());
    }

    @Override
    public List<Document> listAll() {
        return null;
    }
}
