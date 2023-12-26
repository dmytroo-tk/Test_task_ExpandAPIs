package com.example.test_task_expandapis.rest;

import com.example.test_task_expandapis.model.dto.ProductRequest;
import com.example.test_task_expandapis.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest.table(), productRequest.records());
        return ResponseEntity.ok("");
    }

    @GetMapping("/all/{table}")
    public ResponseEntity<?> getAll(@PathVariable String table) {

        return ResponseEntity.ok("");
    }
}
