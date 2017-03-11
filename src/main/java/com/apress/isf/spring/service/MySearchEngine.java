package com.apress.isf.spring.service;

import com.apress.isf.spring.model.Document;
import com.apress.isf.spring.model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MySearchEngine implements SearchEngine {

    @Override
    public List<Document> findByType(Type type) {
        return storage().stream().filter(document -> document.getType().getName().equals(type.getName())).collect(Collectors.toList());
    }

    @Override
    public List<Document> listAll() {
        return storage();
    }

    private List<Document> storage() {
        List<Document> result = new ArrayList<>();
        Type type1 = new Type();
        type1.setName("PDF");
        type1.setDesc("Portable Document Format");
        type1.setExtension(".pdf");

        Type type2 = new Type();
        type2.setName("DOC");
        type2.setDesc("Word Document");
        type2.setExtension(".doc");

        Type type3 = new Type();
        type3.setName("WEB");
        type3.setDesc("Web Link");
        type3.setExtension(".url");

        Document document = new Document();
        document.setName("Book Template");
        document.setType(type1);
        document.setLocation("/Documents/Book Template.pdf");
        result.add(document);

        document = new Document();
        document.setName("Book Template 2");
        document.setType(type1);
        document.setLocation("/Documents/Book Template 2.pdf");
        result.add(document);

        document = new Document();
        document.setName("Book Template 3");
        document.setType(type2);
        document.setLocation("/Documents/Book Template 3.pdf");
        result.add(document);

        document = new Document();
        document.setName("Pro Spring Security Book");
        document.setType(type3);
        document.setLocation("http://www.apress.com/9781430248187");
        result.add(document);

        return result;
    }
}
