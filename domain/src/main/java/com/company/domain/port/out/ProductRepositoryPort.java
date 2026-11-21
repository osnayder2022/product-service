package com.company.domain.port.out;

import com.company.domain.entity.Product;

public interface ProductRepositoryPort {
    Product save(Product product);
    Product update(Product product);
    Product findById(Long id);
    boolean existsById(Long id);
    boolean existsByName(String name);
    void deleteById(Long id);
}
