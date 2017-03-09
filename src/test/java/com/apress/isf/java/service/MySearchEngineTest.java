package com.apress.isf.java.service;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MySearchEngineTest {
    private ClassPathXmlApplicationContext context;
    private SearchEngine engine;
    private Type documentType;

    @Before
    public void setup(){
        context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-context.xml");
        engine = context.getBean(SearchEngine.class);
        documentType = context.getBean(Type.class);
    }

    @Test
    public void testFindByType() {
        List<Document> documents = engine.findByType(documentType);
        assertThat(documents).size().isEqualTo(2);
    }

    @Test
    public void testListAll() {
        List<Document> documents = engine.listAll();
        assertThat(documents).size().isEqualTo(4);
    }
}
