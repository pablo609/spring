package com.apress.isf.spring.service;

import com.apress.isf.spring.model.Document;
import com.apress.isf.spring.model.Type;

import java.util.List;

public interface SearchEngine {
    List<Document> findByType(Type type);
    List<Document> listAll();
    List<Document> findByLocation(String location);
}
