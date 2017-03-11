package com.apress.isf.spring.data;

import com.apress.isf.spring.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TypeDataRepository implements TypeDataDAO {
    private static final Logger log = LoggerFactory.getLogger(TypeDataRepository.class);
    private Map<String,Type> types = null;

    public Map<String, Type> getTypes() {
        return types;
    }

    public void setTypes(Map<String, Type> types) {
        this.types = types;
    }

    @Override
    public List<Type> getAll() {
        return Arrays.asList(types.values().toArray(new Type[0]));
    }

    @Override
    public Type findById(String id) {
        log.debug("Start <findById> Params: " + id);
        Type type = types.get(id);
        log.debug("End <findById> Params: " + type);

        return type;
    }
}
