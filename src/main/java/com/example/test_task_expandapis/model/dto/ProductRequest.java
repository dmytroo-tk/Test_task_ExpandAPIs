package com.example.test_task_expandapis.model.dto;

import com.example.test_task_expandapis.model.Product;

import java.util.List;

public record ProductRequest(String table, List<Product> records) {
}
