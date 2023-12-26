package com.example.test_task_expandapis.service.impl;

import com.example.test_task_expandapis.model.Product;
import com.example.test_task_expandapis.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS %s (" +
            "id SERIAL PRIMARY KEY, " +
            "entry_date VARCHAR(255), " +
            "item_code VARCHAR(255), " +
            "item_name VARCHAR(255), " +
            "item_quantity INT, " +
            "status VARCHAR(255)" +
            ")";
    private static final String INSERT_PRODUCT_QUERY = "INSERT INTO %s (entry_date, item_code, item_name, item_quantity, status) " +
            "VALUES (:entryDate, :itemCode, :itemName, :itemQuantity, :status)";

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void addProduct(String tableName, List<Product> products) {
        createTable(tableName);

        products.forEach(product -> entityManager
                .createNativeQuery(String.format(INSERT_PRODUCT_QUERY, tableName))
                .setParameter("entryDate", product.entryDate())
                .setParameter("itemCode", product.itemCode())
                .setParameter("itemName", product.itemName())
                .setParameter("itemQuantity", product.itemQuantity())
                .setParameter("status", product.status())
                .executeUpdate());
    }

    private void createTable(String tableName) {
        entityManager
                .createNativeQuery(String.format(CREATE_TABLE_QUERY, tableName))
                .executeUpdate();
    }
}
