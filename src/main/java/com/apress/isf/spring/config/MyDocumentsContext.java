package com.apress.isf.spring.config;

import com.apress.isf.spring.aop.BeforeLoggingModule;
import com.apress.isf.spring.data.TypeDataDAO;
import com.apress.isf.spring.model.Document;
import com.apress.isf.spring.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("user.properties")
@ComponentScan("com.apress.isf.spring")
public class MyDocumentsContext {
    @Autowired
    private BeforeLoggingModule loggingModule;

    @Autowired
    private TypeDataDAO typeDataDAO;

    @Bean
    @Profile("dev")
    public Document doc1() {
        Document document = new Document();
        document.setName("Book Template");
        document.setType(typeDataDAO.findById("pdf"));
        document.setLocation("/Users/felipeg/Documents/Random/Book Template.pdf");
        return document;
    }

    @Bean
    public Document doc2() {
        Document document = new Document();
        document.setName("Sample Contract");
        document.setType(typeDataDAO.findById("pdf"));
        document.setLocation("/Users/felipeg/Documents/Contracts/Sample Contract.pdf");
        return document;
    }

    @Bean
    public Document doc3() {
        Document document = new Document();
        document.setName("Clustering with RabbitMQ");
        document.setType(typeDataDAO.findById("note"));
        document.setLocation("/Users/felipeg/Documents/Random/Clustering with RabbitMQ.txt");
        return document;
    }

    @Bean
    public Document doc4() {
        Document document = new Document();
        document.setName("Pro Spring Security Book");
        document.setType(typeDataDAO.findById("web"));
        document.setLocation("http://www.apress.com/9781430248187");
        return document;
    }

    @Bean
    public Type pdf() {
        Type type = new Type();
        type.setName("PDF");
        type.setDesc("Portable Document Format");
        type.setExtension(".pdf");
        return type;
    }

    @Bean
    public Type note() {
        Type type = new Type();
        type.setName("NOTE");
        type.setDesc("Text Notes");
        type.setExtension(".txt");
        return type;
    }

    @Bean
    public Type web() {
        Type type = new Type();
        type.setName("WEB");
        type.setDesc("Web Link");
        type.setExtension(".url");
        return type;
    }
}
