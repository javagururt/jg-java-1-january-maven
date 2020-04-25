package com.javaguru.shoppinglist;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    private Map<Long, Product> database = new HashMap<>();

    public Product findById(Long id) {
        return database.get(id);
    }

    public Product save(Product product) {
        throw new UnsupportedOperationException("Method save unsupported");
    }
}
