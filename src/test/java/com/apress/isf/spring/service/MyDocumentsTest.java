package com.apress.isf.spring.service;

import com.apress.isf.spring.model.Type;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyDocumentsTest {
    private ClassPathXmlApplicationContext context;
    private SearchEngine engine;
    private Type webType;

//    @Before
//    public void setup(){
//        context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-context.xml");
//        engine = context.getBean(SearchEngine.class);
//        webType = context.getBean("webType", Type.class);
//    }
//
//    @Test
//    public void shouldFindDocumentsByType() {
//        List<Document> result = engine.findByType(webType);
//        assertThat(result).size().isEqualTo(1);
//    }
//
//    @Test
//    public void shouldListAllDocuments() {
//        List<Document> result = engine.listAll();
//        assertThat(result).size().isEqualTo(4);
//    }
}
