package com.apress.isf.spring.data;

import com.apress.isf.spring.model.Document;
import com.apress.isf.spring.model.Type;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("documentDAO")
public class AnnotatedDocumentRepository implements DocumentDAO {

    @Override
    public List<Document> getAll() {
        return storage();
    }

    private List<Document> storage() {
        List<Document> result = new ArrayList<Document>();
        Type type = new Type();
        type.setName("PDF");
        type.setDesc("Portable Document Format");
        type.setExtension(".pdf");
        Document document = new Document();
        document.setName("Book Template");
        document.setType(type);
        document.setLocation("/Users/felipeg/Documents/Random/Book Template.pdf");
        result.add(document);
        document = new Document();
        document.setName("Sample Contract");
        document.setType(type);
        document.setLocation("/Users/felipeg/Documents/Contracts/Sample Contract.pdf");
        result.add(document);
        type = new Type();
        type.setName("NOTE");
        type.setDesc("Text Notes");
        type.setExtension(".txt");
        document = new Document();
        document.setName("Clustering with RabbitMQ");
        document.setType(type);
        document.setLocation("/Users/felipeg/Documents/Random/Clustering with RabbitMQ.txt");
        result.add(document);
        type = new Type();
        type.setName("WEB");
        type.setDesc("Web Link");
        type.setExtension(".url");
        document = new Document();
        document.setName("Pro Spring Security Book");
        document.setType(type);
        document.setLocation("http://www.apress.com/9781430248187");
        result.add(document);

        return result;
    }
}
