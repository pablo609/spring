package com.apress.isf.spring.data;

import com.apress.isf.spring.model.Document;

import java.util.List;

public interface DocumentDAO {
    public List<Document> getAll();
}
