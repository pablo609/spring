package com.tests.mvc.services;

import com.tests.mvc.models.TodoDTO;

import java.util.List;

public interface TodoService {
    List<TodoDTO> findAll();
}
