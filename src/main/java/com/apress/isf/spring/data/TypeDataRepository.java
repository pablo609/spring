package com.apress.isf.spring.data;

import com.apress.isf.spring.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class TypeDataRepository implements TypeDataDAO {
    private static final Logger log = LoggerFactory.getLogger(TypeDataRepository.class);

    @Autowired
    private Map<String, Type> types;

    @Override
    public List<Type> getAll() {
        return Arrays.asList(types.values().toArray(new Type[0]));
    }

    @Override
    public Type findById(String id) {
        return types.get(id);
    }
}
