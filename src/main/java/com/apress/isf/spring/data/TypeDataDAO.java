package com.apress.isf.spring.data;

import com.apress.isf.spring.model.Type;

import java.util.List;

public interface TypeDataDAO {
    List<Type> getAll();
    Type findById(String id);
}
