package com.example.test_task_expandapis.service;

import com.example.test_task_expandapis.model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(String tableName, List<Product> products);
}
