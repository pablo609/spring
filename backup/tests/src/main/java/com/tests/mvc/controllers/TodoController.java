package com.tests.mvc.controllers;

import com.tests.mvc.models.TodoDTO;
import com.tests.mvc.services.TodoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private TodoService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<TodoDTO> findAll() {
        return service.findAll();
    }
}
