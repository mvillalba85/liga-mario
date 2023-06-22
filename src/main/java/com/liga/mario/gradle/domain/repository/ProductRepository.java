package com.liga.mario.gradle.domain.repository;

import com.liga.mario.gradle.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getByCategoriaOrderByNombre(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Optional<Product> getByNombre(String name);
    Product save(Product product);
    void delete(int productId);

}
