package com.apress.isf.spring.service;

import com.apress.isf.spring.config.MyDocumentsContext;
import com.apress.isf.spring.model.Document;
import com.apress.isf.spring.model.Type;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchEngineServiceTest {
    private ApplicationContext context;
    private SearchEngine engine;
    private Type webType;
    private Type pdfType;

    @Before
    public void setup(){
        context = new AnnotationConfigApplicationContext(MyDocumentsContext.class);
        engine = context.getBean(SearchEngine.class);
        webType = context.getBean("webType", Type.class);
        pdfType = context.getBean("pdfType", Type.class);
    }

    @Test
    public void shouldFindDocumentsByWebType() {
        List<Document> result = engine.findByType(webType);
        assertThat(result).size().isEqualTo(1);
    }

    @Test
    public void shouldFindDocumentsByPdfType() {
        List<Document> result = engine.findByType(pdfType);
        assertThat(result).size().isEqualTo(2);
    }

    @Test
    public void shouldListAllDocuments() {
        List<Document> result = engine.listAll();
        assertThat(result).size().isEqualTo(4);
    }
}
