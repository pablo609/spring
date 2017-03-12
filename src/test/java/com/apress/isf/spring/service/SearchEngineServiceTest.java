package com.apress.isf.spring.service;

import com.apress.isf.spring.config.MyDocumentsContext;
import com.apress.isf.spring.model.Document;
import com.apress.isf.spring.model.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyDocumentsContext.class)
public class SearchEngineServiceTest {
    @Autowired
    private SearchEngine engine;
    @Autowired
    private Type web;
    @Autowired @Qualifier("pdf")
    private Type pdf;
    @Autowired
    LoginService loginService;
    @Value("${user.login}")
    String user;
    @Value("${user.password}")
    String password;

    @Test
    public void shouldFindDocumentsByWebType() {
        List<Document> result = engine.findByType(web);
        assertThat(result).size().isEqualTo(1);
    }

    @Test
    public void shouldFindDocumentsByPdfType() {
        List<Document> result = engine.findByType(pdf);
        assertThat(result).size().isEqualTo(2);
    }

    @Test
    public void shouldListAllDocuments() {
        List<Document> result = engine.listAll();
        assertThat(result).size().isEqualTo(4);
    }

    @Test
    public void shouldAuthorizedUser() {
        assertThat(loginService.isAuthorized(user , password)).isTrue();
    }

    @Test
    public void shouldNotAuthorizedUserByLogin() {
        assertThat(loginService.isAuthorized(user + "1" , password)).isFalse();
    }

    @Test
    public void shouldNotAuthorizedUserByPassword() {
        assertThat(loginService.isAuthorized(user, password + "1")).isFalse();
    }
}
